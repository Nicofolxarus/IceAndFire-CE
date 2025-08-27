package com.iafenvoy.iceandfire.particle;

import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.ParticleTextureSheet;
import net.minecraft.client.particle.SpriteBillboardParticle;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.util.math.MathHelper;

public class HydraBreathParticle extends SpriteBillboardParticle {
    private final float newScale;

    protected HydraBreathParticle(ClientWorld world, double x, double y, double z, SpriteProvider spriteProvider) {
        super(world, x, y, z, 0, 0, 0);
        this.velocityX *= 0.1;
        this.velocityY *= 0.1;
        this.velocityZ *= 0.1;
        this.newScale = this.scale;
        this.setSprite(spriteProvider);
    }

    public static ParticleFactory<SimpleParticleType> factory(SpriteProvider spriteProvider) {
        return (parameters, world, x, y, z, velocityX, velocityY, velocityZ) -> new HydraBreathParticle(world, x, y, z, spriteProvider);
    }

    @Override
    public void buildGeometry(VertexConsumer consumer, Camera camera, float tickDelta) {
        float scaley = ((float) this.age + tickDelta) / (float) this.maxAge * 32.0F;
        scaley = MathHelper.clamp(scaley, 0.0F, 1.0F);
        this.scale = this.newScale * scaley;
        super.buildGeometry(consumer, camera, tickDelta);
    }

    @Override
    public int getBrightness(float partialTick) {
        return super.getBrightness(partialTick);
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_LIT;
    }
}
