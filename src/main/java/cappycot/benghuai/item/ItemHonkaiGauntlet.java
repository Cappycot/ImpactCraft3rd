package cappycot.benghuai.item;

import javax.annotation.Nonnull;

import com.google.common.collect.Multimap;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public abstract class ItemHonkaiGauntlet extends ItemHonkaiWeapon {

	public ItemHonkaiGauntlet(String name, int maxSP, int maxUpgrades) {
		super(name, maxSP, maxUpgrades);
	}

	public static boolean gauntletsActive(ItemStack gauntlet) {
		NBTTagCompound tags = gauntlet.getTagCompound();
		return tags != null && tags.getBoolean("gauntlets");
	}

	/**
	 * No shield can stand up 100% to a barrage of ORAORAORAORAORAORAORA...
	 */
	public boolean canDisableShield(ItemStack stack, ItemStack shield, EntityLivingBase entity,
			EntityLivingBase attacker) {
		// ItemStack mainStack = attacker.getHeldItemMainhand();
		// TODO: Check that the stack variable is pointing to main hand attacker stack.
		// TODO: Reduce canDisableShield chance from 100% to some percent.
		return gauntletsActive(stack);
	}

	/**
	 * Called each tick as long the item is on a player inventory. Uses by maps to
	 * check if is on a player hand and update it's contents.
	 */
	public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
		if (!(entity instanceof EntityPlayer))
			return;
		EntityPlayer player = (EntityPlayer) entity;
		NBTTagCompound tags = stack.getTagCompound();
		if (tags == null) {
			tags = new NBTTagCompound();
			stack.setTagCompound(tags);
		}
		if (isSelected && !player.getHeldItemOffhand().getItem().equals(stack.getItem()))
			tags.setBoolean("gauntlets", false);
		else
			tags.setBoolean("gauntlets", true);
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
			multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), damageModifier);
			if (tags == null || tags.getBoolean("gauntlets")) {
				// 0 gives an attack speed of 4 (times per second).
				AttributeModifier speedModifier = new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", 16,
						0);
				multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), speedModifier);
			}
		}
		return multimap;
	}

	/**
	 * your punches are stronker idk lmao
	 */
	public float getDestroySpeed(ItemStack stack, IBlockState state) {
		return gauntletsActive(stack) ? 2.0F : 1.5F;
	}

}
