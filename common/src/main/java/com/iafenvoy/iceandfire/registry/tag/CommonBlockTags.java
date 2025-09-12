package com.iafenvoy.iceandfire.registry.tag;

import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public final class CommonBlockTags {
    public static final TagKey<Block> COBBLESTONES = create("cobblestones");
    public static final TagKey<Block> GRAVELS = create("gravels");
    public static final TagKey<Block> STONES = create("stones");

    private static TagKey<Block> create(String name) {
        return TagKey.of(RegistryKeys.BLOCK, Identifier.of("c", name));
    }
}
