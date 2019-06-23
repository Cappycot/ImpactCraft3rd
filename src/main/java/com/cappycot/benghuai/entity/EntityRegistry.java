package com.cappycot.benghuai.entity;

import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;

@EventBusSubscriber
public class EntityRegistry {

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<EntityEntry> event) {
		event.getRegistry().registerAll();
	}
}
