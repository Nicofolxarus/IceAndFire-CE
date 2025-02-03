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

public record DragonSetBurnBlockPayload(int entityId, boolean breathing, BlockPos target) implements CustomPayload {
    private static final Identifier IDENTIFIER = Identifier.of(IceAndFire.MOD_ID, "dragon_set_burn_block");
    public static final Id<DragonSetBurnBlockPayload> ID = new Id<>(IDENTIFIER);
    public static final PacketCodec<ByteBuf, DragonSetBurnBlockPayload> CODEC = PacketCodecs.codec(RecordCodecBuilder.create(i -> i.group(
            Codec.INT.fieldOf("entityId").forGetter(DragonSetBurnBlockPayload::entityId),
            Codec.BOOL.fieldOf("breathing").forGetter(DragonSetBurnBlockPayload::breathing),
            BlockPos.CODEC.fieldOf("target").forGetter(DragonSetBurnBlockPayload::target)
    ).apply(i, DragonSetBurnBlockPayload::new)));

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
