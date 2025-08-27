package com.iafenvoy.iceandfire.particle;

import com.iafenvoy.iceandfire.render.entity.SirenEntityRenderer;
import com.iafenvoy.iceandfire.render.model.SirenModel;
import com.iafenvoy.iceandfire.util.Color4i;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.Model;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.ParticleTextureSheet;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

public class SirenAppearanceParticle extends Particle {
    private final Model model = new SirenModel();
    private final int sirenType;

    protected SirenAppearanceParticle(ClientWorld worldIn, double x, double y, double z, int sirenType) {
        super(worldIn, x, y, z);
        this.gravityStrength = 0.0F;
        this.maxAge = 30;
        this.sirenType = sirenType;
    }

    public static ParticleFactory<SimpleParticleType> factory() {
        return (parameters, world, x, y, z, velocityX, velocityY, velocityZ) -> new SirenAppearanceParticle(world, x, y, z, 1);
    }

    @Override
    public void buildGeometry(VertexConsumer consumer, Camera camera, float tickDelta) {
        float f = ((float) this.age + tickDelta) / (float) this.maxAge;
        float f1 = 0.05F + 0.5F * MathHelper.sin(f * (float) Math.PI);
        MatrixStack matrixstack = new MatrixStack();
        matrixstack.multiply(camera.getRotation());
        matrixstack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(150 * f - 60));
        matrixstack.scale(-1.0F, -1.0F, 1.0F);
        matrixstack.translate(0.0D, -1.101F, 1.5D);
        VertexConsumerProvider.Immediate immediate = MinecraftClient.getInstance().getBufferBuilders().getEntityVertexConsumers();
        VertexConsumer consumer1 = immediate.getBuffer(RenderLayer.getEntityTranslucent(SirenEntityRenderer.getSirenOverlayTexture(this.sirenType)));
        this.model.render(matrixstack, consumer1, 15728880, OverlayTexture.DEFAULT_UV, new Color4i(1.0F, 1.0F, 1.0F, f1).getIntValue());
        immediate.draw();
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.CUSTOM;
    }
}

