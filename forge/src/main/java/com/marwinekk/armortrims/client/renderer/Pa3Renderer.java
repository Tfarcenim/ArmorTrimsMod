package com.marwinekk.armortrims.client.renderer;

import net.minecraft.util.Mth;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;

import com.mojang.math.Axis;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

import com.marwinekk.armortrims.entity.Pa3Entity;
import com.marwinekk.armortrims.client.model.Modelhigharrow_java;

public class Pa3Renderer extends EntityRenderer<Pa3Entity> {
	private static final ResourceLocation texture = new ResourceLocation("armor_trims:textures/entities/higharrow_java.png");
	private final Modelhigharrow_java model;

	public Pa3Renderer(EntityRendererProvider.Context context) {
		super(context);
		model = new Modelhigharrow_java(context.bakeLayer(Modelhigharrow_java.LAYER_LOCATION));
	}

	@Override
	public void render(Pa3Entity entityIn, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource bufferIn, int packedLightIn) {
		VertexConsumer vb = bufferIn.getBuffer(RenderType.entityCutout(this.getTextureLocation(entityIn)));
		poseStack.pushPose();
		poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTicks, entityIn.yRotO, entityIn.getYRot()) - 90));
		poseStack.mulPose(Axis.ZP.rotationDegrees(90 + Mth.lerp(partialTicks, entityIn.xRotO, entityIn.getXRot())));
		model.renderToBuffer(poseStack, vb, packedLightIn, OverlayTexture.NO_OVERLAY, 1, 1, 1, 0.0625f);
		poseStack.popPose();
		super.render(entityIn, entityYaw, partialTicks, poseStack, bufferIn, packedLightIn);
	}

	@Override
	public ResourceLocation getTextureLocation(Pa3Entity entity) {
		return texture;
	}
}
