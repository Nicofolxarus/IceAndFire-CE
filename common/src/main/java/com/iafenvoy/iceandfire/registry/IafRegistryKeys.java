package com.iafenvoy.iceandfire.registry;

import com.iafenvoy.iceandfire.IceAndFire;
import com.iafenvoy.iceandfire.data.*;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public final class IafRegistryKeys {
    public static final RegistryKey<Registry<BestiaryPage>> BESTIARY_PAGE = createKey("bestiary_page");
    public static final RegistryKey<Registry<DragonColor>> DRAGON_COLOR = createKey("dragon_color");
    public static final RegistryKey<Registry<DragonType>> DRAGON_TYPE = createKey("dragon_type");
    public static final RegistryKey<Registry<HippogryphType>> HIPPOGRYPH_TYPE = createKey("hippogryph_type");
    public static final RegistryKey<Registry<SeaSerpentType>> SEA_SERPENT_TYPE = createKey("sea_serpent_type");
    public static final RegistryKey<Registry<TrollType>> TROLL_TYPE = createKey("troll_type");

    private static <T> RegistryKey<Registry<T>> createKey(String id) {
        return RegistryKey.ofRegistry(Identifier.of(IceAndFire.MOD_ID, id));
    }
}
