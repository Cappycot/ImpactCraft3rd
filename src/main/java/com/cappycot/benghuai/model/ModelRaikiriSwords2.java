package com.cappycot.benghuai.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

/**
 * Model for secondary decoration swords.
 * 
 * Raikiri - Cappycot Created using Tabula 7.0.1
 */
public class ModelRaikiriSwords2 extends ModelBase {
	public ModelRenderer HandleBase;
	public ModelRenderer shape49;
	public ModelRenderer shape50;
	public ModelRenderer LowCoverBack;
	public ModelRenderer HighCoverFront;
	public ModelRenderer LowBladeBack;
	public ModelRenderer HandleCoverLow;
	public ModelRenderer TipBottom;
	public ModelRenderer HighBladeFront;
	public ModelRenderer DiagonalTop;
	public ModelRenderer HandleCoverHigh;
	public ModelRenderer BlueLightLeft;
	public ModelRenderer BlueLightRight;
	public ModelRenderer HighBladeBack;
	public ModelRenderer HighCoverBack;
	public ModelRenderer TipTop;
	public ModelRenderer LowCoverFront;
	public ModelRenderer LowBladeFront;
	public ModelRenderer HandleTip;
	public ModelRenderer HandleUpper;
	public ModelRenderer LowestBladeBack;
	public ModelRenderer LowestBladeFront;
	public ModelRenderer HandleBottom;
	public ModelRenderer CoverTip;
	public ModelRenderer TipBack;
	public ModelRenderer HandleCoverLow_1;
	public ModelRenderer DiagonalBottom;
	public ModelRenderer HandleCoverHigh2;

