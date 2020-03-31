package cappycot.benghuai.tconstruct;

import com.cappycot.benghuai.ImpactCraft;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class HonkaiEnergyTrait extends AbstractTrait {

	public HonkaiEnergyTrait() {
		super("honkaienergy", TextFormatting.LIGHT_PURPLE);
	}

	@Override
	public float damage(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, float newDamage,
			boolean isCritical) {
		return super.damage(tool, player, target, damage, newDamage * ImpactCraft.getCivilizationMultiplier(),
				isCritical);
	}
}
