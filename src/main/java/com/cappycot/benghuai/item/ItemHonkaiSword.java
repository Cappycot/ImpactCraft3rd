package com.cappycot.benghuai.item;

import java.util.List;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.cappycot.benghuai.HonkaiConfig;
import com.cappycot.benghuai.HonkaiValues;
import com.cappycot.benghuai.ImpactCraft;
import com.cappycot.benghuai.util.Alliance;
import com.google.common.collect.Multimap;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Swords (broadswords and katanas) are separated from the rest of the weapons
 * just in case other mods base gameplay tweaks based on weapon class.
 */
public abstract class ItemHonkaiSword extends ItemSword implements HonkaiWeapon {

	private String name;
	private int spcap; // TODO: Find a better name for the SP threshold.

	public ItemHonkaiSword(ToolMaterial material, String name, int spcap) {
		super(material); // TODO: Change material to new Honkai material.
		setMaxDamage(spcap);
		setRegistryName(name);
		setNoRepair();
		setUnlocalizedName(HonkaiValues.MODID + "." + name);
		this.name = name;
		this.spcap = spcap;
	}

	public void registerItemModel() {
		ImpactCraft.proxy.registerItemRenderer(this, 0, name);
	}

	@Override
	public double getDurabilityForDisplay(ItemStack stack) {
		return 1 - super.getDurabilityForDisplay(stack);
	}

	@Override
	public int getRGBDurabilityForDisplay(ItemStack stack) {
		return stack.getItemDamage() == stack.getMaxDamage() ? 0x0000FFFF : 0x00FF8000;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return stack.getItemDamage() == stack.getMaxDamage();
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		if (stack.getItemDamage() < stack.getMaxDamage())
			stack.setItemDamage(stack.getItemDamage() + 1);
		return true;
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos,
			EntityLivingBase entityLiving) {
		return false;
	}

	/*
	 * Beginning of major overrides.
	 */

	/**
	 * Called when the player Left Clicks (attacks) an entity. Processed before
	 * damage is done, if return value is true further processing is canceled and
	 * the entity is not attacked.
	 *
	 * @param stack  The Item being used
	 * @param player The player that is attacking
	 * @param entity The entity being attacked
	 * @return True to cancel the rest of the interaction.
	 */
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		return entity instanceof EntityLivingBase && Alliance.isAllyOfPlayer((EntityLivingBase) entity, player);
	}

	public abstract double getDamage(int upgrades);

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
		if (equipmentSlot == EntityEquipmentSlot.MAINHAND) {
			int upgrades = 0;
			NBTTagCompound tags = itemStack.getTagCompound();
			if (tags != null)
				upgrades = tags.getInteger("upgrades");
			// 0 gives an attack of 1 (0.5 heart).
			AttributeModifier damageModifier = new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier",
					getDamage(upgrades), 0);
			// 0 gives an attack speed of 4 (times per second).
			AttributeModifier speedModifier = new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", 6, 0);
			// remove the entries added by superclass (to allow 'overwriting')
			multimap.remove(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), damageModifier);
			multimap.remove(SharedMonsterAttributes.ATTACK_SPEED.getName(), speedModifier);

			multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), damageModifier);
			multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), speedModifier);
			// multimap.put(EntityPlayer.REACH_DISTANCE.getName(), new
			// AttributeModifier(UUID.fromString("7f10172d-de69-49d7-81bd-9594286a6827"),
			// "Weapon modifier", 1, 0));
		}
		return multimap;
	}

	/**
	 * Allow the item one last chance to modify its name used for the tool highlight
	 * useful for adding something extra that can't be removed by a user in the
	 * displayed name, such as a mode of operation.
	 *
	 * @param item        the ItemStack for the item.
	 * @param displayName the name that will be displayed unless it is changed in
	 *                    this method.
	 */
	public String getHighlightTip(ItemStack item, String displayName) {
		NBTTagCompound tags = item.getTagCompound();
		if (tags != null) {
			int upgrades = tags.getInteger("upgrades");
			if (upgrades > 0)
				// I don't think String.format gives that great of an optimization if any in
				// this case.
				return displayName + " +" + upgrades;
		}
		return displayName;
	}

	/**
	 * allows items to add custom lines of information to the mouseover description
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		// tooltip.add("sword");
		// if (flagIn.isAdvanced())
		// tooltip.add("derc");
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
}