	public ModelRaikiriSwords2() {
		this.textureWidth = 32;
		this.textureHeight = 32;
		this.LowBladeFront = new ModelRenderer(this, 0, 10);
		this.LowBladeFront.setRotationPoint(0.0F, 0.3F, -18.0F);
		this.LowBladeFront.addBox(-0.5F, -0.5F, -6.0F, 1, 1, 6, 0.0F);
		this.HandleTip = new ModelRenderer(this, 0, 10);
		this.HandleTip.setRotationPoint(0.0F, -0.2F, 1.0F);
		this.HandleTip.addBox(-0.5F, -1.0F, -1.0F, 1, 2, 1, 0.0F);
		this.HandleBottom = new ModelRenderer(this, 0, 10);
		this.HandleBottom.setRotationPoint(0.0F, 0.8F, -9.0F);
		this.HandleBottom.addBox(-0.5F, -0.5F, -2.0F, 1, 1, 2, 0.0F);
		this.LowCoverFront = new ModelRenderer(this, 0, 10);
		this.LowCoverFront.setRotationPoint(0.0F, -0.7F, -18.0F);
		this.LowCoverFront.addBox(-0.5F, -0.5F, -6.0F, 1, 1, 6, 0.0F);
		this.HighCoverBack = new ModelRenderer(this, 0, 10);
		this.HighCoverBack.setRotationPoint(0.0F, -0.8F, -24.0F);
		this.HighCoverBack.addBox(-0.5F, -0.5F, -4.0F, 1, 1, 4, 0.0F);
		this.TipTop = new ModelRenderer(this, 0, 10);
		this.TipTop.setRotationPoint(0.0F, -1.0F, -32.0F);
		this.TipTop.addBox(-0.5F, -0.5F, -1.0F, 1, 1, 1, 0.0F);
		this.shape49 = new ModelRenderer(this, 0, 10);
		this.shape49.setRotationPoint(0.0F, 12.0F, -6.0F);
		this.shape49.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
		this.CoverTip = new ModelRenderer(this, 0, 10);
		this.CoverTip.setRotationPoint(0.0F, -1.0F, -31.0F);
		this.CoverTip.addBox(-0.5F, -0.5F, -1.0F, 1, 1, 1, 0.0F);
		this.HandleBase = new ModelRenderer(this, 0, 10);
		this.HandleBase.setRotationPoint(0.0F, 11.75F, -15.0F);
		this.HandleBase.addBox(-0.5F, -1.0F, -12.0F, 1, 2, 8, 0.0F);
		// TODO: Use the Math.PI constant instead of very long floating point decimal.
		this.setRotateAngle(HandleBase, 0.0F, 0.0F, -1.5707963267948966F);
		this.HandleCoverLow = new ModelRenderer(this, 0, 10);
		this.HandleCoverLow.setRotationPoint(0.0F, 0.6F, -7.0F);
		this.HandleCoverLow.addBox(-1.0F, -0.5F, -4.0F, 2, 1, 4, 0.0F);
		this.DiagonalTop = new ModelRenderer(this, 0, 10);
		this.DiagonalTop.setRotationPoint(0.0F, -0.6F, -8.8F);
		this.DiagonalTop.addBox(-1.0F, -0.5F, -1.0F, 2, 1, 4, 0.0F);
		this.setRotateAngle(DiagonalTop, -0.7853981633974483F, 0.0F, 0.0F);
		this.BlueLightLeft = new ModelRenderer(this, 0, 10);
		this.BlueLightLeft.setRotationPoint(0.2F, 0.0F, -6.5F);
		this.BlueLightLeft.addBox(-0.5F, -0.5F, -1.0F, 1, 1, 1, 0.0F);
		this.DiagonalBottom = new ModelRenderer(this, 0, 10);
		this.DiagonalBottom.setRotationPoint(0.0F, 1.0F, 0.0F);
		this.DiagonalBottom.addBox(-1.0F, -0.5F, 0.0F, 2, 1, 3, 0.0F);
		this.LowCoverBack = new ModelRenderer(this, 0, 10);
		this.LowCoverBack.setRotationPoint(0.0F, -0.6F, -12.0F);
		this.LowCoverBack.addBox(-0.5F, -0.5F, -6.0F, 1, 1, 6, 0.0F);
		this.HighCoverFront = new ModelRenderer(this, 0, 10);
		this.HighCoverFront.setRotationPoint(0.0F, -0.9F, -28.0F);
		this.HighCoverFront.addBox(-0.5F, -0.5F, -3.0F, 1, 1, 3, 0.0F);
		this.setRotateAngle(HighCoverFront, 0.0F, 0.0F, -0.0036651914291880917F);
		this.BlueLightRight = new ModelRenderer(this, 0, 10);
		this.BlueLightRight.setRotationPoint(-0.2F, 0.0F, -6.5F);
		this.BlueLightRight.addBox(-0.5F, -0.5F, -1.0F, 1, 1, 1, 0.0F);
		this.LowestBladeBack = new ModelRenderer(this, 0, 10);
		this.LowestBladeBack.setRotationPoint(0.0F, 0.6F, -13.0F);
		this.LowestBladeBack.addBox(-0.5F, -0.5F, -5.0F, 1, 1, 5, 0.0F);
		this.HandleUpper = new ModelRenderer(this, 0, 10);
		this.HandleUpper.setRotationPoint(0.0F, -0.1F, 0.0F);
		this.HandleUpper.addBox(-0.5F, -1.0F, -4.0F, 1, 2, 4, 0.0F);
		this.LowBladeBack = new ModelRenderer(this, 0, 10);
		this.LowBladeBack.setRotationPoint(0.0F, 0.4F, -12.0F);
		this.LowBladeBack.addBox(-0.5F, -0.5F, -6.0F, 1, 1, 6, 0.0F);
		this.HighBladeBack = new ModelRenderer(this, 0, 10);
		this.HighBladeBack.setRotationPoint(0.0F, 0.2F, -24.0F);
		this.HighBladeBack.addBox(-0.5F, -0.5F, -4.0F, 1, 1, 4, 0.0F);
		this.TipBottom = new ModelRenderer(this, 0, 10);
		this.TipBottom.setRotationPoint(0.0F, -0.2F, -32.0F);
		this.TipBottom.addBox(-0.5F, -0.5F, -1.0F, 1, 1, 1, 0.0F);
		this.HighBladeFront = new ModelRenderer(this, 0, 10);
		this.HighBladeFront.setRotationPoint(0.0F, 0.1F, -28.0F);
		this.HighBladeFront.addBox(-0.5F, -0.5F, -3.0F, 1, 1, 3, 0.0F);
		this.HandleCoverLow_1 = new ModelRenderer(this, 0, 10);
		this.HandleCoverLow_1.setRotationPoint(0.0F, -0.1F, 0.0F);
		this.HandleCoverLow_1.addBox(-1.0F, -0.5F, -4.0F, 2, 1, 4, 0.0F);
		this.HandleCoverHigh = new ModelRenderer(this, 0, 10);
		this.HandleCoverHigh.setRotationPoint(0.0F, -0.6F, -7.5F);
		this.HandleCoverHigh.addBox(-1.0F, -0.5F, -3.0F, 2, 1, 3, 0.0F);
		this.LowestBladeFront = new ModelRenderer(this, 0, 10);
		this.LowestBladeFront.setRotationPoint(0.0F, 0.5F, -18.0F);
		this.LowestBladeFront.addBox(-0.5F, -0.5F, -3.0F, 1, 1, 3, 0.0F);
		this.HandleCoverHigh2 = new ModelRenderer(this, 0, 10);
		this.HandleCoverHigh2.setRotationPoint(0.0F, 0.1F, 0.0F);
		this.HandleCoverHigh2.addBox(-1.0F, -0.5F, -3.0F, 2, 1, 3, 0.0F);
		this.shape50 = new ModelRenderer(this, 0, 10);
		this.shape50.setRotationPoint(0.0F, 12.0F, -8.0F);
		this.shape50.addBox(-1.0F, 0.0F, -16.0F, 2, 1, 16, 0.0F);
		this.TipBack = new ModelRenderer(this, 0, 10);
		this.TipBack.setRotationPoint(0.0F, 0.0F, -31.0F);
		this.TipBack.addBox(-0.5F, -0.5F, -1.0F, 1, 1, 1, 0.0F);
		this.HandleBase.addChild(this.LowBladeFront);
		this.HandleBase.addChild(this.HandleTip);
		this.HandleBase.addChild(this.HandleBottom);
		this.HandleBase.addChild(this.LowCoverFront);
		this.HandleBase.addChild(this.HighCoverBack);
		this.HandleBase.addChild(this.TipTop);
		this.HandleBase.addChild(this.CoverTip);
		this.HandleBase.addChild(this.HandleCoverLow);
		this.HandleBase.addChild(this.DiagonalTop);
		this.HandleBase.addChild(this.BlueLightLeft);
		this.DiagonalTop.addChild(this.DiagonalBottom);
		this.HandleBase.addChild(this.LowCoverBack);
		this.HandleBase.addChild(this.HighCoverFront);
		this.HandleBase.addChild(this.BlueLightRight);
		this.HandleBase.addChild(this.LowestBladeBack);
		this.HandleBase.addChild(this.HandleUpper);
		this.HandleBase.addChild(this.LowBladeBack);
		this.HandleBase.addChild(this.HighBladeBack);
		this.HandleBase.addChild(this.TipBottom);
		this.HandleBase.addChild(this.HighBladeFront);
		this.HandleCoverLow.addChild(this.HandleCoverLow_1);
		this.HandleBase.addChild(this.HandleCoverHigh);
		this.HandleBase.addChild(this.LowestBladeFront);
		this.HandleCoverHigh.addChild(this.HandleCoverHigh2);
		this.HandleBase.addChild(this.TipBack);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float scale) {
		this.HandleBase.render(scale);
	}

	/**
	 * This is a helper function from Tabula to set the rotation of model parts
	 */
	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}