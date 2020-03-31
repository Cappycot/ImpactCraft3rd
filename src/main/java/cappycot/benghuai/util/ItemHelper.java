package cappycot.benghuai.util;

import cappycot.benghuai.HonkaiValues;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ItemHelper {

	public static NBTTagCompound getTags(ItemStack stack) {
		NBTTagCompound tags = stack.getTagCompound();
		if (tags == null) {
			tags = new NBTTagCompound();
			stack.setTagCompound(tags);
		}
		return tags;
	}

	public static int getTimeFired(ItemStack stack) {
		if (!stack.hasTagCompound())
			return 0;
		return stack.getTagCompound().getInteger(HonkaiValues.PISTOL_FIRED_TAG);
	}

	public static ItemStack setTimeFired(ItemStack stack, int ticks) {
		getTags(stack).setInteger(HonkaiValues.PISTOL_FIRED_TAG, ticks);
		return stack;
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
