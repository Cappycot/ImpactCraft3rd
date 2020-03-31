package cappycot.benghuai.registry;

import cappycot.benghuai.HonkaiLocations;
import cappycot.benghuai.HonkaiValues;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class SoundRegistry {

	public static final SoundEvent SOUND_AIRGETLAM = new SoundEvent(HonkaiLocations.SOUND_AIRGETLAM)
			.setRegistryName(HonkaiLocations.SOUND_AIRGETLAM);
	public static final SoundEvent SOUND_PISTOL_FIRE = new SoundEvent(HonkaiLocations.SOUND_PISTOL_FIRE)
			.setRegistryName(HonkaiLocations.SOUND_PISTOL_FIRE);
	public static final SoundEvent SOUND_LYIN = new SoundEvent(HonkaiLocations.RECORD_LYIN)
			.setRegistryName(HonkaiLocations.RECORD_LYIN);

	@SubscribeEvent
	public static void registerSounds(RegistryEvent.Register<SoundEvent> event) {
		event.getRegistry().registerAll(SOUND_AIRGETLAM, SOUND_PISTOL_FIRE, SOUND_LYIN);
	}
}
