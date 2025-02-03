package com.iafenvoy.iceandfire.network.payload;

import com.iafenvoy.iceandfire.IceAndFire;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record MyrmexSyncPayload(NbtCompound data) implements CustomPayload {
    private static final Identifier IDENTIFIER = Identifier.of(IceAndFire.MOD_ID, "myrmex_sync");
    public static final Id<MyrmexSyncPayload> ID = new Id<>(IDENTIFIER);
    public static final PacketCodec<ByteBuf, MyrmexSyncPayload> CODEC = PacketCodecs.codec(RecordCodecBuilder.create(i -> i.group(
            NbtCompound.CODEC.fieldOf("data").forGetter(MyrmexSyncPayload::data)
    ).apply(i, MyrmexSyncPayload::new)));

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
