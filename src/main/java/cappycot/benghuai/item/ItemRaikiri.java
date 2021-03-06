package cappycot.benghuai.item;

import cappycot.benghuai.HonkaiConfig;
import cappycot.benghuai.entity.EntityRaikiriSwords;
import cappycot.benghuai.util.Alliance;
import cappycot.benghuai.util.ItemHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemRaikiri extends ItemHonkaiSword {

	public ItemRaikiri(String name) {
		super(ToolMaterial.DIAMOND, name, HonkaiConfig.RAIKIRI.SP, 3);
	}

	/**
	 * Blade Ward: Summon Raikiri blades.
	 */
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		if (hand != EnumHand.MAIN_HAND)
			return super.onItemRightClick(world, player, hand);
		ItemStack itemStack = player.getHeldItemMainhand();
		if (!world.isRemote && itemStack.getItemDamage() == 0 && !player.getCooldownTracker().hasCooldown(this)) {
			float bladeDamage;
			int bladeTicks;
			switch (ItemHelper.getUpgrades(itemStack)) {
			case 0:
				bladeDamage = (float) HonkaiConfig.RAIKIRI.bladeDamage0;
				bladeTicks = HonkaiConfig.RAIKIRI.bladeTicks0;
				break;
			case 1:
				bladeDamage = (float) HonkaiConfig.RAIKIRI.bladeDamage1;
				bladeTicks = HonkaiConfig.RAIKIRI.bladeTicks1;
				break;
			case 2:
				bladeDamage = (float) HonkaiConfig.RAIKIRI.bladeDamage2;
				bladeTicks = HonkaiConfig.RAIKIRI.bladeTicks2;
				break;
			default:
				bladeDamage = (float) HonkaiConfig.RAIKIRI.bladeDamage3;
				bladeTicks = HonkaiConfig.RAIKIRI.bladeTicks3;
				break;
			}
			player.getCooldownTracker().setCooldown(this,
					player.capabilities.isCreativeMode ? Math.min(bladeTicks, HonkaiConfig.RAIKIRI.cooldown)
							: HonkaiConfig.RAIKIRI.cooldown);
			for (EntityLivingBase entity : world.getEntitiesWithinAABB(EntityLivingBase.class,
					player.getEntityBoundingBox().grow(HonkaiConfig.RAIKIRI.range)))
				if (Alliance.isAllyOfPlayer(entity, player) && entity.width <= 1.0F && entity.height <= 3.0F)
					world.spawnEntity(new EntityRaikiriSwords(world, player, entity, bladeDamage, bladeTicks));
			// TODO: Grant defense bonus to entities with blades.
			// AttributeModifier.player.getEntityAttribute(SharedMonsterAttributes.ARMOR).applyModifier(modifier);
			itemStack.setItemDamage(itemStack.getMaxDamage());
		}
		return new ActionResult<ItemStack>(EnumActionResult.PASS, player.getHeldItem(hand));
	}

	@Override
	public double getDamage(int upgrades) { // TODO: Decide if ItemStack is needed for a parameter.
		switch (upgrades) {
		case 0:
			return HonkaiConfig.RAIKIRI.damage0;
		case 1:
			return HonkaiConfig.RAIKIRI.damage1;
		case 2:
			return HonkaiConfig.RAIKIRI.damage2;
		default:
			return HonkaiConfig.RAIKIRI.damage3;
		}
	}
}
