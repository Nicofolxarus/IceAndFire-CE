package com.iafenvoy.iceandfire.particle;

import com.iafenvoy.iceandfire.entity.GhostEntity;
import com.iafenvoy.iceandfire.registry.IafRenderLayers;
import com.iafenvoy.iceandfire.render.entity.GhostEntityRenderer;
import com.iafenvoy.iceandfire.render.model.GhostModel;
import com.iafenvoy.iceandfire.util.Color4i;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.Perspective;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.ParticleTextureSheet;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

public class GhostAppearanceParticle extends Particle {
    private final GhostModel model = new GhostModel(0.0F);
    private final int ghost;
    private final boolean fromLeft;

    protected GhostAppearanceParticle(ClientWorld world, double x, double y, double z, int ghost) {
        super(world, x, y, z);
        this.gravityStrength = 0.0F;
        this.maxAge = 15;
        this.ghost = ghost;
        this.fromLeft = world.random.nextBoolean();
    }

    public static ParticleFactory<SimpleParticleType> factory() {
        return (parameters, world, x, y, z, velocityX, velocityY, velocityZ) -> new GhostAppearanceParticle(world, x, y, z, 1);
    }

    @Override
    public void buildGeometry(VertexConsumer consumer, Camera camera, float tickDelta) {
        float f = (this.age + tickDelta) / this.maxAge;
        float f1 = 0.05F + 0.5F * MathHelper.sin(f * (float) Math.PI);
        Entity entity = this.world.getEntityById(this.ghost);
        if (entity instanceof GhostEntity ghostEntity && MinecraftClient.getInstance().options.getPerspective() == Perspective.FIRST_PERSON) {
            MatrixStack matrixstack = new MatrixStack();
            matrixstack.multiply(camera.getRotation());
            if (this.fromLeft) {
                matrixstack.multiply(RotationAxis.NEGATIVE_Y.rotationDegrees(150 * f - 60));
                matrixstack.multiply(RotationAxis.NEGATIVE_Z.rotationDegrees(150 * f - 60));
            } else {
                matrixstack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(150 * f - 60));
                matrixstack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(150 * f - 60));
            }
            matrixstack.scale(-1.0F, -1.0F, 1.0F);
            matrixstack.translate(0.0D, 0.3F, 1.25D);
            VertexConsumerProvider.Immediate immediate = MinecraftClient.getInstance().getBufferBuilders().getEntityVertexConsumers();
            VertexConsumer consumer1 = immediate.getBuffer(IafRenderLayers.getGhost(GhostEntityRenderer.getGhostOverlayForType(ghostEntity.getColor())));
            this.model.setAngles(ghostEntity, 0, 0, entity.age + tickDelta, 0, 0);
            this.model.render(matrixstack, consumer1, 240, OverlayTexture.DEFAULT_UV, new Color4i(1.0F, 1.0F, 1.0F, f1).getIntValue());
            immediate.draw();
        }
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.CUSTOM;
    }
}

