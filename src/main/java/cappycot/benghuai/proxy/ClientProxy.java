package cappycot.benghuai.proxy;

import cappycot.benghuai.HonkaiValues;
import cappycot.benghuai.entity.EntityPistolShot;
import cappycot.benghuai.entity.EntityRaikiriSwords;
import cappycot.benghuai.entity.EntityTimeSlowField;
import cappycot.benghuai.render.RenderNothing;
import cappycot.benghuai.render.RenderRaikiriSwords;
import cappycot.benghuai.render.RenderTimeFracture;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
		RenderingRegistry.registerEntityRenderingHandler(EntityRaikiriSwords.class,
				new IRenderFactory<EntityRaikiriSwords>() {
					@Override
					public Render<? super EntityRaikiriSwords> createRenderFor(RenderManager manager) {
						// TODO Auto-generated method stub
						return new RenderRaikiriSwords(manager);
					}
				});
		RenderingRegistry.registerEntityRenderingHandler(EntityTimeSlowField.class,
				new IRenderFactory<EntityTimeSlowField>() {
					@Override
					public Render<? super EntityTimeSlowField> createRenderFor(RenderManager manager) {
						// TODO Auto-generated method stub
						return new RenderTimeFracture(manager);
					}
				});
		RenderingRegistry.registerEntityRenderingHandler(EntityPistolShot.class,
				new IRenderFactory<EntityPistolShot>() {
					@SuppressWarnings("unchecked")
					@Override
					public Render<? super EntityPistolShot> createRenderFor(RenderManager manager) {
						// TODO Auto-generated method stub
						return new RenderNothing(manager);
					}
				});
	}

	@Override
	public void registerItemRenderer(Item item, int metadata, String id) {
		ModelLoader.setCustomModelResourceLocation(item, metadata,
				new ModelResourceLocation(HonkaiValues.MODID + ":" + id, "inventory"));
	}

}
