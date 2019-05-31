package com.cappycot.benghuai.item;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;

public abstract class ItemHonkaiGauntlet extends ItemHonkaiWeapon {

	public ItemHonkaiGauntlet(String name, int spcap) {
		super(name, spcap);
	}

	public boolean canDisableShield(ItemStack stack, ItemStack shield, EntityLivingBase entity,
			EntityLivingBase attacker) {
		return true;
	}

	/**
	 * your punches are stronker idk lmao
	 */
	public float getDestroySpeed(ItemStack stack, IBlockState state) {
		return 2.0F;
	}

}
