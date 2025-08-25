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

public record UpdatePixieHouseS2CPayload(BlockPos blockPos, boolean hasPixie, int pixieType) implements CustomPayload {
    private static final Identifier IDENTIFIER = Identifier.of(IceAndFire.MOD_ID, "update_pixie_house");
    public static final Id<UpdatePixieHouseS2CPayload> ID = new Id<>(IDENTIFIER);
    public static final PacketCodec<ByteBuf, UpdatePixieHouseS2CPayload> CODEC = PacketCodecs.codec(RecordCodecBuilder.create(i -> i.group(
            BlockPos.CODEC.fieldOf("blockPos").forGetter(UpdatePixieHouseS2CPayload::blockPos),
            Codec.BOOL.fieldOf("hasPixie").forGetter(UpdatePixieHouseS2CPayload::hasPixie),
            Codec.INT.fieldOf("pixieType").forGetter(UpdatePixieHouseS2CPayload::pixieType)
    ).apply(i, UpdatePixieHouseS2CPayload::new)));

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
