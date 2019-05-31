package com.cappycot.benghuai.render;

import com.cappycot.benghuai.HonkaiLocations;
import com.cappycot.benghuai.entity.EntityRaikiriSwords;
import com.cappycot.benghuai.model.ModelRaikiriSwords;
import com.cappycot.benghuai.model.ModelRaikiriSwords2;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderRaikiriSwords extends Render {

	private static final ModelRaikiriSwords modelRaikiri = new ModelRaikiriSwords();
	private static final ModelRaikiriSwords2 modelRaikiri2 = new ModelRaikiriSwords2();

	public RenderRaikiriSwords(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) { 
		return HonkaiLocations.RAIKIRI_SWORDS_TEXTURE;
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float entityYaw, float partialTicks) {
		bindEntityTexture(entity);
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
		GlStateManager.pushMatrix();
		// int j = 15728880; // 15 << 20 | 15 << 4; // 61680;
		// int k = 240; // j % 65536;
		// int l = 240; // j / 65536;
		// Make this brighter than the Discord light theme.
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240F, 240F);
		// TODO: This is broken rendering from Tabula so figure out how to fix.
		GlStateManager.translate((float) x, (float) y + 1.5F, (float) z);
		GlStateManager.rotate(180F, 1F, 0, 0);
		float rotation = (entity.ticksExisted % 60 + partialTicks) * 24F;
		GlStateManager.rotate(rotation, 0, -1F, 0);
		for (int i = 0; i < 3; i++) {
			modelRaikiri.render(entity, 0, 0, 0, 0, 0, 0.0625F);
			GlStateManager.rotate(120F, 0, 1F, 0);
		}
		if (entity.ticksExisted < 5) {
			GlStateManager.rotate(360F - rotation + (entity.ticksExisted % 5 + partialTicks) * 72F, 0, -1F, 0);
			modelRaikiri2.render(entity, 0, 0, 0, 0, 0, 0.0625F);
			GlStateManager.rotate(120F, 0, 1F, 0);
			modelRaikiri2.render(entity, 0, 0, 0, 0, 0, 0.0625F);
			GlStateManager.rotate(120F, 0, 1F, 0);
			modelRaikiri2.render(entity, 0, 0, 0, 0, 0, 0.0625F);
		}
		GlStateManager.popMatrix();
	}
}
