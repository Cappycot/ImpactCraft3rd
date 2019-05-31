package com.cappycot.benghuai.item;

import com.cappycot.benghuai.HonkaiStrings;
import com.cappycot.benghuai.ImpactCraft;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class ItemHonkaiWeapon extends Item implements HonkaiWeapon {

	private String name;
	private int spcap; // TODO: Find a better name for the SP threshold.

	public ItemHonkaiWeapon(String name, int spcap) {
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
	public int getRGBDurabilityForDisplay(ItemStack stack) {
		return 0x0000FFFF;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return stack.getItemDamage() == 0;
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		stack.damageItem(-1, attacker);
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
