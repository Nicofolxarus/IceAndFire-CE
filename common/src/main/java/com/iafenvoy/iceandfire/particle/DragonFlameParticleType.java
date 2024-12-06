package com.iafenvoy.iceandfire.particle;

import com.iafenvoy.iceandfire.registry.IafParticles;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.particle.ParticleType;

public class DragonFlameParticleType extends DragonParticleType<DragonFlameParticleType> {
    private static final Factory<DragonFlameParticleType> FACTORY = new Factory<>() {
        @Override
        public DragonFlameParticleType read(ParticleType<DragonFlameParticleType> type, StringReader reader) throws CommandSyntaxException {
            float scale = reader.readFloat();
            return new DragonFlameParticleType(scale);
        }

        @Override
        public DragonFlameParticleType read(ParticleType<DragonFlameParticleType> type, PacketByteBuf buf) {
            return new DragonFlameParticleType(buf.readFloat());
        }
    };
    private static final Codec<DragonFlameParticleType> CODEC = RecordCodecBuilder.create(i -> i.group(Codec.FLOAT.fieldOf("scale").forGetter(DragonFlameParticleType::getScale)).apply(i, DragonFlameParticleType::new));

    public DragonFlameParticleType() {
        this(1);
    }

    public DragonFlameParticleType(float scale) {
        super(scale, FACTORY);
    }

    @Override
    public ParticleType<?> getType() {
        return IafParticles.DRAGON_FLAME.get();
    }

    @Override
    public String asString() {
        return "dragon_flame_type";
    }

    @Override
    public Codec<DragonFlameParticleType> getCodec() {
        return CODEC;
    }
}
