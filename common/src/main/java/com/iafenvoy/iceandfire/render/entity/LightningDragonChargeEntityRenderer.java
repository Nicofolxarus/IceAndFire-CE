package com.iafenvoy.iceandfire.render.entity;

import com.iafenvoy.iceandfire.IceAndFire;
import com.iafenvoy.iceandfire.entity.LightningDragonChargeEntity;
import com.iafenvoy.iceandfire.render.model.DreadLichSkullModel;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;

public class LightningDragonChargeEntityRenderer extends EntityRenderer<LightningDragonChargeEntity> {
    public static final Identifier TEXTURE = Identifier.of(IceAndFire.MOD_ID, "textures/entity/lightningdragon/charge.png");
    public static final Identifier TEXTURE_CORE = Identifier.of(IceAndFire.MOD_ID, "textures/entity/lightningdragon/charge_core.png");
    private static final DreadLichSkullModel MODEL_SPIRIT = new DreadLichSkullModel();

    public LightningDragonChargeEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public void render(LightningDragonChargeEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStackIn, VertexConsumerProvider bufferIn, int packedLightIn) {
        float f = (float) entity.age + partialTicks;
        float yaw = entity.prevYaw + (entity.getYaw() - entity.prevYaw) * partialTicks;

        VertexConsumer ivertexbuilder2 = bufferIn.getBuffer(RenderLayer.getEyes(TEXTURE_CORE));
        matrixStackIn.push();
        matrixStackIn.translate(0F, 0.5F, 0F);
        matrixStackIn.translate(0F, -0.25F, 0F);
        matrixStackIn.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(yaw - 180.0F));
        matrixStackIn.multiply(RotationAxis.POSITIVE_X.rotationDegrees(f * 20.0F));
        matrixStackIn.translate(0F, 0.25F, 0F);
        MODEL_SPIRIT.render(matrixStackIn, ivertexbuilder2, packedLightIn, OverlayTexture.DEFAULT_UV, -1);
        matrixStackIn.pop();

        VertexConsumer ivertexbuilder = bufferIn.getBuffer(RenderLayer.getEnergySwirl(TEXTURE, f * 0.01F, f * 0.01F));
        matrixStackIn.push();
        matrixStackIn.translate(0F, 0.5F, 0F);
        matrixStackIn.translate(0F, -0.25F, 0F);
        matrixStackIn.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(yaw - 180.0F));
        matrixStackIn.multiply(RotationAxis.POSITIVE_X.rotationDegrees(f * 15.0F));
        matrixStackIn.translate(0F, 0.25F, 0F);
        matrixStackIn.scale(1.5F, 1.5F, 1.5F);
        MODEL_SPIRIT.render(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.DEFAULT_UV, -1);
        matrixStackIn.pop();

        matrixStackIn.push();
        matrixStackIn.translate(0F, 0.75F, 0F);
        matrixStackIn.translate(0F, -0.25F, 0F);
        matrixStackIn.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(yaw - 180.0F));
        matrixStackIn.multiply(RotationAxis.POSITIVE_X.rotationDegrees(f * 10.0F));
        matrixStackIn.translate(0F, 0.75F, 0F);
        matrixStackIn.scale(2.5F, 2.5F, 2.5F);
        MODEL_SPIRIT.render(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.DEFAULT_UV, -1);
        matrixStackIn.pop();

        super.render(entity, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    @Override
    public Identifier getTexture(LightningDragonChargeEntity entity) {
        return TEXTURE;
    }
}
