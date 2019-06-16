package com.cappycot.benghuai.crafting;

import com.cappycot.benghuai.util.ItemHelper;
import com.google.gson.JsonObject;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.JsonUtils;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IIngredientFactory;
import net.minecraftforge.common.crafting.JsonContext;

/**
 * I wish I knew what I was doing here.
 */
public class HonkaiIngredientFactory implements IIngredientFactory {

	@Override
	public Ingredient parse(JsonContext context, JsonObject json) {
		final ItemStack stack = CraftingHelper.getItemStack(json, context);
		if (JsonUtils.hasField(json, "upgrades")) {
			int upgrades = JsonUtils.getInt(json, "upgrades");
			ItemHelper.setUpgrades(stack, upgrades);
			return new IngredientHonkaiUpgradeable(stack, upgrades);
		}
		return new IngredientHonkaiUpgradeable(stack);
	}
}
