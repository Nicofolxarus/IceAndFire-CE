package com.iafenvoy.iceandfire.registry.tag;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public final class CommonTags {
    public static class Blocks {
        public static final TagKey<Block> COBBLESTONES = create("cobblestones");
        public static final TagKey<Block> GRAVELS = create("gravels");
        public static final TagKey<Block> STONES = create("stones");

        private static TagKey<Block> create(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of("c", name));
        }
    }

    public static class Items {
        public static final TagKey<Item> STRINGS = create("strings");
        public static final TagKey<Item> INGOTS_SILVER = create("ingots/silver");

        private static TagKey<Item> create(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of("c", name));
        }
    }
}
