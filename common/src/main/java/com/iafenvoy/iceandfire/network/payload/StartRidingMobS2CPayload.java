package com.iafenvoy.iceandfire.network.payload;

import com.iafenvoy.iceandfire.IceAndFire;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record StartRidingMobS2CPayload(int dragonId, boolean ride, boolean baby) implements CustomPayload {
    private static final Identifier IDENTIFIER = Identifier.of(IceAndFire.MOD_ID, "start_riding_mob_s2c");
    public static final Id<StartRidingMobS2CPayload> ID = new Id<>(IDENTIFIER);
    public static final PacketCodec<ByteBuf, StartRidingMobS2CPayload> CODEC = PacketCodecs.codec(RecordCodecBuilder.create(i -> i.group(
            Codec.INT.fieldOf("dragonId").forGetter(StartRidingMobS2CPayload::dragonId),
            Codec.BOOL.fieldOf("ride").forGetter(StartRidingMobS2CPayload::ride),
            Codec.BOOL.fieldOf("baby").forGetter(StartRidingMobS2CPayload::baby)
    ).apply(i, StartRidingMobS2CPayload::new)));

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
