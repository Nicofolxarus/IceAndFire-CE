package com.iafenvoy.iceandfire.registry;

import com.iafenvoy.iceandfire.IceAndFire;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.block.entity.BannerPattern;
import net.minecraft.registry.RegistryKeys;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public final class IafBannerPatterns {
    public static final DeferredRegister<BannerPattern> REGISTRY = DeferredRegister.create(IceAndFire.MOD_ID, RegistryKeys.BANNER_PATTERN);

    public static final RegistrySupplier<BannerPattern> PATTERN_FIRE = register("fire", () -> new BannerPattern("iaf_fire"));
    public static final RegistrySupplier<BannerPattern> PATTERN_ICE = register("ice", () -> new BannerPattern("iaf_ice"));
    public static final RegistrySupplier<BannerPattern> PATTERN_LIGHTNING = register("lightning", () -> new BannerPattern("iaf_lightning"));
    public static final RegistrySupplier<BannerPattern> PATTERN_FIRE_HEAD = register("fire_head", () -> new BannerPattern("iaf_fire_head"));
    public static final RegistrySupplier<BannerPattern> PATTERN_ICE_HEAD = register("ice_head", () -> new BannerPattern("iaf_ice_head"));
    public static final RegistrySupplier<BannerPattern> PATTERN_LIGHTNING_HEAD = register("lightning_head", () -> new BannerPattern("iaf_lightning_head"));
    public static final RegistrySupplier<BannerPattern> PATTERN_AMPHITHERE = register("amphithere", () -> new BannerPattern("iaf_amphithere"));
    public static final RegistrySupplier<BannerPattern> PATTERN_BIRD = register("bird", () -> new BannerPattern("iaf_bird"));
    public static final RegistrySupplier<BannerPattern> PATTERN_EYE = register("eye", () -> new BannerPattern("iaf_eye"));
    public static final RegistrySupplier<BannerPattern> PATTERN_FAE = register("fae", () -> new BannerPattern("iaf_fae"));
    public static final RegistrySupplier<BannerPattern> PATTERN_FEATHER = register("feather", () -> new BannerPattern("iaf_feather"));
    public static final RegistrySupplier<BannerPattern> PATTERN_GORGON = register("gorgon", () -> new BannerPattern("iaf_gorgon"));
    public static final RegistrySupplier<BannerPattern> PATTERN_HIPPOCAMPUS = register("hippocampus", () -> new BannerPattern("iaf_hippocampus"));
    public static final RegistrySupplier<BannerPattern> PATTERN_HIPPOGRYPH_HEAD = register("hippogryph_head", () -> new BannerPattern("iaf_hippogryph_head"));
    public static final RegistrySupplier<BannerPattern> PATTERN_MERMAID = register("mermaid", () -> new BannerPattern("iaf_mermaid"));
    public static final RegistrySupplier<BannerPattern> PATTERN_SEA_SERPENT = register("sea_serpent", () -> new BannerPattern("iaf_sea_serpent"));
    public static final RegistrySupplier<BannerPattern> PATTERN_TROLL = register("troll", () -> new BannerPattern("iaf_troll"));
    public static final RegistrySupplier<BannerPattern> PATTERN_WEEZER = register("weezer", () -> new BannerPattern("iaf_weezer"));
    public static final RegistrySupplier<BannerPattern> PATTERN_DREAD = register("dread", () -> new BannerPattern("iaf_dread"));

    private static RegistrySupplier<BannerPattern> register(String name, Supplier<BannerPattern> pattern) {
        return REGISTRY.register(name, pattern);
    }
}
