package com.cappycot.benghuai.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class ItemHelper {

	public static NBTTagCompound getTags(ItemStack stack) {
		NBTTagCompound tags = stack.getTagCompound();
		if (tags == null) {
			tags = new NBTTagCompound();
			stack.setTagCompound(tags);
		}
		return tags;
	}
	
	public static int getUpgrades(ItemStack stack) {
		if (!stack.hasTagCompound())
			return 0;
		return stack.getTagCompound().getInteger("upgrades");
	}
	
	public static ItemStack setUpgrades(ItemStack stack, int upgrades) {
		getTags(stack).setInteger("upgrades", upgrades);
		return stack;
	}
}
