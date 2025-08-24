package com.iafenvoy.iceandfire.render.entity.feature;

import com.iafenvoy.iceandfire.entity.PixieEntity;
import com.iafenvoy.iceandfire.render.entity.PixieEntityRenderer;
import com.iafenvoy.iceandfire.render.model.PixieModel;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class PixieGlowFeatureRenderer extends FeatureRenderer<PixieEntity, PixieModel> {
    public PixieGlowFeatureRenderer(PixieEntityRenderer renderIn) {
        super(renderIn);
    }

    @Override
    public void render(MatrixStack matrixStackIn, VertexConsumerProvider bufferIn, int packedLightIn, PixieEntity pixie, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        Identifier texture = switch (pixie.getColor()) {
            case 1 -> PixieEntityRenderer.TEXTURE_1;
            case 2 -> PixieEntityRenderer.TEXTURE_2;
            case 3 -> PixieEntityRenderer.TEXTURE_3;
            case 4 -> PixieEntityRenderer.TEXTURE_4;
            case 5 -> PixieEntityRenderer.TEXTURE_5;
            default -> PixieEntityRenderer.TEXTURE_0;
        };
        RenderLayer eyes = RenderLayer.getEyes(texture);
        VertexConsumer vertexConsumer = bufferIn.getBuffer(eyes);
        this.getContextModel().render(matrixStackIn, vertexConsumer, packedLightIn, OverlayTexture.DEFAULT_UV, -1);
    }
}