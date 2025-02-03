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

public record UpdatePixieHousePayload(BlockPos blockPos, boolean hasPixie, int pixieType) implements CustomPayload {
    private static final Identifier IDENTIFIER = Identifier.of(IceAndFire.MOD_ID, "update_pixie_house");
    public static final Id<UpdatePixieHousePayload> ID = new Id<>(IDENTIFIER);
    public static final PacketCodec<ByteBuf, UpdatePixieHousePayload> CODEC = PacketCodecs.codec(RecordCodecBuilder.create(i -> i.group(
            BlockPos.CODEC.fieldOf("blockPos").forGetter(UpdatePixieHousePayload::blockPos),
            Codec.BOOL.fieldOf("hasPixie").forGetter(UpdatePixieHousePayload::hasPixie),
            Codec.INT.fieldOf("pixieType").forGetter(UpdatePixieHousePayload::pixieType)
    ).apply(i, UpdatePixieHousePayload::new)));

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
