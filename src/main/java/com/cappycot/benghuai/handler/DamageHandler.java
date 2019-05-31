package com.cappycot.benghuai.handler;

import com.cappycot.benghuai.entity.EntityRaikiriSwords;
import com.cappycot.benghuai.item.HonkaiWeapon;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class DamageHandler {

	/**
	 * Mitigate damage invulnerability time with Honkai weapons.
	 */
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void onLivingAttack(LivingAttackEvent event) {
		EntityLivingBase entity = event.getEntityLiving();
		DamageSource source = event.getSource();
		Entity trueSource = source.getTrueSource();
		if (trueSource instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) trueSource;
			ItemStack itemStack = player.getHeldItemMainhand();
			if (itemStack != null && itemStack.getItem() instanceof HonkaiWeapon) {
				entity.hurtResistantTime = 0;
				entity.hurtTime = 0;
			}
		} else if (source.getImmediateSource() instanceof EntityRaikiriSwords) {
			entity.hurtResistantTime = 0;
			entity.hurtTime = 0;
		}
	}
}
