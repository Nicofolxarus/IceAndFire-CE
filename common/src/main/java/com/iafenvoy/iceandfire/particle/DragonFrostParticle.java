package com.iafenvoy.iceandfire.particle;

import com.iafenvoy.uranus.object.VecUtil;
import com.iafenvoy.uranus.util.RandomHelper;
import net.minecraft.block.BlockState;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import org.jetbrains.annotations.NotNull;

public class DragonFrostParticle extends SpriteBillboardParticle {
    protected DragonFrostParticle(DragonFrostParticleType parameters, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, SpriteProvider spriteProvider) {
        super(world, x, y, z);
        float size = parameters.getScale();
        this.scale *= (float) RandomHelper.nextDouble(size, size * 2);
        this.maxAge = 30;
        this.gravityStrength = 0.0F;
        this.collidesWithWorld = false;
        this.setSprite(spriteProvider);
        this.setVelocity(RandomHelper.randomize(velocityX, 0.5), RandomHelper.randomize(velocityY, 0.5), RandomHelper.randomize(velocityZ, 0.5));
    }

    public static ParticleFactory<DragonFrostParticleType> factory(SpriteProvider spriteProvider) {
        return (parameters, world, x, y, z, velocityX, velocityY, velocityZ) -> new DragonFrostParticle(parameters, world, x, y, z, velocityX, velocityY, velocityZ, spriteProvider);
    }

    @Override
    public int getBrightness(float partialTick) {
        int i = super.getBrightness(partialTick);
        int j = i & 255;
        int k = i >> 16 & 255;
        if (j > 240) j = 240;
        return j | k << 16;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void tick() {
        super.tick();
        BlockState state = this.world.getBlockState(VecUtil.createBlockPos(this.x, this.y, this.z));
        if (state != null && state.isSolid())
            this.markDead();
    }

    @Override
    public @NotNull ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_LIT;
    }

    protected record Provider(SpriteProvider spriteProvider) implements ParticleFactory<DragonFrostParticleType> {
        @Override
        public Particle createParticle(DragonFrostParticleType typeIn, ClientWorld worldIn, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
            return new DragonFrostParticle(typeIn, worldIn, x, y, z, velocityX, velocityY, velocityZ, this.spriteProvider);
        }
    }
}
