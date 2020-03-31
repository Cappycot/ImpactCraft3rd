package cappycot.benghuai.crafting;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import cappycot.benghuai.item.IHonkaiUpgradeable;
import cappycot.benghuai.util.ItemHelper;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IRecipeFactory;
import net.minecraftforge.common.crafting.JsonContext;

/**
 * Use for when ingredients do not change with each level upgrade.
 */
public class HonkaiShapelessUpgradeRecipe extends ShapelessRecipes {

	IHonkaiUpgradeable target;

	public HonkaiShapelessUpgradeRecipe(String group, ItemStack output, NonNullList<Ingredient> ingredients) {
		super(group, output, ingredients);
		// Yes I know this will crash if the wrong item is used.
		// TODO: Throw custom error if item is not upgrade type.
		if (!(output.getItem() instanceof IHonkaiUpgradeable))
			;
		this.target = (IHonkaiUpgradeable) output.getItem();
	}

	/**
	 * Used to check if a recipe matches current crafting inventory
	 */
	@Override
	public boolean matches(InventoryCrafting inv, World worldIn) {
		boolean ismatching = super.matches(inv, worldIn);
		for (int i = 0; ismatching && i < inv.getSizeInventory(); i++) {
			ItemStack s = inv.getStackInSlot(i);
			if (s.getItem() == target)
				ismatching = ItemHelper.getUpgrades(s) + 1 <= target.getMaxUpgrades();
		}
		return ismatching;
	}

	/**
	 * Returns an Item that is the result of this recipe
	 */
	@Override
	public ItemStack getCraftingResult(InventoryCrafting inv) {
		ItemStack result = super.getCraftingResult(inv);
		if (!result.isEmpty()) {
			for (int i = 0; i < inv.getSizeInventory(); i++) {
				ItemStack s = inv.getStackInSlot(i);
				if (result.getItem() == s.getItem()) {
					ItemHelper.setUpgrades(result, ItemHelper.getUpgrades(s) + 1);
					result.setItemDamage(s.getItemDamage());
					break;
				}
			}
		}
		return result;
	}

	public static class Factory implements IRecipeFactory {
		@Override
		public IRecipe parse(JsonContext context, JsonObject json) {
			String group = JsonUtils.getString(json, "group", "");
			NonNullList<Ingredient> ingredients = NonNullList.create();
			ItemStack output = CraftingHelper.getItemStack(JsonUtils.getJsonObject(json, "result"), context);
			for (JsonElement ele : JsonUtils.getJsonArray(json, "ingredients"))
				ingredients.add(CraftingHelper.getIngredient(ele, context));
			if (ingredients.isEmpty())
				throw new JsonParseException("No ingredients for shapeless recipe");
			return new HonkaiShapelessUpgradeRecipe(group, output, ingredients);
		}
	}
}
