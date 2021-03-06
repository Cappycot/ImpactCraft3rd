package cappycot.benghuai.item.weapon;

import javax.annotation.Nonnull;

import com.google.common.collect.Multimap;

import cappycot.benghuai.HonkaiValues;
import cappycot.benghuai.item.ItemHonkaiWeapon;
import cappycot.benghuai.util.ItemHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemHonkaiPistol extends ItemHonkaiWeapon {

	public ItemHonkaiPistol(String name, int maxSP, int maxUpgrades) {
		super(name, maxSP, maxUpgrades);
	}

	public static boolean pistolsActive(ItemStack gauntlet) {
		NBTTagCompound tags = gauntlet.getTagCompound();
		return tags != null && tags.getBoolean("pistols");
	}

	/**
	 * Called each tick as long the item is on a player inventory. Uses by maps to
	 * check if is on a player hand and update it's contents.
	 */
	public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
		if (!(entity instanceof EntityPlayer))
			return;
		EntityPlayer player = (EntityPlayer) entity;
		int lastFired = ItemHelper.getTimeFired(stack);
		if (lastFired > player.ticksExisted && stack.hasTagCompound())
			stack.getTagCompound().removeTag(HonkaiValues.PISTOL_FIRED_TAG);
	}

	/**
	 * Thanks twilightforest mod lol
	 * 
	 * Changed to non-deprecated version.
	 */
	@Override
	@Nonnull
	public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot equipmentSlot,
			ItemStack itemStack) {
		Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(equipmentSlot, itemStack);
		NBTTagCompound tags = itemStack.getTagCompound();
		if (equipmentSlot == EntityEquipmentSlot.MAINHAND) {
			// 0 gives an attack of 1 (0.5 heart).
			AttributeModifier damageModifier = new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", 1, 0);
			AttributeModifier speedModifier = new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", 16, 0);
			// multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(),
			// damageModifier);
			// multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), speedModifier);
		}
		return multimap;
	}
}
