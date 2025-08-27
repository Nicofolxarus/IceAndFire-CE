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

public class PixieDustParticle extends SpriteBillboardParticle {
    private final float newScale;

    protected PixieDustParticle(ClientWorld world, double x, double y, double z, float scale, float red, float green, float blue, SpriteProvider spriteProvider) {
        super(world, x, y, z, 0.0D, 0.0D, 0.0D);
        this.velocityX *= 0.1;
        this.velocityY *= 0.1;
        this.velocityZ *= 0.1;
        float f = (float) Math.random() * 0.4F + 0.6F;
        this.red = ((float) (Math.random() * 0.2) + 0.8F) * red * f;
        this.green = ((float) (Math.random() * 0.2) + 0.8F) * green * f;
        this.blue = ((float) (Math.random() * 0.2) + 0.8F) * blue * f;
        this.scale *= scale;
        this.newScale = this.scale;
        this.maxAge = (int) (this.maxAge * scale);
        this.setSprite(spriteProvider);
    }

    public static ParticleFactory<SimpleParticleType> factory(SpriteProvider spriteProvider) {
        return (parameters, world, x, y, z, velocityX, velocityY, velocityZ) -> new PixieDustParticle(world, x, y, z, 1, 1, 1, 1, spriteProvider);
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
        return 240;
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_LIT;
    }
}
