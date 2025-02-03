package com.iafenvoy.iceandfire.particle;

import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;

public abstract class DragonParticleType<T extends DragonParticleType<T>> extends ParticleType<T> implements ParticleEffect {
    protected final float scale;

    public DragonParticleType(float scale) {
        super(false);
        this.scale = scale;
    }

    public float getScale() {
        return this.scale;
    }
}
