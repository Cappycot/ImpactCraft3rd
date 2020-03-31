package cappycot.benghuai.handler;

import java.lang.reflect.Field;

import cappycot.benghuai.HonkaiValues;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class TimeSlowHandler {

	@SubscribeEvent
	public static void onLivingUpdate(LivingUpdateEvent event) {
		EntityLivingBase entity = event.getEntityLiving();
		if (!entity.getTags().contains(HonkaiValues.TIME_FRACTURE_TAG))
			return;
		// 3x Time Slow
		if (entity instanceof EntityPlayer)
			;
		else if (entity.ticksExisted % 3 != 0 && !(entity instanceof EntityPlayer)) {
			if (entity.getHealth() <= 0)
				entity.deathTime--;
			if (entity.hurtTime > 0)
				entity.hurtTime--;
			if (entity.hurtResistantTime > 0)
				entity.hurtResistantTime--;
			if (!entity.world.isRemote) {
				entity.onEntityUpdate();
				event.setCanceled(true);
			}
			// Should work for any instance of EntityCreeper, including mod entities,
			// provided that it uses the same mechanics of ignition time to explode.
			if (entity instanceof EntityCreeper) {
				EntityCreeper creeper = (EntityCreeper) entity;
				if (creeper.getCreeperState() > 0) {
					Field field;
					try {
						field = creeper.getClass().getDeclaredField("timeSinceIgnited");
						field.setAccessible(true);
						int timeSinceIgnited = field.getInt(creeper);
						if (timeSinceIgnited > 1)
							field.set(creeper, timeSinceIgnited - 1);
					} catch (Exception e) {
						// In this case we can't do anything about it.
					}
				}
			}
		}
	}
}
