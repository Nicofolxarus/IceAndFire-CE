package com.iafenvoy.iceandfire.particle;

import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.ParticleTextureSheet;
import net.minecraft.client.particle.SpriteBillboardParticle;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.util.math.MathHelper;

public class SirenMusicParticle extends SpriteBillboardParticle {
    private float colorScale;

    protected SirenMusicParticle(ClientWorld world, double x, double y, double z, double motX, double motY, double motZ, SpriteProvider spriteProvider) {
        super(world, x, y, z, motX, motY, motZ);
        this.setPos(x, y, z);
        this.colorScale = (float) 1;
        this.red = Math.max(0, MathHelper.sin((this.colorScale + 0.0F) * 6.2831855F) * 0.65F + 0.35F);
        this.green = Math.max(0, MathHelper.sin((this.colorScale + 0.33333334F) * 6.2831855F) * 0.65F + 0.35F);
        this.blue = Math.max(0, MathHelper.sin((this.colorScale + 0.6666667F) * 6.2831855F) * 0.65F + 0.35F);
        this.setSprite(spriteProvider);
    }

    public static ParticleFactory<SimpleParticleType> factory(SpriteProvider spriteProvider) {
        return (parameters, world, x, y, z, velocityX, velocityY, velocityZ) -> new SirenMusicParticle(world, x, y, z, 1, 1, 1, spriteProvider);
    }

    @Override
    public void tick() {
        super.tick();
        this.colorScale += 0.015F;
        if (this.colorScale > 25) this.colorScale = 0;
        this.red = Math.max(0.0F, MathHelper.sin((this.colorScale + 0.0F) * 6.2831855F) * 0.65F + 0.35F);
        this.green = Math.max(0.0F, MathHelper.sin((this.colorScale + 0.33333334F) * 6.2831855F) * 0.65F + 0.35F);
        this.blue = Math.max(0.0F, MathHelper.sin((this.colorScale + 0.6666667F) * 6.2831855F) * 0.65F + 0.35F);
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