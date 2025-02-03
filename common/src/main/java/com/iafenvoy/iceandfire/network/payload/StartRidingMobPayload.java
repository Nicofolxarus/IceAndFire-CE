package com.iafenvoy.iceandfire.network.payload;

import com.iafenvoy.iceandfire.IceAndFire;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record StartRidingMobPayload(int dragonId, boolean ride, boolean baby) implements CustomPayload {
    private static final Identifier IDENTIFIER = Identifier.of(IceAndFire.MOD_ID, "start_riding_mob");
    public static final Id<StartRidingMobPayload> ID = new Id<>(IDENTIFIER);
    public static final PacketCodec<ByteBuf, StartRidingMobPayload> CODEC = PacketCodecs.codec(RecordCodecBuilder.create(i -> i.group(
            Codec.INT.fieldOf("dragonId").forGetter(StartRidingMobPayload::dragonId),
            Codec.BOOL.fieldOf("ride").forGetter(StartRidingMobPayload::ride),
            Codec.BOOL.fieldOf("baby").forGetter(StartRidingMobPayload::baby)
    ).apply(i, StartRidingMobPayload::new)));

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
