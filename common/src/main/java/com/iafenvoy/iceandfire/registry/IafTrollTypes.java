package com.iafenvoy.iceandfire.registry;

import com.iafenvoy.iceandfire.IceAndFire;
import com.iafenvoy.iceandfire.data.TrollType;
import com.iafenvoy.iceandfire.registry.tag.IafBiomeTags;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

public final class IafTrollTypes {
    public static final TrollType FOREST = register("forest", IafArmorMaterials.TROLL_FOREST_ARMOR_MATERIAL, IafBiomeTags.FOREST_TROLL, TrollType.BuiltinWeapon.TRUNK, TrollType.BuiltinWeapon.COLUMN_FOREST, TrollType.BuiltinWeapon.AXE, TrollType.BuiltinWeapon.HAMMER);
    public static final TrollType FROST = register("frost", IafArmorMaterials.TROLL_FROST_ARMOR_MATERIAL, IafBiomeTags.SNOWY_TROLL, TrollType.BuiltinWeapon.COLUMN_FROST, TrollType.BuiltinWeapon.TRUNK_FROST, TrollType.BuiltinWeapon.AXE, TrollType.BuiltinWeapon.HAMMER);
    public static final TrollType MOUNTAIN = register("mountain", IafArmorMaterials.TROLL_MOUNTAIN_ARMOR_MATERIAL, IafBiomeTags.MOUNTAIN_TROLL, TrollType.BuiltinWeapon.COLUMN, TrollType.BuiltinWeapon.AXE, TrollType.BuiltinWeapon.HAMMER);

    private static TrollType register(String name, RegistryEntry<ArmorMaterial> material, TagKey<Biome> spawnBiomes, TrollType.BuiltinWeapon... weapons) {
        return Registry.register(IafRegistries.TROLL_TYPE, Identifier.of(IceAndFire.MOD_ID, name), new TrollType(name, material, spawnBiomes, weapons));
    }

    public static void init() {
    }
}
