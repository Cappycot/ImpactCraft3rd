package com.cappycot.benghuai.proxy;

import com.cappycot.benghuai.HonkaiStrings;
import com.cappycot.benghuai.ImpactCraft;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerItemRenderer(Item item, int metadata, String id) {
		ModelLoader.setCustomModelResourceLocation(item, metadata,
				new ModelResourceLocation(HonkaiStrings.MODID + ":" + id, "inventory"));
	}

}
