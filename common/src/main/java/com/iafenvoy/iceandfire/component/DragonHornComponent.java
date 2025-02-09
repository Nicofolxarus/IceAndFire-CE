package com.iafenvoy.iceandfire.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import net.minecraft.util.Uuids;

import java.util.UUID;

public record DragonHornComponent(Identifier entityType, UUID entityUuid, NbtCompound entityData) {
    public static final Codec<DragonHornComponent> CODEC = RecordCodecBuilder.create(i -> i.group(
            Identifier.CODEC.optionalFieldOf("entityType", Identifier.ofVanilla("empty")).forGetter(DragonHornComponent::entityType),
            Uuids.CODEC.optionalFieldOf("entityUuid", new UUID(0, 0)).forGetter(DragonHornComponent::entityUuid),
            NbtCompound.CODEC.optionalFieldOf("entityData", new NbtCompound()).forGetter(DragonHornComponent::entityData)
    ).apply(i, DragonHornComponent::new));
}
