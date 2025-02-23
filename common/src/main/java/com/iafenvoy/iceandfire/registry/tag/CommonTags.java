package com.iafenvoy.iceandfire.registry.tag;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public final class CommonTags {
    public static class Blocks {
        public static final TagKey<Block> COBBLESTONES = tag("cobblestones");
        public static final TagKey<Block> GRAVELS = tag("gravels");
        public static final TagKey<Block> STONES = tag("stones");

        private static TagKey<Block> tag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of("c", name));
        }
    }

    public static class Items {
        public static final TagKey<Item> STRINGS = tag("strings");

        private static TagKey<Item> tag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of("c", name));
        }
    }
}
