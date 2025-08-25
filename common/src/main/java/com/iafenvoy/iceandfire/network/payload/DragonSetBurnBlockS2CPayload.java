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

public record DragonSetBurnBlockS2CPayload(int entityId, boolean breathing, BlockPos target) implements CustomPayload {
    private static final Identifier IDENTIFIER = Identifier.of(IceAndFire.MOD_ID, "dragon_set_burn_block");
    public static final Id<DragonSetBurnBlockS2CPayload> ID = new Id<>(IDENTIFIER);
    public static final PacketCodec<ByteBuf, DragonSetBurnBlockS2CPayload> CODEC = PacketCodecs.codec(RecordCodecBuilder.create(i -> i.group(
            Codec.INT.fieldOf("entityId").forGetter(DragonSetBurnBlockS2CPayload::entityId),
            Codec.BOOL.fieldOf("breathing").forGetter(DragonSetBurnBlockS2CPayload::breathing),
            BlockPos.CODEC.fieldOf("target").forGetter(DragonSetBurnBlockS2CPayload::target)
    ).apply(i, DragonSetBurnBlockS2CPayload::new)));

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
