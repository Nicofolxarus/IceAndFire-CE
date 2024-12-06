package com.iafenvoy.iceandfire.particle;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;

public abstract class DragonParticleType<T extends DragonParticleType<T>> extends ParticleType<T> implements ParticleEffect {
    protected final float scale;

    public DragonParticleType(float scale, Factory<T> factory) {
        super(false, factory);
        this.scale = scale;
    }

    @Override
    public void write(PacketByteBuf buf) {
        buf.writeFloat(this.scale);
    }

    public float getScale() {
        return this.scale;
    }
}
