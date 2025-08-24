package com.iafenvoy.iceandfire.render.entity.layer;

import com.iafenvoy.iceandfire.entity.GorgonEntity;
import com.iafenvoy.iceandfire.entity.TrollEntity;
import com.iafenvoy.iceandfire.render.entity.TrollEntityRenderer;
import com.iafenvoy.iceandfire.render.model.TrollModel;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.util.math.MatrixStack;

public class TrollEyesFeatureRenderer extends FeatureRenderer<TrollEntity, TrollModel> {
    public TrollEyesFeatureRenderer(TrollEntityRenderer renderer) {
        super(renderer);
    }

    @Override
    public void render(MatrixStack matrixStackIn, VertexConsumerProvider bufferIn, int packedLightIn, TrollEntity troll, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (!GorgonEntity.isStoneMob(troll)) {
            RenderLayer tex = RenderLayer.getEyes(troll.getTrollType().getEyesTexture());
            VertexConsumer vertexConsumer = bufferIn.getBuffer(tex);
            this.getContextModel().render(matrixStackIn, vertexConsumer, packedLightIn, OverlayTexture.DEFAULT_UV, -1);
        }
    }
}
