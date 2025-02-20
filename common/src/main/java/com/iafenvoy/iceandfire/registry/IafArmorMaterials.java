package com.iafenvoy.iceandfire.registry;

import com.iafenvoy.iceandfire.IceAndFire;
import com.iafenvoy.iceandfire.config.IafCommonConfig;
import com.iafenvoy.iceandfire.data.TrollType;
import com.iafenvoy.iceandfire.registry.tag.CommonTags;
import com.iafenvoy.iceandfire.registry.tag.IafItemTags;
import com.iafenvoy.uranus.util.function.MemorizeSupplier;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.block.Blocks;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public final class IafArmorMaterials {
    public static final DeferredRegister<ArmorMaterial> REGISTRY = DeferredRegister.create(IceAndFire.MOD_ID, RegistryKeys.ARMOR_MATERIAL);

    public static final RegistrySupplier<ArmorMaterial> COPPER_ARMOR_MATERIAL = register("copper", 10, new int[]{1, 3, 4, 2}, 15, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0, new MemorizeSupplier<>(() -> Ingredient.ofItems(Items.COPPER_INGOT)));
    public static final RegistrySupplier<ArmorMaterial> SILVER_ARMOR_MATERIAL = register("silver", 15, new int[]{1, 4, 5, 2}, 20, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 0, new MemorizeSupplier<>(() -> Ingredient.fromTag(IafItemTags.INGOTS_SILVER)));
    public static final RegistrySupplier<ArmorMaterial> BLINDFOLD_ARMOR_MATERIAL = register("blindfold", 5, new int[]{1, 1, 1, 1}, 10, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0, new MemorizeSupplier<>(() -> Ingredient.fromTag(CommonTags.Items.STRINGS)));
    public static final RegistrySupplier<ArmorMaterial> SHEEP_ARMOR_MATERIAL = register("sheep", 5, new int[]{1, 3, 2, 1}, 15, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0, new MemorizeSupplier<>(() -> Ingredient.ofItems(Blocks.WHITE_WOOL)));
    public static final RegistrySupplier<ArmorMaterial> MYRMEX_DESERT_ARMOR_MATERIAL = register("myrmex_desert", 20, new int[]{3, 5, 8, 4}, 15, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0, new MemorizeSupplier<>(() -> Ingredient.ofItems(IafItems.MYRMEX_DESERT_CHITIN.get())));
    public static final RegistrySupplier<ArmorMaterial> MYRMEX_JUNGLE_ARMOR_MATERIAL = register("myrmex_jungle", 20, new int[]{3, 5, 8, 4}, 15, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0, new MemorizeSupplier<>(() -> Ingredient.ofItems(IafItems.MYRMEX_JUNGLE_CHITIN.get())));
    public static final RegistrySupplier<ArmorMaterial> EARPLUGS_ARMOR_MATERIAL = register("earplugs", 5, new int[]{1, 1, 1, 1}, 10, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0, new MemorizeSupplier<>(() -> Ingredient.ofItems(Blocks.OAK_BUTTON)));
    public static final RegistrySupplier<ArmorMaterial> DEATHWORM_0_ARMOR_MATERIAL = register("yellow_deathworm", 15, new int[]{2, 5, 7, 3}, 5, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 1.5F, new MemorizeSupplier<>(() -> Ingredient.ofItems(IafItems.DEATH_WORM_CHITIN_YELLOW.get())));
    public static final RegistrySupplier<ArmorMaterial> DEATHWORM_1_ARMOR_MATERIAL = register("white_deathworm", 15, new int[]{2, 5, 7, 3}, 5, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 1.5F, new MemorizeSupplier<>(() -> Ingredient.ofItems(IafItems.DEATH_WORM_CHITIN_RED.get())));
    public static final RegistrySupplier<ArmorMaterial> DEATHWORM_2_ARMOR_MATERIAL = register("red_deathworm", 15, new int[]{2, 5, 7, 3}, 5, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 1.5F, new MemorizeSupplier<>(() -> Ingredient.ofItems(IafItems.DEATH_WORM_CHITIN_WHITE.get())));
    public static final RegistrySupplier<ArmorMaterial> TROLL_MOUNTAIN_ARMOR_MATERIAL = register("mountain_troll", 20, new int[]{2, 5, 7, 3}, 10, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 1F, new MemorizeSupplier<>(() -> Ingredient.ofItems(TrollType.MOUNTAIN.leather.get())));
    public static final RegistrySupplier<ArmorMaterial> TROLL_FOREST_ARMOR_MATERIAL = register("forest_troll", 20, new int[]{2, 5, 7, 3}, 10, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 1F, new MemorizeSupplier<>(() -> Ingredient.ofItems(TrollType.FOREST.leather.get())));
    public static final RegistrySupplier<ArmorMaterial> TROLL_FROST_ARMOR_MATERIAL = register("frost_troll", 20, new int[]{2, 5, 7, 3}, 10, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 1F, new MemorizeSupplier<>(() -> Ingredient.ofItems(TrollType.FROST.leather.get())));
    public static final RegistrySupplier<ArmorMaterial> DRAGONSTEEL_FIRE_ARMOR_MATERIAL = register("dragonsteel_fire", (int) (0.02D * IafCommonConfig.INSTANCE.armors.dragonSteelBaseDurabilityEquipment.getValue()), new int[]{IafCommonConfig.INSTANCE.armors.dragonSteelBaseArmor.getValue() - 6, IafCommonConfig.INSTANCE.armors.dragonSteelBaseArmor.getValue() - 3, IafCommonConfig.INSTANCE.armors.dragonSteelBaseArmor.getValue(), IafCommonConfig.INSTANCE.armors.dragonSteelBaseArmor.getValue() - 5}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, IafCommonConfig.INSTANCE.armors.dragonSteelBaseArmorToughness.getValue().floatValue(), new MemorizeSupplier<>(() -> Ingredient.ofItems(IafItems.DRAGONSTEEL_FIRE_INGOT.get())));
    public static final RegistrySupplier<ArmorMaterial> DRAGONSTEEL_ICE_ARMOR_MATERIAL = register("dragonsteel_ice", (int) (0.02D * IafCommonConfig.INSTANCE.armors.dragonSteelBaseDurabilityEquipment.getValue()), new int[]{IafCommonConfig.INSTANCE.armors.dragonSteelBaseArmor.getValue() - 6, IafCommonConfig.INSTANCE.armors.dragonSteelBaseArmor.getValue() - 3, IafCommonConfig.INSTANCE.armors.dragonSteelBaseArmor.getValue(), IafCommonConfig.INSTANCE.armors.dragonSteelBaseArmor.getValue() - 5}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, IafCommonConfig.INSTANCE.armors.dragonSteelBaseArmorToughness.getValue().floatValue(), new MemorizeSupplier<>(() -> Ingredient.ofItems(IafItems.DRAGONSTEEL_ICE_INGOT.get())));
    public static final RegistrySupplier<ArmorMaterial> DRAGONSTEEL_LIGHTNING_ARMOR_MATERIAL = register("dragonsteel_lightning", (int) (0.02D * IafCommonConfig.INSTANCE.armors.dragonSteelBaseDurabilityEquipment.getValue()), new int[]{IafCommonConfig.INSTANCE.armors.dragonSteelBaseArmor.getValue() - 6, IafCommonConfig.INSTANCE.armors.dragonSteelBaseArmor.getValue() - 3, IafCommonConfig.INSTANCE.armors.dragonSteelBaseArmor.getValue(), IafCommonConfig.INSTANCE.armors.dragonSteelBaseArmor.getValue() - 5}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, IafCommonConfig.INSTANCE.armors.dragonSteelBaseArmorToughness.getValue().floatValue(), new MemorizeSupplier<>(() -> Ingredient.ofItems(IafItems.DRAGONSTEEL_LIGHTNING_INGOT.get())));

    public static RegistrySupplier<ArmorMaterial> register(String name, int durability, int[] damageReduction, int encantability, RegistryEntry<SoundEvent> sound, float toughness, Supplier<Ingredient> repairIngredients) {
        return REGISTRY.register(name, () -> register(name, damageReduction, encantability, sound, toughness, 0, repairIngredients));
    }

    public static ArmorMaterial register(String name, int[] protection, int enchantAbility, RegistryEntry<SoundEvent> equipSound, float toughness, float knockBackResistance, Supplier<Ingredient> repairIngredients) {
        return new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), (map) -> {
            map.put(ArmorItem.Type.HELMET, protection[3]);
            map.put(ArmorItem.Type.CHESTPLATE, protection[2]);
            map.put(ArmorItem.Type.LEGGINGS, protection[1]);
            map.put(ArmorItem.Type.BOOTS, protection[0]);
        }), enchantAbility, equipSound, repairIngredients, List.of(new ArmorMaterial.Layer(Identifier.tryParse(name))), toughness, knockBackResistance);
    }
}
