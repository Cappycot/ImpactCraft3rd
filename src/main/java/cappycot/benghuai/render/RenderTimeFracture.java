package cappycot.benghuai.render;

import cappycot.benghuai.entity.EntityTimeSlowField;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderTimeFracture extends Render<EntityTimeSlowField> {

	public RenderTimeFracture(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityTimeSlowField entity) {
		return null;
	}

	@Override
	public void doRender(EntityTimeSlowField entity, double x, double y, double z, float entityYaw,
			float partialTicks) {
		// Do nothing.
	}
}
