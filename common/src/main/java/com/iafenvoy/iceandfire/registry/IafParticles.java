package com.iafenvoy.iceandfire.registry;

import com.iafenvoy.iceandfire.IceAndFire;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.RegistryKeys;

import java.util.List;
import java.util.function.Supplier;

public final class IafParticles {
    public static final DeferredRegister<ParticleType<?>> REGISTRY = DeferredRegister.create(IceAndFire.MOD_ID, RegistryKeys.PARTICLE_TYPE);
    //Fire
    public static final RegistrySupplier<DefaultParticleType> DRAGON_FLAME_1 = register("dragon_flame_1", () -> new DefaultParticleType(true));
    public static final RegistrySupplier<DefaultParticleType> DRAGON_FLAME_2 = register("dragon_flame_2", () -> new DefaultParticleType(true));
    public static final RegistrySupplier<DefaultParticleType> DRAGON_FLAME_3 = register("dragon_flame_3", () -> new DefaultParticleType(true));
    public static final RegistrySupplier<DefaultParticleType> DRAGON_FLAME_4 = register("dragon_flame_4", () -> new DefaultParticleType(true));
    public static final RegistrySupplier<DefaultParticleType> DRAGON_FLAME_5 = register("dragon_flame_5", () -> new DefaultParticleType(true));
    public static final List<Supplier<DefaultParticleType>> ALL_DRAGON_FLAME = List.of(() -> ParticleTypes.FLAME, DRAGON_FLAME_1, DRAGON_FLAME_2, DRAGON_FLAME_3, DRAGON_FLAME_4, DRAGON_FLAME_5);
    //Ice
    public static final RegistrySupplier<DefaultParticleType> DRAGON_FROST_1 = register("dragon_frost_1", () -> new DefaultParticleType(true));
    public static final RegistrySupplier<DefaultParticleType> DRAGON_FROST_2 = register("dragon_frost_2", () -> new DefaultParticleType(true));
    public static final RegistrySupplier<DefaultParticleType> DRAGON_FROST_3 = register("dragon_frost_3", () -> new DefaultParticleType(true));
    public static final RegistrySupplier<DefaultParticleType> DRAGON_FROST_4 = register("dragon_frost_4", () -> new DefaultParticleType(true));
    public static final RegistrySupplier<DefaultParticleType> DRAGON_FROST_5 = register("dragon_frost_5", () -> new DefaultParticleType(true));
    public static final List<Supplier<DefaultParticleType>> ALL_DRAGON_FROST = List.of(() -> ParticleTypes.SNOWFLAKE, DRAGON_FROST_1, DRAGON_FROST_2, DRAGON_FROST_3, DRAGON_FROST_4, DRAGON_FROST_5);
    //Others
    public static final RegistrySupplier<DefaultParticleType> BLOOD = register("blood", () -> new DefaultParticleType(true));
    public static final RegistrySupplier<DefaultParticleType> DREAD_PORTAL = register("dread_portal", () -> new DefaultParticleType(true));
    public static final RegistrySupplier<DefaultParticleType> DREAD_TORCH = register("dread_torch", () -> new DefaultParticleType(true));
    public static final RegistrySupplier<DefaultParticleType> GHOST_APPEARANCE = register("ghost_appearance", () -> new DefaultParticleType(true));
    public static final RegistrySupplier<DefaultParticleType> HYDRA_BREATH = register("hydra_breath", () -> new DefaultParticleType(true));
    public static final RegistrySupplier<DefaultParticleType> PIXIE_DUST = register("pixie_dust", () -> new DefaultParticleType(true));
    public static final RegistrySupplier<DefaultParticleType> SERPENT_BUBBLE = register("serpent_bubble", () -> new DefaultParticleType(true));
    public static final RegistrySupplier<DefaultParticleType> SIREN_APPEARANCE = register("siren_appearance", () -> new DefaultParticleType(true));
    public static final RegistrySupplier<DefaultParticleType> SIREN_MUSIC = register("siren_music", () -> new DefaultParticleType(true));

    private static RegistrySupplier<DefaultParticleType> register(String name, Supplier<DefaultParticleType> obj) {
        return REGISTRY.register(name, obj);
    }
}
