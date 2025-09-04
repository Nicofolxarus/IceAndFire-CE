package com.iafenvoy.iceandfire.data;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

public record DragonArmorMaterial(String name, double protection) {
    private static final List<DragonArmorMaterial> MATERIALS = new ArrayList<>();
    public static final DragonArmorMaterial IRON = new DragonArmorMaterial("iron", 1.5);
    public static final DragonArmorMaterial COPPER = new DragonArmorMaterial("copper", 2);
    public static final DragonArmorMaterial SILVER = new DragonArmorMaterial("silver", 3);
    public static final DragonArmorMaterial GOLD = new DragonArmorMaterial("gold", 2);
    public static final DragonArmorMaterial DIAMOND = new DragonArmorMaterial("diamond", 5);
    public static final DragonArmorMaterial NETHERITE = new DragonArmorMaterial("netherite", 7);
    public static final DragonArmorMaterial DRAGON_STEEL_FIRE = new DragonArmorMaterial("dragon_steel_fire", 10);
    public static final DragonArmorMaterial DRAGON_STEEL_ICE = new DragonArmorMaterial("dragon_steel_ice", 10);
    public static final DragonArmorMaterial DRAGON_STEEL_LIGHTNING = new DragonArmorMaterial("dragon_steel_lightning", 10);

    public DragonArmorMaterial(String name, double protection) {
        this.name = name;
        this.protection = protection;
        MATERIALS.add(this);
    }

    public static List<DragonArmorMaterial> values() {
        return ImmutableList.copyOf(MATERIALS);
    }
}
