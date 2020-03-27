package com.cappycot.benghuai.handler;

import com.cappycot.benghuai.HonkaiValues;
import com.cappycot.benghuai.entity.EntityPistolShot;
import com.cappycot.benghuai.item.HonkaiWeapon;
import com.cappycot.benghuai.item.weapon.ItemHonkaiPistol;
import com.cappycot.benghuai.registry.SoundRegistry;
import com.cappycot.benghuai.util.ItemHelper;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SPacketAnimation;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.WorldServer;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@EventBusSubscriber
public class WeaponHandler {

	// TODO: Fix alpha code lol
	public static final SimpleNetworkWrapper NETWORK = NetworkRegistry.INSTANCE.newSimpleChannel("impactcraft");

	public static class PistolFireMessage implements IMessage {

		public PistolFireMessage() {
			// Default constructor for server side.
		}

		public PistolFireMessage(ItemHonkaiPistol pistol) {
			// TODO: Shot stats.
		}

		@Override
		public void fromBytes(ByteBuf buf) {
		}

		@Override
		public void toBytes(ByteBuf buf) {
		}
	}

	// TODO: Find a more suitable class location for pistol firing.
	public static class PistolFireHandler implements IMessageHandler<PistolFireMessage, IMessage> {

		@Override
		public IMessage onMessage(PistolFireMessage message, MessageContext ctx) {
			EntityPlayerMP player = ctx.getServerHandler().player;
			WorldServer world = player.getServerWorld();
			world.addScheduledTask(() -> {
				// Check cooldown on mainhand pistol.
				ItemStack main = player.getHeldItemMainhand();
				ItemStack off = player.getHeldItemOffhand();
				if (!(main.getItem() instanceof ItemHonkaiPistol))
					return;
				// System.out.println(player.ticksExisted);
				int lastMain = ItemHelper.getTimeFired(main);
				if (lastMain + HonkaiValues.PISTOL_FIRE_COOLDOWN < player.ticksExisted) {
					ItemHelper.setTimeFired(main, player.ticksExisted);
					// Send arm swing notification to everyone except the firing player.
					world.getEntityTracker().sendToTracking(player, new SPacketAnimation(player, 0));
				} else if (off.getItem() instanceof ItemHonkaiPistol
						&& lastMain + HonkaiValues.PISTOL_FIRE_COOLDOWN / 2 < player.ticksExisted
						&& ItemHelper.getTimeFired(off) < lastMain) {
					ItemHelper.setTimeFired(off, player.ticksExisted);
					// Send arm swing notification to everyone except the firing player.
					world.getEntityTracker().sendToTracking(player, new SPacketAnimation(player, 3));
				} else
					return;
				EntityPistolShot entityarrow = new EntityPistolShot(world, player);
				entityarrow.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 4.0F, 0.0F);
				world.spawnEntity(entityarrow);
				// player.playSound(SoundRegistry.SOUND_PISTOL_FIRE, 2.0F, 1.0F);
				world.playSound((EntityPlayer) null, player.posX, player.posY, player.posZ,
						SoundRegistry.SOUND_PISTOL_FIRE, SoundCategory.PLAYERS, 4.0F, 1.0F);
				// System.out.println("fired a shot");

			});
			return null;
		}
	}

	/**
	 * Firing mechanism for pistols when the attack button is on the mouse.
	 */
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public static void onMouse(MouseEvent event) {
		if (event.getButton() != -1 && event.isButtonstate()
				&& Minecraft.getMinecraft().gameSettings.keyBindAttack.getKeyCode() - event.getButton() == -100) {
			Item item = Minecraft.getMinecraft().player.getHeldItemMainhand().getItem();
			if (item instanceof ItemHonkaiPistol) {
				NETWORK.sendToServer(new PistolFireMessage((ItemHonkaiPistol) item));
				event.setCanceled(true);
			}
		}
	}

	/**
	 * Firing mechanism for pistols when the attack button is not on the mouse.
	 */
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public static void onInput(InputEvent event) {
		Item item = Minecraft.getMinecraft().player.getHeldItemMainhand().getItem();
		if (item instanceof ItemHonkaiPistol && Minecraft.getMinecraft().gameSettings.keyBindAttack.isPressed())
			NETWORK.sendToServer(new PistolFireMessage((ItemHonkaiPistol) item));
	}

	/**
	 * Allow for instant weapon skill use in creative mode.
	 */
	@SubscribeEvent
	public static void onLivingEquipmentChange(LivingEquipmentChangeEvent event) {
		ItemStack stack = event.getTo();
		if (!(event.getEntityLiving() instanceof EntityPlayer))
			return;
		EntityPlayer player = (EntityPlayer) event.getEntityLiving();
		if (stack.getItem() instanceof HonkaiWeapon && player.capabilities.isCreativeMode)
			stack.setItemDamage(0);
	}
}
