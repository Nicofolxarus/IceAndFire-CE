package com.iafenvoy.iceandfire.particle;

import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.ParticleTextureSheet;
import net.minecraft.client.particle.SpriteBillboardParticle;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;

public class BloodParticle extends SpriteBillboardParticle {
    protected BloodParticle(ClientWorld world, double x, double y, double z, SpriteProvider spriteProvider) {
        super(world, x, y, z, 0, Math.random() * (double) 0.2F + 0.1, 0);
        this.setPos(x, y, z);
        this.velocityY += 0.01D;
        this.setSprite(spriteProvider);
    }

    public static ParticleFactory<SimpleParticleType> factory(SpriteProvider spriteProvider) {
        return (parameters, world, x, y, z, velocityX, velocityY, velocityZ) -> new BloodParticle(world, x, y, z, spriteProvider);
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
