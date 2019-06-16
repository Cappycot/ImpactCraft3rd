package com.cappycot.benghuai.handler;

import com.cappycot.benghuai.item.HonkaiWeapon;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class WeaponHandler {

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
