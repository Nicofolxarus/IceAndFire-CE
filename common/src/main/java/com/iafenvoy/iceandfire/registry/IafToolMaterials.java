package com.iafenvoy.iceandfire.registry;

import com.iafenvoy.iceandfire.registry.tag.IafItemTags;
import com.iafenvoy.iceandfire.util.CustomToolMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;

public final class IafToolMaterials {
    public static final CustomToolMaterial SILVER_TOOL_MATERIAL = new CustomToolMaterial("silver", 460, 1.0F, 11.0F, 18);
    public static final CustomToolMaterial COPPER_TOOL_MATERIAL = new CustomToolMaterial("copper", 300, 0.0F, 0.7F, 10);
    public static final CustomToolMaterial DRAGONBONE_TOOL_MATERIAL = new CustomToolMaterial("dragon_bone", 1660, 4.0F, 10.0F, 22);
    public static final CustomToolMaterial FIRE_DRAGONBONE_TOOL_MATERIAL = new CustomToolMaterial("fire_dragon_bone", 2000, 5.5F, 10F, 22);
    public static final CustomToolMaterial ICE_DRAGONBONE_TOOL_MATERIAL = new CustomToolMaterial("ice_dragon_bone", 2000, 5.5F, 10F, 22);
    public static final CustomToolMaterial LIGHTNING_DRAGONBONE_TOOL_MATERIAL = new CustomToolMaterial("lightning_dragon_bone", 2000, 5.5F, 10F, 22);
    public static final CustomToolMaterial TROLL_WEAPON_TOOL_MATERIAL = new CustomToolMaterial("troll_weapon", 300, 1F, 10F, 1);
    public static final CustomToolMaterial MYRMEX_CHITIN_TOOL_MATERIAL = new CustomToolMaterial("myrmex_chitin", 600, 1.0F, 6.0F, 8);
    public static final CustomToolMaterial HIPPOGRYPH_SWORD_TOOL_MATERIAL = new CustomToolMaterial("hippogryph_sword", 500, 2.5F, 10F, 10);
    public static final CustomToolMaterial STYMHALIAN_SWORD_TOOL_MATERIAL = new CustomToolMaterial("stymphalian_sword", 500, 2, 10.0F, 10);
    public static final CustomToolMaterial AMPHITHERE_SWORD_TOOL_MATERIAL = new CustomToolMaterial("amphithere_sword", 500, 1F, 10F, 10);
    public static final CustomToolMaterial HIPPOCAMPUS_SWORD_TOOL_MATERIAL = new CustomToolMaterial("hippocampus_sword", 500, -2F, 0F, 50);
    public static final CustomToolMaterial DREAD_SWORD_TOOL_MATERIAL = new CustomToolMaterial("dread_sword", 100, 1F, 10F, 0);
    public static final CustomToolMaterial DREAD_KNIGHT_TOOL_MATERIAL = new CustomToolMaterial("dread_knight_sword", 1200, 13F, 0F, 10);
    public static final CustomToolMaterial GHOST_SWORD_TOOL_MATERIAL = new CustomToolMaterial("ghost_sword", 3000, 5, 10.0F, 25);

    public static void init() {
        SILVER_TOOL_MATERIAL.setRepairMaterial(Ingredient.fromTag(IafItemTags.INGOTS_SILVER));
        DRAGONBONE_TOOL_MATERIAL.setRepairMaterial(Ingredient.ofItems(IafItems.DRAGON_BONE.get()));
        FIRE_DRAGONBONE_TOOL_MATERIAL.setRepairMaterial(Ingredient.ofItems(IafItems.DRAGON_BONE.get()));
        ICE_DRAGONBONE_TOOL_MATERIAL.setRepairMaterial(Ingredient.ofItems(IafItems.DRAGON_BONE.get()));
        LIGHTNING_DRAGONBONE_TOOL_MATERIAL.setRepairMaterial(Ingredient.ofItems(IafItems.DRAGON_BONE.get()));
        TROLL_WEAPON_TOOL_MATERIAL.setRepairMaterial(Ingredient.ofItems(Items.STONE));
        HIPPOGRYPH_SWORD_TOOL_MATERIAL.setRepairMaterial(Ingredient.ofItems(IafItems.HIPPOGRYPH_TALON.get()));
        HIPPOCAMPUS_SWORD_TOOL_MATERIAL.setRepairMaterial(Ingredient.ofItems(IafItems.SHINY_SCALES.get()));
        AMPHITHERE_SWORD_TOOL_MATERIAL.setRepairMaterial(Ingredient.ofItems(IafItems.AMPHITHERE_FEATHER.get()));
        STYMHALIAN_SWORD_TOOL_MATERIAL.setRepairMaterial(Ingredient.ofItems(IafItems.STYMPHALIAN_BIRD_FEATHER.get()));
        MYRMEX_CHITIN_TOOL_MATERIAL.setRepairMaterial(Ingredient.ofItems(IafItems.MYRMEX_DESERT_CHITIN.get()));
        DREAD_SWORD_TOOL_MATERIAL.setRepairMaterial(Ingredient.ofItems(IafItems.DREAD_SHARD.get()));
        DREAD_KNIGHT_TOOL_MATERIAL.setRepairMaterial(Ingredient.ofItems(IafItems.DREAD_SHARD.get()));
        COPPER_TOOL_MATERIAL.setRepairMaterial(Ingredient.ofItems(Items.COPPER_INGOT));
    }
}
