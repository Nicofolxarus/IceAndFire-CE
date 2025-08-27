package com.iafenvoy.iceandfire.particle;

import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.ParticleTextureSheet;
import net.minecraft.client.particle.SpriteBillboardParticle;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;

public class DreadTorchParticle extends SpriteBillboardParticle {
    protected DreadTorchParticle(ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, SpriteProvider spriteProvider) {
        super(world, x, y, z, velocityX, velocityY, velocityZ);
        this.setPos(x, y, z);
        this.velocityY += 0.01D;
        this.setSprite(spriteProvider);
    }

    public static ParticleFactory<SimpleParticleType> factory(SpriteProvider spriteProvider) {
        return (parameters, world, x, y, z, velocityX, velocityY, velocityZ) -> new DreadTorchParticle(world, x, y, z, velocityX, velocityY, velocityZ, spriteProvider);
    }

    @Override
    public void buildGeometry(VertexConsumer consumer, Camera camera, float tickDelta) {
        this.scale = 0.125F * (this.maxAge - (this.age));
        this.scale = this.scale * 0.09F;
        this.velocityX *= 0.75D;
        this.velocityY *= 0.75D;
        this.velocityZ *= 0.75D;
        super.buildGeometry(consumer, camera, tickDelta);
    }

    @Override
    public int getBrightness(float tint) {
        return 240;
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_LIT;
    }
}
