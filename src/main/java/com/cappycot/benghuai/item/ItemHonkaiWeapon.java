package com.cappycot.benghuai.item;

import com.cappycot.benghuai.HonkaiValues;
import com.cappycot.benghuai.ImpactCraft;
import com.cappycot.benghuai.util.Alliance;

import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class ItemHonkaiWeapon extends Item implements HonkaiWeapon {

	private String name;
	private int spcap; // TODO: Find a better name for the SP threshold.

	public ItemHonkaiWeapon(String name, int spcap) {
		setMaxDamage(spcap);
		setMaxStackSize(1);
		setRegistryName(name);
		setCreativeTab(CreativeTabs.COMBAT);
		setNoRepair();
		setUnlocalizedName(HonkaiValues.MODID + "." + name);
		this.name = name;
		this.spcap = spcap;
	}

	public void registerItemModel() {
		ImpactCraft.proxy.registerItemRenderer(this, 0, name);
	}

	// Overrides of Item Functions
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

	@Override
	public boolean canDestroyBlockInCreative(World world, BlockPos pos, ItemStack stack, EntityPlayer player) {
		return false;
	}

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
				// I don't think String.format gives that great of an optimization if any.
				return displayName + " +" + upgrades;
		}
		return displayName;
	}
}
