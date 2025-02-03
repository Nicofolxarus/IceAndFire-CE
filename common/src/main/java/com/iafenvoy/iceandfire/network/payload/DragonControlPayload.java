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

public record DragonControlPayload(int dragonId, byte controlState, BlockPos pos) implements CustomPayload {
    private static final Identifier IDENTIFIER = Identifier.of(IceAndFire.MOD_ID, "dragon_control");
    public static final Id<DragonControlPayload> ID = new Id<>(IDENTIFIER);
    public static final PacketCodec<ByteBuf, DragonControlPayload> CODEC = PacketCodecs.codec(RecordCodecBuilder.create(i -> i.group(
            Codec.INT.fieldOf("dragonId").forGetter(DragonControlPayload::dragonId),
            Codec.BYTE.fieldOf("controlState").forGetter(DragonControlPayload::controlState),
            BlockPos.CODEC.fieldOf("pos").forGetter(DragonControlPayload::pos)
    ).apply(i, DragonControlPayload::new)));

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
