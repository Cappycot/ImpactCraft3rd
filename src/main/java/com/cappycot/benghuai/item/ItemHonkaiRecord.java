package com.cappycot.benghuai.item;

import com.cappycot.benghuai.ImpactCraft;

import net.minecraft.item.ItemRecord;
import net.minecraft.util.SoundEvent;

public class ItemHonkaiRecord extends ItemRecord {

	private String title;

	public ItemHonkaiRecord(String title, SoundEvent soundIn) {
		super(title, soundIn);
		this.title = title;
		setRegistryName("record_" + title);
		setUnlocalizedName("record");
	}

	public void registerItemModel() {
		ImpactCraft.proxy.registerItemRenderer(this, 0, "record_" + title);
	}
}
