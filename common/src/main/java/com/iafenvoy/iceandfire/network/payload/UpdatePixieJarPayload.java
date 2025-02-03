package com.iafenvoy.iceandfire.network.payload;

import com.iafenvoy.iceandfire.IceAndFire;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public record UpdatePixieJarPayload(BlockPos blockPos, boolean isProducing) implements CustomPayload {
    private static final Identifier IDENTIFIER = Identifier.of(IceAndFire.MOD_ID, "update_pixie_jar");
    public static final Id<UpdatePixieJarPayload> ID = new Id<>(IDENTIFIER);
    public static final PacketCodec<ByteBuf, UpdatePixieJarPayload> CODEC = PacketCodecs.codec(RecordCodecBuilder.create(i -> i.group(
            BlockPos.CODEC.fieldOf("blockPos").forGetter(UpdatePixieJarPayload::blockPos),
            Codec.BOOL.fieldOf("isProducing").forGetter(UpdatePixieJarPayload::isProducing)
    ).apply(i, UpdatePixieJarPayload::new)));

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
