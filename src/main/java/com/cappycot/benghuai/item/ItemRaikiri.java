package com.cappycot.benghuai.item;

import javax.annotation.Nonnull;

import com.cappycot.benghuai.HonkaiNumbers;
import com.cappycot.benghuai.entity.EntityRaikiriSwords;
import com.google.common.collect.Multimap;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemRaikiri extends ItemHonkaiSword {

	public ItemRaikiri(ToolMaterial material, String name) {
		super(material, name, HonkaiNumbers.RAIKIRI_SP);
	}

	/**
	 * Called when the equipped item is right clicked.
	 */
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		if (hand != EnumHand.MAIN_HAND)
			return super.onItemRightClick(world, player, hand);
		ItemStack itemStack = player.getHeldItemMainhand();
		if (!world.isRemote && itemStack.getItemDamage() == itemStack.getMaxDamage()
				&& !player.getCooldownTracker().hasCooldown(this)) {
			// TODO: Set durability to HonkaiNumbers class.
			player.getCooldownTracker().setCooldown(this,
					player.capabilities.isCreativeMode ? 120 : HonkaiNumbers.RAIKIRI_COOLDOWN * 20);
			for (EntityLivingBase entity : world.getEntitiesWithinAABB(EntityLivingBase.class,
					player.getEntityBoundingBox().grow(16D)))
				if ((entity == player
						|| entity instanceof EntityTameable && ((EntityTameable) entity).getOwner() == player
						|| entity instanceof EntityPlayer && ((EntityPlayer) entity).isOnSameTeam(player))
						&& entity.width <= 1.0F && entity.height <= 3.0F)
					world.spawnEntity(new EntityRaikiriSwords(world, player, entity));
			itemStack.setItemDamage(0);
		}
		return new ActionResult<ItemStack>(EnumActionResult.PASS, player.getHeldItem(hand));
	}

	/**
	 * Thanks twilightforest mod lol
	 */
	@Override
	@Nonnull
	public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot) {
		Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);

		if (equipmentSlot == EntityEquipmentSlot.MAINHAND) {
			// 0 gives an attack of 1 (0.5 heart).
			AttributeModifier damageModifier = new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", 4, 0);
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
}
