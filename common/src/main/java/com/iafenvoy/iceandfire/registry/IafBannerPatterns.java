package com.iafenvoy.iceandfire.registry;

import com.iafenvoy.iceandfire.IceAndFire;
import net.minecraft.block.entity.BannerPattern;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public final class IafBannerPatterns {
    public static final RegistryKey<BannerPattern> PATTERN_DREAD = RegistryKey.of(RegistryKeys.BANNER_PATTERN, Identifier.of(IceAndFire.MOD_ID, "dread"));
}
