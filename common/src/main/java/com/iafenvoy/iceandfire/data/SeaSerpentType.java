package com.iafenvoy.iceandfire.data;

import com.iafenvoy.iceandfire.IceAndFire;
import com.iafenvoy.iceandfire.item.SeaSerpentScalesItem;
import com.iafenvoy.iceandfire.item.armor.SeaSerpentArmorItem;
import com.iafenvoy.iceandfire.item.block.SeaSerpentScalesBlock;
import com.iafenvoy.iceandfire.registry.IafArmorMaterials;
import com.iafenvoy.iceandfire.registry.IafBlocks;
import com.iafenvoy.iceandfire.registry.IafItems;
import com.iafenvoy.iceandfire.registry.IafRegistries;
import com.iafenvoy.uranus.util.function.MemorizeSupplier;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.block.Block;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.List;

public class SeaSerpentType {
    private final String name;
    private final Formatting color;
    public RegistrySupplier<ArmorMaterial> armorMaterial;
    public RegistrySupplier<Item> scale, helmet, chestplate, leggings, boots;
    public RegistrySupplier<Block> scaleBlock;

    public SeaSerpentType(String name, Formatting color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return this.name;
    }

    public Formatting getColor() {
        return this.color;
    }

    public Identifier getTexture(boolean blink) {
        return Identifier.of(IceAndFire.MOD_ID, String.format("textures/entity/seaserpent/seaserpent_%s%s.png", this.name, blink ? "_blink" : ""));
    }

    public static List<SeaSerpentType> values() {
        return IafRegistries.SEA_SERPENT_TYPE.stream().toList();
    }

    public static void initArmors() {
        for (SeaSerpentType color : SeaSerpentType.values()) {
            color.armorMaterial = IafArmorMaterials.register("sea_serpent_scales_" + color.name, new int[]{4, 7, 8, 4}, 25, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 2.5F, new MemorizeSupplier<>(() -> Ingredient.ofItems(color.scale.get())));
            color.scaleBlock = IafBlocks.register("sea_serpent_scale_block_" + color.name, () -> new SeaSerpentScalesBlock(color.name, color.color));
            color.scale = IafItems.register("sea_serpent_scales_" + color.name, () -> new SeaSerpentScalesItem(color.name, color.color));
            color.helmet = IafItems.register("tide_" + color.name + "_helmet", () -> new SeaSerpentArmorItem(color, color.armorMaterial, ArmorItem.Type.HELMET));
            color.chestplate = IafItems.register("tide_" + color.name + "_chestplate", () -> new SeaSerpentArmorItem(color, color.armorMaterial, ArmorItem.Type.CHESTPLATE));
            color.leggings = IafItems.register("tide_" + color.name + "_leggings", () -> new SeaSerpentArmorItem(color, color.armorMaterial, ArmorItem.Type.LEGGINGS));
            color.boots = IafItems.register("tide_" + color.name + "_boots", () -> new SeaSerpentArmorItem(color, color.armorMaterial, ArmorItem.Type.BOOTS));
        }
    }
}
