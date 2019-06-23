package com.cappycot.benghuai.handler;

import com.cappycot.benghuai.entity.EntityPistolShot;
import com.cappycot.benghuai.item.HonkaiWeapon;
import com.cappycot.benghuai.item.weapon.ItemHonkaiPistol;
import com.cappycot.benghuai.registry.SoundRegistry;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.WorldServer;
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

	public static class PistolFireHandler implements IMessageHandler<PistolFireMessage, IMessage> {

		@Override
		public IMessage onMessage(PistolFireMessage message, MessageContext ctx) {
			EntityPlayerMP player = ctx.getServerHandler().player;
			WorldServer world = player.getServerWorld();
			world.addScheduledTask(() -> {
				EntityPistolShot entityarrow = new EntityPistolShot(world, player);
				entityarrow.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 10.0F, 0.0F);
				world.spawnEntity(entityarrow);
				// player.playSound(SoundRegistry.SOUND_PISTOL_FIRE, 2.0F, 1.0F);
				world.playSound((EntityPlayer) null, player.posX, player.posY, player.posZ,
						SoundRegistry.SOUND_PISTOL_FIRE, SoundCategory.PLAYERS, 1.0F, 1.0F);
			});
			return null;
		}
	}

	/**
	 * Firing mechanism for pistols.
	 */
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public static void onInput(InputEvent event) {
		Item item = Minecraft.getMinecraft().player.getHeldItemMainhand().getItem();
		if (item instanceof ItemHonkaiPistol && Minecraft.getMinecraft().gameSettings.keyBindAttack.isPressed()) {
			System.out.println("fire");
			NETWORK.sendToServer(new PistolFireMessage((ItemHonkaiPistol) item));
		}
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
