package com.iafenvoy.iceandfire.network.payload;

import com.iafenvoy.iceandfire.IceAndFire;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record StartRidingMobC2SPayload(int dragonId, boolean ride, boolean baby) implements CustomPayload {
    private static final Identifier IDENTIFIER = Identifier.of(IceAndFire.MOD_ID, "start_riding_mob_c2s");
    public static final Id<StartRidingMobC2SPayload> ID = new Id<>(IDENTIFIER);
    public static final PacketCodec<ByteBuf, StartRidingMobC2SPayload> CODEC = PacketCodecs.codec(RecordCodecBuilder.create(i -> i.group(
            Codec.INT.fieldOf("dragonId").forGetter(StartRidingMobC2SPayload::dragonId),
            Codec.BOOL.fieldOf("ride").forGetter(StartRidingMobC2SPayload::ride),
            Codec.BOOL.fieldOf("baby").forGetter(StartRidingMobC2SPayload::baby)
    ).apply(i, StartRidingMobC2SPayload::new)));

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
