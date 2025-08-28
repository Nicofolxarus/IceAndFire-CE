package com.iafenvoy.iceandfire.render.entity;

import com.iafenvoy.iceandfire.entity.LightningDragonEntity;
import com.iafenvoy.iceandfire.render.misc.LightningBoltData;
import com.iafenvoy.iceandfire.render.misc.LightningRenderer;
import com.iafenvoy.uranus.client.model.TabulaModel;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Frustum;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

public class LightningDragonEntityRenderer extends DragonBaseEntityRenderer<LightningDragonEntity> {
    private final LightningRenderer lightningRenderer = new LightningRenderer();

    public LightningDragonEntityRenderer(EntityRendererFactory.Context context, TabulaModel<LightningDragonEntity> modelSupplier) {
        super(context, modelSupplier);
    }

    private static float getBoundedScale(float scale) {
        return (float) 0.5 + scale * ((float) 2 - (float) 0.5);
    }

    @Override
    public boolean shouldRender(LightningDragonEntity livingEntityIn, Frustum camera, double camX, double camY, double camZ) {
        if (super.shouldRender(livingEntityIn, camera, camX, camY, camZ)) return true;
        else {
            if (livingEntityIn.hasLightningTarget()) {
                Vec3d head = livingEntityIn.getHeadPosition();
                Vec3d target = new Vec3d(livingEntityIn.getLightningTargetX(), livingEntityIn.getLightningTargetY(), livingEntityIn.getLightningTargetZ());
                return camera.isVisible(new Box(head.x, head.y, head.z, target.x, target.y, target.z));
            }
            return false;
        }
    }

    @Override
    public void render(LightningDragonEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, VertexConsumerProvider bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        matrixStackIn.push();
        if (entityIn.hasLightningTarget()) {
            MinecraftClient client = MinecraftClient.getInstance();
            assert client.player != null;
            double dist = client.player.distanceTo(entityIn);
            if (dist <= Math.max(256, client.options.getViewDistance().getValue() * 16F)) {
                Vec3d Vector3d1 = entityIn.getHeadPosition();
                Vec3d Vector3d = new Vec3d(entityIn.getLightningTargetX(), entityIn.getLightningTargetY(), entityIn.getLightningTargetZ());
                float energyScale = 0.4F * entityIn.getScaleFactor();
                LightningBoltData bolt = new LightningBoltData(LightningBoltData.BoltRenderInfo.ELECTRICITY, Vector3d1, Vector3d, 15)
                        .size(0.05F * getBoundedScale(energyScale))
                        .lifespan(4)
                        .spawn(LightningBoltData.SpawnFunction.NO_DELAY);
                this.lightningRenderer.update(null, bolt, partialTicks);
                matrixStackIn.translate(-entityIn.getX(), -entityIn.getY(), -entityIn.getZ());
                this.lightningRenderer.render(partialTicks, matrixStackIn, bufferIn);
            }
        }
        matrixStackIn.pop();
    }
}
