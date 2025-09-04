package com.iafenvoy.iceandfire.registry;

import com.iafenvoy.iceandfire.data.*;
import com.mojang.serialization.Lifecycle;
import net.minecraft.registry.DefaultedRegistry;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.SimpleDefaultedRegistry;

public final class IafRegistries {
    public static final DefaultedRegistry<BestiaryPage> BESTIARY_PAGE = create("introduction", IafRegistryKeys.BESTIARY_PAGE);
    public static final DefaultedRegistry<DragonColor> DRAGON_COLOR = create("red", IafRegistryKeys.DRAGON_COLOR);
    public static final DefaultedRegistry<DragonType> DRAGON_TYPE = create("fire", IafRegistryKeys.DRAGON_TYPE);
    public static final DefaultedRegistry<HippogryphType> HIPPOGRYPH_TYPE = create("black", IafRegistryKeys.HIPPOGRYPH_TYPE);
    public static final DefaultedRegistry<SeaSerpentType> SEA_SERPENT_TYPE = create("blue", IafRegistryKeys.SEA_SERPENT_TYPE);
    public static final DefaultedRegistry<TrollType> TROLL_TYPE = create("forest", IafRegistryKeys.TROLL_TYPE);

    private static <T> DefaultedRegistry<T> create(String defaultId, RegistryKey<Registry<T>> key) {
        return new SimpleDefaultedRegistry<>(defaultId, key, Lifecycle.stable(), false);
    }
}
