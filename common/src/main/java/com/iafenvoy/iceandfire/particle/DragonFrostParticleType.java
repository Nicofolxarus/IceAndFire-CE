package com.iafenvoy.iceandfire.particle;

import com.iafenvoy.iceandfire.registry.IafParticles;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.particle.ParticleType;

public class DragonFrostParticleType extends DragonParticleType<DragonFrostParticleType> {
    private static final MapCodec<DragonFrostParticleType> CODEC = RecordCodecBuilder.mapCodec(i -> i.group(
            Codec.FLOAT.fieldOf("scale").forGetter(DragonFrostParticleType::getScale)
    ).apply(i, DragonFrostParticleType::new));

    public DragonFrostParticleType() {
        this(1);
    }

    public DragonFrostParticleType(float scale) {
        super(scale);
    }

    @Override
    public ParticleType<?> getType() {
        return IafParticles.DRAGON_FROST.get();
    }

    @Override
    public MapCodec<DragonFrostParticleType> getCodec() {
        return CODEC;
    }

    @Override
    public PacketCodec<? super RegistryByteBuf, DragonFrostParticleType> getPacketCodec() {
        return PacketCodec.tuple(PacketCodecs.FLOAT, DragonFrostParticleType::getScale, DragonFrostParticleType::new);
    }
}
