package cappycot.benghuai.handler;

import cappycot.benghuai.entity.EntityPistolShot;
import cappycot.benghuai.item.HonkaiWeapon;
import cappycot.benghuai.util.Alliance;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class DamageHandler {

	/**
	 * Mitigate damage invulnerability time with Honkai weapons.
	 */
	@SubscribeEvent
	public static void onLivingAttack(LivingAttackEvent event) {
		EntityLivingBase entity = event.getEntityLiving();
		DamageSource source = event.getSource();
		Entity immediateSource = source.getImmediateSource();
		Entity trueSource = source.getTrueSource();
		if (trueSource instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) trueSource;
			if (immediateSource instanceof EntityPistolShot || immediateSource == trueSource
					&& player.getHeldItemMainhand().getItem() instanceof HonkaiWeapon) {
				// Have to do that last check because of sweeping sword damage.
				if (Alliance.isAllyOfPlayer(entity, player))
					event.setCanceled(true);
				else {
					entity.hurtResistantTime = 0;
					entity.hurtTime = 0;
				}
			}
		}
	}
}
