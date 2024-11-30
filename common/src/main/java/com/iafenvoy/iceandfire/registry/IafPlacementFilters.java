package com.iafenvoy.iceandfire.registry;

import com.iafenvoy.iceandfire.IceAndFire;
import com.iafenvoy.iceandfire.world.CustomBiomeFilter;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifierType;

import java.util.function.Supplier;

public final class IafPlacementFilters {
    public static final DeferredRegister<PlacementModifierType<?>> REGISTRY = DeferredRegister.create(IceAndFire.MOD_ID, RegistryKeys.PLACEMENT_MODIFIER_TYPE);

    public static final RegistrySupplier<PlacementModifierType<CustomBiomeFilter>> CUSTOM_BIOME_FILTER = register("biome_extended", () -> () -> CustomBiomeFilter.CODEC);

    private static <T extends PlacementModifier> RegistrySupplier<PlacementModifierType<T>> register(String name, Supplier<PlacementModifierType<T>> type) {
        return REGISTRY.register(name, type);
    }
}
