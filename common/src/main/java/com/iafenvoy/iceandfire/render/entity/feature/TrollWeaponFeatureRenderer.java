package com.iafenvoy.iceandfire.render.entity.feature;

import com.iafenvoy.iceandfire.entity.GorgonEntity;
import com.iafenvoy.iceandfire.entity.TrollEntity;
import com.iafenvoy.iceandfire.render.entity.TrollEntityRenderer;
import com.iafenvoy.iceandfire.render.model.TrollModel;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.util.math.MatrixStack;

public class TrollWeaponFeatureRenderer extends FeatureRenderer<TrollEntity, TrollModel> {
    public TrollWeaponFeatureRenderer(TrollEntityRenderer renderer) {
        super(renderer);
    }

    @Override
    public void render(MatrixStack matrixStackIn, VertexConsumerProvider bufferIn, int packedLightIn, TrollEntity troll, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (troll.getWeaponType() != null && !GorgonEntity.isStoneMob(troll)) {
            RenderLayer tex = RenderLayer.getEntityCutout(troll.getWeaponType().getTexture());
            this.getContextModel().render(matrixStackIn, bufferIn.getBuffer(tex), packedLightIn, OverlayTexture.DEFAULT_UV, -1);
        }
    }
}