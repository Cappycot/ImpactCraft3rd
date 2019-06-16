package com.cappycot.benghuai.crafting;

import javax.annotation.Nullable;

import com.cappycot.benghuai.util.ItemHelper;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;

/**
 * Modified IngredientNBT
 */
public class IngredientHonkaiUpgradeable extends Ingredient {
	private final ItemStack stack;
	private int upgrades = -1;;

	protected IngredientHonkaiUpgradeable(ItemStack stack) {
		super(stack);
		this.stack = stack;
	}

	protected IngredientHonkaiUpgradeable(ItemStack stack, int upgrades) {
		super(stack);
		this.stack = stack;
		this.upgrades = upgrades;
	}

	@Override
	public boolean apply(@Nullable ItemStack input) {
		if (input == null)
			return false;
		return this.stack.getItem() == input.getItem()
				&& (upgrades == -1 || ItemHelper.getUpgrades(input) == ItemHelper.getUpgrades(stack));
	}

	@Override
	public boolean isSimple() {
		return false;
	}
}