package com.cappycot.benghuai.item;

import com.cappycot.benghuai.HonkaiConfig;
import com.cappycot.benghuai.entity.EntityRaikiriSwords;
import com.cappycot.benghuai.util.Alliance;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemNuadasGrief extends ItemHonkaiGauntlet {

	public ItemNuadasGrief(String name) {
		super(name, HonkaiConfig.NUADAS_GRIEF.SP);
	}

	private static boolean airgetlam(ItemStack stack) {
		NBTTagCompound tags = stack.getTagCompound();
		return tags != null && tags.getBoolean("airgetlam");
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
		super.onUpdate(stack, world, entity, itemSlot, isSelected);
		if (!(entity instanceof EntityPlayer))
			return;
		else if (airgetlam(stack)
				&& (stack != ((EntityPlayer) entity).getHeldItemMainhand() || !gauntletsActive(stack)))
			stack.getTagCompound().setBoolean("airgetlam", false);
	}

	@Override
	public double getDurabilityForDisplay(ItemStack stack) {
		return airgetlam(stack) ? 0D : super.getDurabilityForDisplay(stack);
	}

	@Override
	public int getRGBDurabilityForDisplay(ItemStack stack) {
		return airgetlam(stack) ? 0x00FF0000 : super.getRGBDurabilityForDisplay(stack);
	}

	@Override
	public boolean showDurabilityBar(ItemStack stack) {
		return airgetlam(stack) || super.showDurabilityBar(stack);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return airgetlam(stack) || super.hasEffect(stack);
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity victim) {
		World world = player.world;
		if (airgetlam(stack) && victim instanceof EntityLivingBase
				&& !Alliance.shouldNotHarmByPlayer((EntityLivingBase) victim, player)) {
			float skillDamage = 0F;
			if (!world.isRemote) {
				int upgrades = 0;
				NBTTagCompound tags = stack.getTagCompound();
				if (tags != null)
					upgrades = tags.getInteger("upgrades");
				switch (upgrades) {
				case 0:
				case 1:
				case 2:
				default:
					skillDamage = 5.0F;
					break;
				}
			}
			for (EntityLivingBase entity : world.getEntitiesWithinAABB(EntityLivingBase.class,
					victim.getEntityBoundingBox().grow(2, 0, 2))) {
				if (!Alliance.shouldNotHarmByPlayer(entity, player)) {
					if (!world.isRemote) {
						// TODO: Make sure damage source is correct. Also check damage invuln time.
						entity.attackEntityFrom(DamageSource.causePlayerDamage(player), skillDamage);
						// TODO: Find a good velocity somewhere between a push and a yA YEET.
						entity.setVelocity(0, 1, 0);
					} else {
						world.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, entity.posX,
								entity.posY + entity.height / 2D, entity.posZ, 1.0D, 0.0D, 0.0D);
					}
				}
			}
			world.playSound((EntityPlayer) null, victim.posX, victim.posY, victim.posZ,
					SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 4.0F,
					(1.0F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.2F) * 0.7F);
			if (!player.capabilities.isCreativeMode)
				player.getCooldownTracker().setCooldown(this, 400);
			player.setSprinting(false);
			stack.getTagCompound().setBoolean("airgetlam", false);
			return true; // Cancel rest of attack.
		}
		return super.onLeftClickEntity(stack, player, victim);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		if (hand != EnumHand.MAIN_HAND)
			return super.onItemRightClick(world, player, hand);
		ItemStack itemStack = player.getHeldItemMainhand();
		if (!world.isRemote && gauntletsActive(itemStack) && !airgetlam(itemStack)
				&& itemStack.getItemDamage() == itemStack.getMaxDamage()
				&& !player.getCooldownTracker().hasCooldown(this)) {
			NBTTagCompound tags = itemStack.getTagCompound();
			if (tags == null) {
				tags = new NBTTagCompound();
				itemStack.setTagCompound(tags);
			}
			if (!player.capabilities.isCreativeMode)
				player.getCooldownTracker().setCooldown(this, 400);
			itemStack.setItemDamage(0);
			tags.setBoolean("airgetlam", true);
		}
		return new ActionResult<ItemStack>(EnumActionResult.PASS, player.getHeldItem(hand));
	}
}
