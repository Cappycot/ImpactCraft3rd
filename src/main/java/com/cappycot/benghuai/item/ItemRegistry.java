package com.cappycot.benghuai.item;

import java.util.Arrays;

import com.cappycot.benghuai.HonkaiValues;
import com.cappycot.benghuai.item.weapon.ItemTranquilArias;
import com.cappycot.benghuai.registry.SoundRegistry;
import com.cappycot.benghuai.util.ItemHelper;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@EventBusSubscriber
public class ItemRegistry {

	public static final ItemHonkai CRYSTAL = new ItemHonkai(HonkaiValues.CRYSTAL)
			.setCreativeTab(CreativeTabs.MATERIALS);
	public static final ItemHonkai CRYSTAL_SHARD = new ItemHonkai(HonkaiValues.CRYSTAL_SHARD)
			.setCreativeTab(CreativeTabs.MATERIALS);
	public static final ItemNuadasGrief NUADAS_GRIEF = new ItemNuadasGrief(HonkaiValues.NUADAS_GRIEF);
	public static final ItemRaikiri RAIKIRI = new ItemRaikiri(HonkaiValues.RAIKIRI);
	public static final ItemTranquilArias TRANQUIL_ARIAS = new ItemTranquilArias(HonkaiValues.TRANQUIL_ARIAS);
	public static final ItemHonkaiRecord RECORD_LYIN = new ItemHonkaiRecord("lyin", SoundRegistry.SOUND_LYIN);

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(CRYSTAL, CRYSTAL_SHARD, NUADAS_GRIEF, RAIKIRI, TRANQUIL_ARIAS, RECORD_LYIN);
		CRYSTAL.registerItemModel();
		CRYSTAL_SHARD.registerItemModel();
		NUADAS_GRIEF.registerItemModel();
		RAIKIRI.registerItemModel();
		TRANQUIL_ARIAS.registerItemModel();
		RECORD_LYIN.registerItemModel();
	}
}
