package com.cappycot.benghuai.item;

import java.util.UUID;

import javax.annotation.Nonnull;

import com.cappycot.benghuai.HonkaiStrings;
import com.cappycot.benghuai.ImpactCraft;
import com.google.common.collect.Multimap;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Swords (broadswords and katanas) are separated from the rest of the weapons
 * for
 */
public abstract class ItemHonkaiSword extends ItemSword implements HonkaiWeapon {

	private String name;
	private int spcap; // TODO: Find a better name for the SP threshold.

	public ItemHonkaiSword(ToolMaterial material, String name, int spcap) {
		super(material); // TODO: Change material to new Honkai material.
		setMaxDamage(spcap);
		setRegistryName(name);
		setUnlocalizedName(HonkaiStrings.MODID + "." + name);
		this.name = name;
		this.spcap = spcap;
	}

	public void registerItemModel() {
		ImpactCraft.proxy.registerItemRenderer(this, 0, name);
	}

	@Override
	public double getDurabilityForDisplay(ItemStack stack) {
		return 1 - super.getDurabilityForDisplay(stack);
	}

	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return false;
	}

	@Override
	public int getRGBDurabilityForDisplay(ItemStack stack) {
		return 0x0000FFFF;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return stack.getItemDamage() == stack.getMaxDamage();
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		if (stack.getItemDamage() < stack.getMaxDamage())
			stack.damageItem(1, attacker);
		return true;
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos,
			EntityLivingBase entityLiving) {
		return true;
	}

	@Override
	public boolean showDurabilityBar(ItemStack stack) {
		return true;
	}
}
