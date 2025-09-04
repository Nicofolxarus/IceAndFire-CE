package com.iafenvoy.iceandfire.data;

import com.iafenvoy.iceandfire.IceAndFire;
import com.iafenvoy.iceandfire.entity.DragonBaseEntity;
import com.iafenvoy.iceandfire.registry.IafRegistries;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;

public record DragonType(String name, List<DragonColor> colors,
                         Supplier<EntityType<? extends DragonBaseEntity>> entityType, Supplier<Item> skullItem,
                         Supplier<Item> crystalItem, boolean piscivore) {
    public DragonType(String name, Supplier<EntityType<? extends DragonBaseEntity>> entityType, Supplier<Item> skullItem, Supplier<Item> crystalItem, boolean piscivore) {
        this(name, new LinkedList<>(), entityType, skullItem, crystalItem, piscivore);
    }

    public static String getNameFromInt(int type) {
        return IafRegistries.DRAGON_TYPE.get(type).name;
    }

    public static DragonType getTypeById(String id) {
        return IafRegistries.DRAGON_TYPE.get(IceAndFire.id(id));
    }

    public int getIntFromType() {
        return IafRegistries.DRAGON_TYPE.getRawId(this);
    }

    public EntityType<? extends DragonBaseEntity> getEntity() {
        return this.entityType.get();
    }

    public Identifier getSkeletonTexture(int stage) {
        return Identifier.of(IceAndFire.MOD_ID, String.format("textures/entity/%sdragon/%s_skeleton_%d.png", this.name, this.name, stage));
    }

    public Item getSkullItem() {
        return this.skullItem.get();
    }

    public Item getCrystalItem() {
        return this.crystalItem.get();
    }
}
