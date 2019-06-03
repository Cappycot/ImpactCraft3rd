package com.cappycot.benghuai.handler;

import com.cappycot.benghuai.item.HonkaiWeapon;
import com.cappycot.benghuai.item.ItemHonkaiSword;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class WeaponHandler {
	
	/**
	 * Reset weapon SP to 0.
	 */
	// @SubscribeEvent
	public static void onLivingEquipmentChange(LivingEquipmentChangeEvent event) {
		ItemStack itemTo = event.getTo();
		if (!(itemTo.getItem() instanceof ItemHonkaiSword && event.getEntityLiving() instanceof EntityPlayer))
			return;
		EntityPlayer player = (EntityPlayer) event.getEntityLiving();
		if (event.getFrom().getItem() != itemTo.getItem() && itemTo.getItem() instanceof HonkaiWeapon)
			itemTo.setItemDamage(player.capabilities.isCreativeMode ? itemTo.getMaxDamage() : 0);
	}
}
