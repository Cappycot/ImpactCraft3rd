package com.cappycot.benghuai.item;

import com.cappycot.benghuai.HonkaiValues;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class ItemRegistry {

	public static final ItemTemplate CRYSTAL = new ItemCrystal(HonkaiValues.CRYSTAL)
			.setCreativeTab(CreativeTabs.MATERIALS);
	public static final ItemTemplate CRYSTAL_SHARD = new ItemCrystalShard(HonkaiValues.CRYSTAL_SHARD)
			.setCreativeTab(CreativeTabs.MATERIALS);
	public static final ItemNuadasGrief NUADAS_GRIEF = new ItemNuadasGrief(HonkaiValues.NUADAS_GRIEF);
	public static final ItemRaikiri RAIKIRI = new ItemRaikiri(HonkaiValues.RAIKIRI);

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(CRYSTAL, CRYSTAL_SHARD, NUADAS_GRIEF, RAIKIRI);
		CRYSTAL.registerItemModel();
		CRYSTAL_SHARD.registerItemModel();
		NUADAS_GRIEF.registerItemModel();
		RAIKIRI.registerItemModel();
	}
}
