package cappycot.benghuai.tconstruct;

import cappycot.benghuai.item.ItemRegistry;
import net.minecraft.util.text.TextFormatting;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.ExtraMaterialStats;
import slimeknights.tconstruct.library.materials.HandleMaterialStats;
import slimeknights.tconstruct.library.materials.HeadMaterialStats;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.materials.MaterialTypes;

public class HonkaiTConstruct {
	public static void init() {
		Material honkaiCrystal = new Material("honkaicrystal", TextFormatting.LIGHT_PURPLE);
		honkaiCrystal.addTrait(new HonkaiEnergyTrait(), MaterialTypes.HEAD);
		honkaiCrystal.setCraftable(true);
		TinkerRegistry.addMaterial(honkaiCrystal);
		TinkerRegistry.addMaterialStats(honkaiCrystal, new HeadMaterialStats(250, 5.0F, 7.0F, 4),
				new HandleMaterialStats(0.5F, 250), new ExtraMaterialStats(125));
		honkaiCrystal.addItem(ItemRegistry.CRYSTAL, 1, Material.VALUE_Ingot);
		honkaiCrystal.addItem(ItemRegistry.CRYSTAL_SHARD, 1, Material.VALUE_Ingot / 3);
		honkaiCrystal.setRepresentativeItem(ItemRegistry.CRYSTAL);
	}
}
