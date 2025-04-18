package com.iafenvoy.iceandfire.impl;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiConsumer;

@Environment(EnvType.CLIENT)
public class ParticleProviderHolder<T extends ParticleEffect> {
    private final ParticleType<T> type;
    @Nullable
    private final ParticleFactory<T> commonFactory;
    @Nullable
    private final ParticleManager.SpriteAwareFactory<T> extendedFactory;

    public ParticleProviderHolder(ParticleType<T> type, @NotNull ParticleFactory<T> factory) {
        this.type = type;
        this.commonFactory = factory;
        this.extendedFactory = null;
    }

    public ParticleProviderHolder(ParticleType<T> type, @NotNull ParticleManager.SpriteAwareFactory<T> factory) {
        this.type = type;
        this.commonFactory = null;
        this.extendedFactory = factory;
    }

    public void applyRegister(BiConsumer<ParticleType<T>, ParticleFactory<T>> common, BiConsumer<ParticleType<T>, ParticleManager.SpriteAwareFactory<T>> extended) {
        if (this.commonFactory != null) common.accept(this.type, this.commonFactory);
        if (this.extendedFactory != null) extended.accept(this.type, this.extendedFactory);
    }
}
