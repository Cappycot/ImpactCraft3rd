package cappycot.benghuai.item;

import cappycot.benghuai.HonkaiValues;
import cappycot.benghuai.ImpactCraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemHonkai extends Item {

	private String name;

	public ItemHonkai(String name) {
		this.name = name;
		setRegistryName(name);
		setUnlocalizedName(HonkaiValues.MODID + "." + name);
	}

	public void registerItemModel() {
		ImpactCraft.proxy.registerItemRenderer(this, 0, name);
	}

	@Override
	public ItemHonkai setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}
}
