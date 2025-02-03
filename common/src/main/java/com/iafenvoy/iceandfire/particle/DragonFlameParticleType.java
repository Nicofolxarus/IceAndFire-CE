package com.iafenvoy.iceandfire.particle;

import com.iafenvoy.iceandfire.registry.IafParticles;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.particle.ParticleType;

public class DragonFlameParticleType extends DragonParticleType<DragonFlameParticleType> {
    private static final MapCodec<DragonFlameParticleType> CODEC = RecordCodecBuilder.mapCodec(i -> i.group(
            Codec.FLOAT.fieldOf("scale").forGetter(DragonFlameParticleType::getScale)
    ).apply(i, DragonFlameParticleType::new));

    public DragonFlameParticleType() {
        this(1);
    }

    public DragonFlameParticleType(float scale) {
        super(scale);
    }

    @Override
    public ParticleType<?> getType() {
        return IafParticles.DRAGON_FLAME.get();
    }

    @Override
    public MapCodec<DragonFlameParticleType> getCodec() {
        return CODEC;
    }

    @Override
    public PacketCodec<? super RegistryByteBuf, DragonFlameParticleType> getPacketCodec() {
        return PacketCodec.tuple(PacketCodecs.FLOAT, DragonFlameParticleType::getScale, DragonFlameParticleType::new);
    }
}
