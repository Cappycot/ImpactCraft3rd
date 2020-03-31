package cappycot.benghuai.item;

import cappycot.benghuai.HonkaiValues;
import cappycot.benghuai.ImpactCraft;
import cappycot.benghuai.util.Alliance;
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
	private int maxSP;
	private int maxUpgrades;

	public ItemHonkaiWeapon(String name, int maxSP, int maxUpgrades) {
		if (maxSP > 0)
			setMaxDamage(maxSP);
		setMaxStackSize(1);
		setRegistryName(name);
		setCreativeTab(CreativeTabs.COMBAT);
		setNoRepair();
		setUnlocalizedName(HonkaiValues.MODID + "." + name);
		this.name = name;
		this.maxSP = maxSP;
		this.maxUpgrades = maxUpgrades;
	}

	@Override
	public int getMaxSP() {
		return maxSP;
	}

	@Override
	public int getMaxUpgrades() {
		return maxUpgrades;
	}

	public void registerItemModel() {
		ImpactCraft.proxy.registerItemRenderer(this, 0, name);
	}

	@Override
	public boolean showDurabilityBar(ItemStack stack) {
		return maxSP > 0;
	}

	@Override
	public int getRGBDurabilityForDisplay(ItemStack stack) {
		return stack.getItemDamage() == 0 ? 0x0000FFFF : 0x00FF8000;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return maxSP > 0 && stack.getItemDamage() == 0;
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		if (stack.getItemDamage() > 0)
			stack.setItemDamage(stack.getItemDamage() - 1);
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
	 * @param stack
	 *            The Item being used
	 * @param player
	 *            The player that is attacking
	 * @param entity
	 *            The entity being attacked
	 * @return True to cancel the rest of the interaction.
	 */
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		return entity instanceof EntityLivingBase && Alliance.isAllyOfPlayer((EntityLivingBase) entity, player);
	}
}
