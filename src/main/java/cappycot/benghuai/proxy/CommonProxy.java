package cappycot.benghuai.proxy;

import cappycot.benghuai.HonkaiValues;
import cappycot.benghuai.ImpactCraft;
import cappycot.benghuai.entity.EntityPistolShot;
import cappycot.benghuai.entity.EntityRaikiriSwords;
import cappycot.benghuai.entity.EntityTimeSlowField;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class CommonProxy {

	public static final int RAIKIRI_SWORDS_ID = 480; // TODO: Move to new entity IDs class.

	public void preInit(FMLPreInitializationEvent event) {
		// TODO: Move to new class with event listener for entity registration.
		EntityRegistry.registerModEntity(new ResourceLocation(HonkaiValues.MODID, HonkaiValues.RAIKIRI_SWORDS_NAME),
				EntityRaikiriSwords.class, HonkaiValues.RAIKIRI_SWORDS_NAME, RAIKIRI_SWORDS_ID, ImpactCraft.instance,
				64, 1, false);

		EntityRegistry.registerModEntity(new ResourceLocation(HonkaiValues.MODID, "timeslow"),
				EntityTimeSlowField.class, "timeslow", RAIKIRI_SWORDS_ID + 1, ImpactCraft.instance, 64, 1, false);

		EntityRegistry.registerModEntity(new ResourceLocation(HonkaiValues.MODID, "pistolshot"), EntityPistolShot.class,
				"pistolshot", RAIKIRI_SWORDS_ID + 2, ImpactCraft.instance, 64, 1, false);
	}

	public void registerItemRenderer(Item item, int metadata, String id) {
	}

}
