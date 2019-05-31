package com.cappycot.benghuai.item;

import com.cappycot.benghuai.HonkaiStrings;
import com.cappycot.benghuai.ImpactCraft;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public abstract class ItemTemplate extends Item {

	private String name;

	public ItemTemplate(String name) {
		this.name = name;
		setRegistryName(name);
		setUnlocalizedName(HonkaiStrings.MODID + "." + name);
	}

	public void registerItemModel() {
		ImpactCraft.proxy.registerItemRenderer(this, 0, name);
	}

	@Override
	public ItemTemplate setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}
}
