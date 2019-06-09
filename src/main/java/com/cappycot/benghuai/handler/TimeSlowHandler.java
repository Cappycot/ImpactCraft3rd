package com.cappycot.benghuai.handler;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.IMob;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class TimeSlowHandler {

	// @SubscribeEvent
	public static void onLivingUpdate(LivingUpdateEvent event) {

		EntityLivingBase entity = event.getEntityLiving();
		// 3x Time Slow
		if (entity instanceof IMob && entity.ticksExisted % 3 != 0 && !entity.world.isRemote) {
			// if (entity.hurtTime > 0)
			// entity.hurtTime--;
			// if (entity.hurtResistantTime > 0)
			// entity.hurtResistantTime--;
			// entity.onEntityUpdate();
			// EntityCreeper creeper = (EntityCreeper) entity;
			// if (creeper.getCreeperState() > 0) {
			// Field field;
			// try {
			// field = creeper.getClass().getDeclaredField("timeSinceIgnited");
			// field.setAccessible(true);
			// int timeSinceIgnited = field.getInt(creeper);
			// if (timeSinceIgnited > 1)
			// field.set(creeper, timeSinceIgnited - 1);
			// } catch (Exception e) {
			// e.printStackTrace();
			// }
			// }
			// event.setCanceled(true);
		}
	}
}
