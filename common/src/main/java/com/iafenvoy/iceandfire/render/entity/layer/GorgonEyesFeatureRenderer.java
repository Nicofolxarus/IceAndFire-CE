package com.iafenvoy.iceandfire.render.entity.layer;

import com.iafenvoy.iceandfire.IceAndFire;
import com.iafenvoy.iceandfire.entity.GorgonEntity;
import com.iafenvoy.iceandfire.render.entity.GorgonEntityRenderer;
import com.iafenvoy.iceandfire.render.model.GorgonModel;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class GorgonEyesFeatureRenderer extends FeatureRenderer<GorgonEntity, GorgonModel> {
    private static final Identifier TEXTURE = Identifier.of(IceAndFire.MOD_ID, "textures/entity/gorgon/gorgon_eyes.png");

    public GorgonEyesFeatureRenderer(GorgonEntityRenderer renderIn) {
        super(renderIn);
    }

    @Override
    public void render(MatrixStack matrixStackIn, VertexConsumerProvider bufferIn, int packedLightIn, GorgonEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (entity.getAnimation() == GorgonEntity.ANIMATION_SCARE || entity.getAnimation() == GorgonEntity.ANIMATION_HIT) {
            RenderLayer eyes = RenderLayer.getEyes(TEXTURE);
            VertexConsumer vertexConsumer = bufferIn.getBuffer(eyes);
            this.getContextModel().render(matrixStackIn, vertexConsumer, packedLightIn, OverlayTexture.DEFAULT_UV, -1);
        }
    }
}