package com.iafenvoy.iceandfire.particle;

import com.iafenvoy.iceandfire.registry.IafParticles;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.particle.ParticleType;

public class DragonFrostParticleType extends DragonParticleType<DragonFrostParticleType> {
    private static final Factory<DragonFrostParticleType> FACTORY = new Factory<>() {
        @Override
        public DragonFrostParticleType read(ParticleType<DragonFrostParticleType> type, StringReader reader) throws CommandSyntaxException {
            float scale = reader.readFloat();
            return new DragonFrostParticleType(scale);
        }

        @Override
        public DragonFrostParticleType read(ParticleType<DragonFrostParticleType> type, PacketByteBuf buf) {
            return new DragonFrostParticleType(buf.readFloat());
        }
    };
    private static final Codec<DragonFrostParticleType> CODEC = RecordCodecBuilder.create(i -> i.group(Codec.FLOAT.fieldOf("scale").forGetter(DragonFrostParticleType::getScale)).apply(i, DragonFrostParticleType::new));

    public DragonFrostParticleType() {
        this(1);
    }

    public DragonFrostParticleType(float scale) {
        super(scale, FACTORY);
    }

    @Override
    public ParticleType<?> getType() {
        return IafParticles.DRAGON_FROST.get();
    }

    @Override
    public String asString() {
        return "dragon_frost_type";
    }

    @Override
    public Codec<DragonFrostParticleType> getCodec() {
        return CODEC;
    }
}
