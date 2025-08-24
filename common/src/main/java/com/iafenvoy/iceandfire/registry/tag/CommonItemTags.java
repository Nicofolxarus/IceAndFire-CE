package com.iafenvoy.iceandfire.registry.tag;

import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class CommonItemTags {
    public static final TagKey<Item> STRINGS = create("strings");
    public static final TagKey<Item> INGOTS_SILVER = create("ingots/silver");

    private static TagKey<Item> create(String name) {
        return TagKey.of(RegistryKeys.ITEM, Identifier.of("c", name));
    }
}
