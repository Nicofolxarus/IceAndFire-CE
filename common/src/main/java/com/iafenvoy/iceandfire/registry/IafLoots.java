package com.iafenvoy.iceandfire.registry;

import com.iafenvoy.iceandfire.IceAndFire;
import com.iafenvoy.iceandfire.loot.DragonLootFunction;
import com.iafenvoy.iceandfire.loot.SeaSerpentLootFunction;
import com.mojang.serialization.MapCodec;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.loot.function.LootFunction;
import net.minecraft.loot.function.LootFunctionType;
import net.minecraft.registry.RegistryKeys;

import java.util.function.Supplier;

public final class IafLoots {
    public static final DeferredRegister<LootFunctionType<?>> REGISTRY = DeferredRegister.create(IceAndFire.MOD_ID, RegistryKeys.LOOT_FUNCTION_TYPE);

    public static final RegistrySupplier<LootFunctionType<DragonLootFunction>> DRAGON_LOOT = register("dragon_loot", () -> DragonLootFunction.CODEC);
    public static final RegistrySupplier<LootFunctionType<SeaSerpentLootFunction>> SEA_SERPENT_LOOT = register("sea_serpent_loot", () -> SeaSerpentLootFunction.CODEC);

    private static <T extends LootFunction> RegistrySupplier<LootFunctionType<T>> register(String id, Supplier<MapCodec<T>> obj) {
        return REGISTRY.register(id, () -> new LootFunctionType<>(obj.get()));
    }
}
