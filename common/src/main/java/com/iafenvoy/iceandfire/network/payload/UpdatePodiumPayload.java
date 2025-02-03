package com.iafenvoy.iceandfire.network.payload;

import com.iafenvoy.iceandfire.IceAndFire;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.item.ItemStack;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public record UpdatePodiumPayload(BlockPos blockPos, ItemStack heldStack) implements CustomPayload {
    private static final Identifier IDENTIFIER = Identifier.of(IceAndFire.MOD_ID, "update_podium");
    public static final Id<UpdatePodiumPayload> ID = new Id<>(IDENTIFIER);
    public static final PacketCodec<ByteBuf, UpdatePodiumPayload> CODEC = PacketCodecs.codec(RecordCodecBuilder.create(i -> i.group(
            BlockPos.CODEC.fieldOf("blockPos").forGetter(UpdatePodiumPayload::blockPos),
            ItemStack.OPTIONAL_CODEC.fieldOf("heldStack").forGetter(UpdatePodiumPayload::heldStack)
    ).apply(i, UpdatePodiumPayload::new)));

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
