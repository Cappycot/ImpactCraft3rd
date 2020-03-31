package cappycot.benghuai;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cappycot.benghuai.entity.EntityPistolShot;
import cappycot.benghuai.entity.EntityRaikiriSwords;
import cappycot.benghuai.entity.EntityTimeSlowField;
import cappycot.benghuai.handler.WeaponHandler;
import cappycot.benghuai.proxy.CommonProxy;
import cappycot.benghuai.render.RenderNothing;
import cappycot.benghuai.render.RenderRaikiriSwords;
import cappycot.benghuai.render.RenderTimeFracture;
import cappycot.benghuai.tconstruct.HonkaiTConstruct;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = HonkaiValues.MODID, name = HonkaiValues.NAME, version = HonkaiValues.VERSION, dependencies = "after:mantle;after:tconstruct;")
public class ImpactCraft {

	private static Logger logger = LogManager.getLogger(HonkaiValues.NAME);

	@Instance(HonkaiValues.MODID)
	public static ImpactCraft instance;

	@SidedProxy(serverSide = "com.cappycot.benghuai.proxy.CommonProxy", clientSide = "com.cappycot.benghuai.proxy.ClientProxy")
	public static CommonProxy proxy;

	public ImpactCraft() {
		String[] noOneWillFindThis = { "Fly away, let me fly away...", "I was born out of this great divide...",
				"Hail my proud queen on and on, on and on...", "Never let you go, it's why I did them all...",
				"PWISHHHH CAPTAIN ON BRIDGE!", "It took me 100 supply crates to get this mod working.",
				"Teri Teri! Sekai de ichiban kawaii!!", "That'll be 8088 crystals. Cash or card?" };
		logger.info(noOneWillFindThis[(int) (Math.random() * noOneWillFindThis.length)]);
	}

	public static final int RAIKIRI_SWORDS_ID = 480; // TODO: Move to new entity IDs class.

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		// TODO: Move to new class with event listener for entity registration.
		EntityRegistry.registerModEntity(new ResourceLocation(HonkaiValues.MODID, HonkaiValues.RAIKIRI_SWORDS_NAME),
				EntityRaikiriSwords.class, HonkaiValues.RAIKIRI_SWORDS_NAME, RAIKIRI_SWORDS_ID, ImpactCraft.instance,
				64, 1, false);
		RenderingRegistry.registerEntityRenderingHandler(EntityRaikiriSwords.class,
				new IRenderFactory<EntityRaikiriSwords>() {
					@Override
					public Render<? super EntityRaikiriSwords> createRenderFor(RenderManager manager) {
						// TODO Auto-generated method stub
						return new RenderRaikiriSwords(manager);
					}
				});
		EntityRegistry.registerModEntity(new ResourceLocation(HonkaiValues.MODID, "timeslow"),
				EntityTimeSlowField.class, "timeslow", RAIKIRI_SWORDS_ID + 1, ImpactCraft.instance, 64, 1, false);
		RenderingRegistry.registerEntityRenderingHandler(EntityTimeSlowField.class,
				new IRenderFactory<EntityTimeSlowField>() {
					@Override
					public Render<? super EntityTimeSlowField> createRenderFor(RenderManager manager) {
						// TODO Auto-generated method stub
						return new RenderTimeFracture(manager);
					}
				});
		EntityRegistry.registerModEntity(new ResourceLocation(HonkaiValues.MODID, "pistolshot"), EntityPistolShot.class,
				"pistolshot", RAIKIRI_SWORDS_ID + 2, ImpactCraft.instance, 64, 1, false);
		RenderingRegistry.registerEntityRenderingHandler(EntityPistolShot.class,
				new IRenderFactory<EntityPistolShot>() {
					@Override
					public Render<? super EntityPistolShot> createRenderFor(RenderManager manager) {
						// TODO Auto-generated method stub
						return new RenderNothing(manager);
					}
				});
		WeaponHandler.NETWORK.registerMessage(WeaponHandler.PistolFireHandler.class,
				WeaponHandler.PistolFireMessage.class, 0, Side.SERVER);

		if (Loader.isModLoaded("tconstruct"))
			HonkaiTConstruct.init();
		else
			logger.info("Tinkers Construct not found. Skipping material.");

		logger.info("Completed preInit event.");
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
	}

	private static float CIV_MULTIPLIER = 1F;

	public static float getCivilizationMultiplier() {
		return CIV_MULTIPLIER;
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		// TODO: Calculate civilization factor.
		int activeMods = Math.max(0, net.minecraftforge.fml.common.Loader.instance().getActiveModList().size() - 5);
		CIV_MULTIPLIER = 1F + 0.01F * activeMods;
		logger.info(String.format("There are %d active mods. Civilization multiplier set to %.2f.", activeMods,
				CIV_MULTIPLIER));
	}
}
