package com.iafenvoy.iceandfire.network.payload;

import com.iafenvoy.iceandfire.IceAndFire;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record PlayerHitMultipartPayload(int entityId, int index) implements CustomPayload {
    private static final Identifier IDENTIFIER = Identifier.of(IceAndFire.MOD_ID, "player_hit_multipart");
    public static final Id<PlayerHitMultipartPayload> ID = new Id<>(IDENTIFIER);
    public static final PacketCodec<ByteBuf, PlayerHitMultipartPayload> CODEC = PacketCodecs.codec(RecordCodecBuilder.create(i -> i.group(
            Codec.INT.fieldOf("blockPos").forGetter(PlayerHitMultipartPayload::entityId),
            Codec.INT.fieldOf("isProducing").forGetter(PlayerHitMultipartPayload::index)
    ).apply(i, PlayerHitMultipartPayload::new)));

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
