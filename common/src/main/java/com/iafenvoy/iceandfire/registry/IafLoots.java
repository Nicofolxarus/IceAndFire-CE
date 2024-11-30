package com.iafenvoy.iceandfire.registry;

import com.iafenvoy.iceandfire.IceAndFire;
import com.iafenvoy.iceandfire.loot.CustomizeToDragon;
import com.iafenvoy.iceandfire.loot.CustomizeToSeaSerpent;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.loot.function.LootFunction;
import net.minecraft.loot.function.LootFunctionType;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.JsonSerializer;

import java.util.function.Supplier;

public final class IafLoots {
    public static final DeferredRegister<LootFunctionType> REGISTRY = DeferredRegister.create(IceAndFire.MOD_ID, RegistryKeys.LOOT_FUNCTION_TYPE);

    public static final RegistrySupplier<LootFunctionType> CUSTOMIZE_TO_DRAGON = register("customize_to_dragon", CustomizeToDragon.Serializer::new);
    public static final RegistrySupplier<LootFunctionType> CUSTOMIZE_TO_SERPENT = register("customize_to_sea_serpent", CustomizeToSeaSerpent.Serializer::new);

    private static RegistrySupplier<LootFunctionType> register(String p_237451_0_, Supplier<JsonSerializer<? extends LootFunction>> p_237451_1_) {
        return REGISTRY.register(p_237451_0_, () -> new LootFunctionType(p_237451_1_.get()));
    }
}
