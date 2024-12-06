package com.iafenvoy.iceandfire.registry;

import com.iafenvoy.iceandfire.IceAndFire;
import com.iafenvoy.iceandfire.particle.DragonFlameParticleType;
import com.iafenvoy.iceandfire.particle.DragonFrostParticleType;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleType;
import net.minecraft.registry.RegistryKeys;

import java.util.function.Supplier;

public final class IafParticles {
    public static final DeferredRegister<ParticleType<?>> REGISTRY = DeferredRegister.create(IceAndFire.MOD_ID, RegistryKeys.PARTICLE_TYPE);
    public static final RegistrySupplier<DragonFlameParticleType> DRAGON_FLAME = register("dragon_flame", DragonFlameParticleType::new);
    public static final RegistrySupplier<DragonFrostParticleType> DRAGON_FROST = register("dragon_frost", DragonFrostParticleType::new);
    public static final RegistrySupplier<DefaultParticleType> BLOOD = register("blood", () -> new DefaultParticleType(true));
    public static final RegistrySupplier<DefaultParticleType> DREAD_PORTAL = register("dread_portal", () -> new DefaultParticleType(true));
    public static final RegistrySupplier<DefaultParticleType> DREAD_TORCH = register("dread_torch", () -> new DefaultParticleType(true));
    public static final RegistrySupplier<DefaultParticleType> GHOST_APPEARANCE = register("ghost_appearance", () -> new DefaultParticleType(true));
    public static final RegistrySupplier<DefaultParticleType> HYDRA_BREATH = register("hydra_breath", () -> new DefaultParticleType(true));
    public static final RegistrySupplier<DefaultParticleType> PIXIE_DUST = register("pixie_dust", () -> new DefaultParticleType(true));
    public static final RegistrySupplier<DefaultParticleType> SERPENT_BUBBLE = register("serpent_bubble", () -> new DefaultParticleType(true));
    public static final RegistrySupplier<DefaultParticleType> SIREN_APPEARANCE = register("siren_appearance", () -> new DefaultParticleType(true));
    public static final RegistrySupplier<DefaultParticleType> SIREN_MUSIC = register("siren_music", () -> new DefaultParticleType(true));

    private static <T extends ParticleType<?>> RegistrySupplier<T> register(String name, Supplier<T> obj) {
        return REGISTRY.register(name, obj);
    }
}
