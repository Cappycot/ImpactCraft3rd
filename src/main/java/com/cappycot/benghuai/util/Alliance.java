package com.cappycot.benghuai.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.entity.passive.AbstractHorse;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;

public class Alliance {

	/**
	 * Trust no one lmao
	 */
	private static boolean isDirectEnemyOfPlayer(EntityLivingBase entity, EntityPlayer player) {
		if (entity.getLastAttackedEntity() == player)
			return true;
		return (entity instanceof EntityLiving) && ((EntityLiving) entity).getAttackTarget() == player;
	}

	/**
	 * Checks for entities that are known to be part of a player's party/team. The
	 * following effects are applicable to this list of entities:
	 * <ul>
	 * <li>Raikiri Blades</li>
	 * </ul>
	 */
	public static boolean isAllyOfPlayer(EntityLivingBase entity, EntityPlayer player) {
		if (entity == player)
			return true;
		else if (isDirectEnemyOfPlayer(entity, player))
			return false;
		else if (entity instanceof EntityTameable && ((EntityTameable) entity).getOwner() == player)
			return true;
		return (entity instanceof EntityPlayer) && entity.isOnSameTeam(player);
	}

	/**
	 * List of entities that should probably not be attacked by the player to
	 * prevent collateral from attacks such as:
	 * <ul>
	 * <li>Gauntlet Shockwaves</li>
	 * <li>Nuada's Grief Airgetlam</li>
	 * <li>Raikiri Blades</li>
	 * <ul>
	 */
	public static boolean shouldNotHarmByPlayer(EntityLivingBase entity, EntityPlayer player) {
		if (isAllyOfPlayer(entity, player))
			return true;
		else if (isDirectEnemyOfPlayer(entity, player))
			return false;
		else if (entity instanceof AbstractHorse && entity.getPassengers().size() == 0)
			return true;
		else if (entity instanceof EntityIronGolem || entity instanceof EntitySnowman
				|| entity instanceof EntityVillager)
			return true;
		else if (entity instanceof EntityPlayer && ((EntityPlayer) entity).capabilities.isCreativeMode)
			return true; // Here for aggro prevention purposes.
		else if (entity instanceof EntityTameable) {
			EntityTameable entityTameable = (EntityTameable) entity;
			if (entityTameable.getOwner() != null && isAllyOfPlayer(entityTameable.getOwner(), player))
				return true;
		} else if (entity.getRidingEntity() instanceof EntityLivingBase
				&& isAllyOfPlayer((EntityLivingBase) entity.getRidingEntity(), player))
			return true;
		else
			for (Entity e : entity.getPassengers())
				if (e instanceof EntityLivingBase && isAllyOfPlayer((EntityLivingBase) e, player))
					return true;
		return false;
	}
}
