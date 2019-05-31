package com.cappycot.benghuai.item;

import com.cappycot.benghuai.HonkaiStrings;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class ItemRegistry {

	public static final ItemTemplate CRYSTAL = new ItemCrystal(HonkaiStrings.CRYSTAL).setCreativeTab(CreativeTabs.MATERIALS);
	public static final ItemTemplate CRYSTAL_SHARD = new ItemCrystalShard(HonkaiStrings.CRYSTAL_SHARD).setCreativeTab(CreativeTabs.MATERIALS);
	public static final ItemRaikiri RAIKIRI = new ItemRaikiri(ToolMaterial.DIAMOND, HonkaiStrings.RAIKIRI);

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(CRYSTAL, CRYSTAL_SHARD, RAIKIRI);
		CRYSTAL.registerItemModel();
		CRYSTAL_SHARD.registerItemModel();
		RAIKIRI.registerItemModel();
	}
}
