package com.iafenvoy.iceandfire.registry;

import com.iafenvoy.iceandfire.IceAndFire;
import com.iafenvoy.iceandfire.data.DragonType;
import com.iafenvoy.iceandfire.entity.DragonBaseEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.function.Supplier;

public final class IafDragonTypes {
    public static final DragonType FIRE = register("fire", IafEntities.FIRE_DRAGON::get, IafItems.DRAGON_SKULL_FIRE, IafItems.SUMMONING_CRYSTAL_FIRE, false);
    public static final DragonType ICE = register("ice", IafEntities.ICE_DRAGON::get, IafItems.DRAGON_SKULL_ICE, IafItems.SUMMONING_CRYSTAL_ICE, true);
    public static final DragonType LIGHTNING = register("lightning", IafEntities.LIGHTNING_DRAGON::get, IafItems.DRAGON_SKULL_LIGHTNING, IafItems.SUMMONING_CRYSTAL_LIGHTNING, false);

    private static DragonType register(String name, Supplier<EntityType<? extends DragonBaseEntity>> entityType, Supplier<Item> skullItem, Supplier<Item> crystalItem, boolean piscivore) {
        return Registry.register(IafRegistries.DRAGON_TYPE, Identifier.of(IceAndFire.MOD_ID, name), new DragonType(name, entityType, skullItem, crystalItem, piscivore));
    }

    public static void init() {
    }
}
