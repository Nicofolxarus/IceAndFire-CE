package com.iafenvoy.iceandfire.network.payload;

import com.iafenvoy.iceandfire.IceAndFire;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record PlayerHitMultipartC2SPayload(int entityId, int index) implements CustomPayload {
    private static final Identifier IDENTIFIER = Identifier.of(IceAndFire.MOD_ID, "player_hit_multipart");
    public static final Id<PlayerHitMultipartC2SPayload> ID = new Id<>(IDENTIFIER);
    public static final PacketCodec<ByteBuf, PlayerHitMultipartC2SPayload> CODEC = PacketCodecs.codec(RecordCodecBuilder.create(i -> i.group(
            Codec.INT.fieldOf("blockPos").forGetter(PlayerHitMultipartC2SPayload::entityId),
            Codec.INT.fieldOf("isProducing").forGetter(PlayerHitMultipartC2SPayload::index)
    ).apply(i, PlayerHitMultipartC2SPayload::new)));

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
