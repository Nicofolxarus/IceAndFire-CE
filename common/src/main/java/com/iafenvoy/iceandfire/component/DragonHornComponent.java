package com.iafenvoy.iceandfire.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Uuids;

import java.util.UUID;

public record DragonHornComponent(String entityType, UUID entityUuid, NbtCompound entityData) {
    public static final Codec<DragonHornComponent> CODEC = RecordCodecBuilder.create(i -> i.group(
            Codec.STRING.fieldOf("entityType").forGetter(DragonHornComponent::entityType),
            Uuids.CODEC.fieldOf("entityUuid").forGetter(DragonHornComponent::entityUuid),
            NbtCompound.CODEC.fieldOf("entityData").forGetter(DragonHornComponent::entityData)
    ).apply(i, DragonHornComponent::new));
}
