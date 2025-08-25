package com.iafenvoy.iceandfire.network.payload;

import com.iafenvoy.iceandfire.IceAndFire;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;
import net.minecraft.util.math.Vec3d;

import java.util.List;

public record LightningBoltS2CPayload(List<Pair<Vec3d, Vec3d>> lightnings) implements CustomPayload {
    private static final Identifier IDENTIFIER = Identifier.of(IceAndFire.MOD_ID, "lightning_bolt_s2c");
    public static final Id<LightningBoltS2CPayload> ID = new Id<>(IDENTIFIER);
    public static final PacketCodec<ByteBuf, LightningBoltS2CPayload> CODEC = PacketCodecs.codec(RecordCodecBuilder.create(i -> i.group(
            RecordCodecBuilder.<Pair<Vec3d, Vec3d>>create(i1 -> i1.group(
                    Vec3d.CODEC.fieldOf("left").forGetter(Pair::getLeft),
                    Vec3d.CODEC.fieldOf("right").forGetter(Pair::getRight)
            ).apply(i1, Pair::new)).listOf().fieldOf("lightnings").forGetter(LightningBoltS2CPayload::lightnings)
    ).apply(i, LightningBoltS2CPayload::new)));

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
