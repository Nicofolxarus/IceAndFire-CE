package com.iafenvoy.iceandfire.network.payload;

import com.iafenvoy.iceandfire.IceAndFire;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.minecraft.util.Uuids;

import java.util.UUID;

public record MultipartInteractPayload(UUID creatureID, float dmg) implements CustomPayload {
    private static final Identifier IDENTIFIER = Identifier.of(IceAndFire.MOD_ID, "multipart_interact");
    public static final Id<MultipartInteractPayload> ID = new Id<>(IDENTIFIER);
    public static final PacketCodec<ByteBuf, MultipartInteractPayload> CODEC = PacketCodecs.codec(RecordCodecBuilder.create(i -> i.group(
            Uuids.CODEC.fieldOf("blockPos").forGetter(MultipartInteractPayload::creatureID),
            Codec.FLOAT.fieldOf("isProducing").forGetter(MultipartInteractPayload::dmg)
    ).apply(i, MultipartInteractPayload::new)));

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
