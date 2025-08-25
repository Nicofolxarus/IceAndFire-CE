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

public record UpdatePixieJarS2CPayload(BlockPos blockPos, boolean isProducing) implements CustomPayload {
    private static final Identifier IDENTIFIER = Identifier.of(IceAndFire.MOD_ID, "update_pixie_jar");
    public static final Id<UpdatePixieJarS2CPayload> ID = new Id<>(IDENTIFIER);
    public static final PacketCodec<ByteBuf, UpdatePixieJarS2CPayload> CODEC = PacketCodecs.codec(RecordCodecBuilder.create(i -> i.group(
            BlockPos.CODEC.fieldOf("blockPos").forGetter(UpdatePixieJarS2CPayload::blockPos),
            Codec.BOOL.fieldOf("isProducing").forGetter(UpdatePixieJarS2CPayload::isProducing)
    ).apply(i, UpdatePixieJarS2CPayload::new)));

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
