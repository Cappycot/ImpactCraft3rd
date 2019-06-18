package com.cappycot.benghuai.registry;

import com.cappycot.benghuai.HonkaiLocations;
import com.cappycot.benghuai.HonkaiValues;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class SoundRegistry {

	public static final SoundEvent SOUND_AIRGETLAM = new SoundEvent(HonkaiLocations.SOUND_AIRGETLAM)
			.setRegistryName(HonkaiLocations.SOUND_AIRGETLAM);
	public static final SoundEvent SOUND_LYIN = new SoundEvent(HonkaiLocations.RECORD_LYIN)
			.setRegistryName(HonkaiLocations.RECORD_LYIN);

	@SubscribeEvent
	public static void registerSounds(RegistryEvent.Register<SoundEvent> event) {
		event.getRegistry().registerAll(SOUND_AIRGETLAM, SOUND_LYIN);
	}
}
