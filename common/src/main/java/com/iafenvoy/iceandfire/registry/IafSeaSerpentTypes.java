package com.iafenvoy.iceandfire.registry;

import com.iafenvoy.iceandfire.IceAndFire;
import com.iafenvoy.iceandfire.data.SeaSerpentType;
import net.minecraft.registry.Registry;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

public final class IafSeaSerpentTypes {
    public static final SeaSerpentType BLUE = register("blue", Formatting.BLUE);
    public static final SeaSerpentType BRONZE = register("bronze", Formatting.GOLD);
    public static final SeaSerpentType DEEPBLUE = register("deepblue", Formatting.DARK_BLUE);
    public static final SeaSerpentType GREEN = register("green", Formatting.DARK_GREEN);
    public static final SeaSerpentType PURPLE = register("purple", Formatting.DARK_PURPLE);
    public static final SeaSerpentType RED = register("red", Formatting.DARK_RED);
    public static final SeaSerpentType TEAL = register("teal", Formatting.AQUA);

    private static SeaSerpentType register(String name, Formatting color) {
        return Registry.register(IafRegistries.SEA_SERPENT_TYPE, Identifier.of(IceAndFire.MOD_ID, name), new SeaSerpentType(name, color));
    }

    public static void init() {
    }
}
