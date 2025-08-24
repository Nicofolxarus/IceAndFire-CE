package com.iafenvoy.iceandfire.render.block;

import com.iafenvoy.iceandfire.item.block.entity.EggInIceBlockEntity;
import com.iafenvoy.iceandfire.render.model.DragonEggModel;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;

public class EggInIceBlockEntityRenderer<T extends EggInIceBlockEntity> implements BlockEntityRenderer<T> {
    public EggInIceBlockEntityRenderer(BlockEntityRendererFactory.Context context) {
    }

    @Override
    public void render(T egg, float partialTicks, MatrixStack matrixStackIn, VertexConsumerProvider bufferIn, int combinedLightIn, int combinedOverlayIn) {
        DragonEggModel model = new DragonEggModel();
        if (egg.type != null) {
            matrixStackIn.push();
            matrixStackIn.translate(0.5, -0.8F, 0.5F);
            matrixStackIn.push();
            model.renderFrozen(egg);
            model.render(matrixStackIn, bufferIn.getBuffer(PodiumBlockEntityRenderer.getEggTexture(egg.type)), combinedLightIn, combinedOverlayIn, -1);
            matrixStackIn.pop();
            matrixStackIn.pop();
        }
    }
}
