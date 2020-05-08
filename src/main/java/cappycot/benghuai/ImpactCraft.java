package cappycot.benghuai;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cappycot.benghuai.handler.WeaponHandler;
import cappycot.benghuai.proxy.CommonProxy;
import cappycot.benghuai.tconstruct.HonkaiTConstruct;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = HonkaiValues.MODID, name = HonkaiValues.NAME, version = HonkaiValues.VERSION, dependencies = "after:mantle;after:tconstruct;")
public class ImpactCraft {

	private static Logger logger = LogManager.getLogger(HonkaiValues.NAME);

	@Instance(HonkaiValues.MODID)
	public static ImpactCraft instance;

	@SidedProxy(serverSide = "cappycot.benghuai.proxy.CommonProxy", clientSide = "cappycot.benghuai.proxy.ClientProxy")
	public static CommonProxy proxy;

	public ImpactCraft() {
		String[] noOneWillFindThis = { "Fly away, let me fly away...", "I was born out of this great divide...",
				"Hail my proud queen on and on, on and on...", "Never let you go, it's why I did them all...",
				"There is no \"Exception\" in this library...", "Crash against the barrier above the crowd...",
				"PWISHHHH CAPTAIN ON BRIDGE!", "It took me 100 supply crates to get this mod working.",
				"Teri Teri! Sekai de ichiban kawaii!!", "That'll be 8088 crystals. Cash or card?" };
		logger.info(noOneWillFindThis[(int) (Math.random() * noOneWillFindThis.length)]);
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.preInit(event);

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
