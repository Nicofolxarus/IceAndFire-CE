package com.iafenvoy.iceandfire.data;

import com.iafenvoy.iceandfire.IceAndFire;
import com.iafenvoy.iceandfire.entity.DragonBaseEntity;
import com.iafenvoy.iceandfire.item.armor.ScaleArmorItem;
import com.iafenvoy.iceandfire.registry.IafArmorMaterials;
import com.iafenvoy.iceandfire.registry.IafItems;
import com.iafenvoy.iceandfire.registry.IafRegistries;
import com.iafenvoy.uranus.util.function.MemorizeSupplier;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.Locale;
import java.util.function.Supplier;

public final class DragonColor {
    private final String name;
    private final Formatting color;
    private final DragonType dragonType;
    private final Supplier<Item> eggItem, scaleItem;
    //FIXME:: Remove this
    public RegistrySupplier<Item> helmet, chestplate, leggings, boots;
    //FIXME:: Remove this
    private RegistrySupplier<ArmorMaterial> material;

    public DragonColor(String name, Formatting color, DragonType dragonType, Supplier<Item> eggItem, Supplier<Item> scaleItem) {
        this.name = name;
        this.color = color;
        this.dragonType = dragonType;
        this.eggItem = eggItem;
        this.scaleItem = scaleItem;
        dragonType.colors().add(this);
    }

    public static void initArmors() {
        for (DragonColor armor : IafRegistries.DRAGON_COLOR.stream().toList()) {
            armor.material = IafArmorMaterials.register("armor_dragon_scales_" + IafRegistries.DRAGON_COLOR.getRawId(armor), new int[]{5, 7, 9, 5}, 15, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 2, new MemorizeSupplier<>(() -> Ingredient.ofItems(armor.scaleItem.get())));
            String sub = "armor_" + armor.getName().toLowerCase(Locale.ROOT);
            armor.helmet = IafItems.registerArmor(sub + "_helmet", () -> new ScaleArmorItem(armor, ArmorItem.Type.HELMET));
            armor.chestplate = IafItems.registerArmor(sub + "_chestplate", () -> new ScaleArmorItem(armor, ArmorItem.Type.CHESTPLATE));
            armor.leggings = IafItems.registerArmor(sub + "_leggings", () -> new ScaleArmorItem(armor, ArmorItem.Type.LEGGINGS));
            armor.boots = IafItems.registerArmor(sub + "_boots", () -> new ScaleArmorItem(armor, ArmorItem.Type.BOOTS));
        }
    }

    public static DragonColor getById(String id) {
        return IafRegistries.DRAGON_COLOR.get(IceAndFire.id(id));
    }

    public Item getEggItem() {
        return this.eggItem.get();
    }

    public Item getScaleItem() {
        return this.scaleItem.get();
    }

    public Identifier getEggTexture() {
        return Identifier.of(IceAndFire.MOD_ID, String.format("textures/entity/%sdragon/egg_%s.png", this.dragonType.name(), this.name));
    }

    public Identifier getTextureByEntity(DragonBaseEntity dragon) {
        int stage = dragon.getDragonStage();
        if (dragon.isModelDead()) {
            if (dragon.getDeathStage() >= dragon.getAgeInDays() / 10) return this.getSkeletonTexture(stage);
            else return this.getSleepTexture(stage);
        } else if (dragon.isSleeping() || dragon.isBlinking()) return this.getSleepTexture(stage);
        else return this.getBodyTexture(stage);
    }

    public Identifier getBodyTexture(int stage) {
        return Identifier.of(IceAndFire.MOD_ID, String.format("textures/entity/%sdragon/%s_%d.png", this.dragonType.name(), this.name, stage));
    }

    public Identifier getSleepTexture(int stage) {
        return Identifier.of(IceAndFire.MOD_ID, String.format("textures/entity/%sdragon/%s_%d_sleeping.png", this.dragonType.name(), this.name, stage));
    }

    public Identifier getEyesTexture(int stage) {
        return Identifier.of(IceAndFire.MOD_ID, String.format("textures/entity/%sdragon/%s_%d_eyes.png", this.dragonType.name(), this.name, stage));
    }

    public Identifier getSkeletonTexture(int stage) {
        return Identifier.of(IceAndFire.MOD_ID, String.format("textures/entity/%sdragon/%s_skeleton_%d.png", this.dragonType.name(), this.dragonType.name(), stage));
    }

    public Identifier getMaleOverlay() {
        return Identifier.of(IceAndFire.MOD_ID, String.format("textures/entity/%sdragon/male_%s.png", this.dragonType.name(), this.name));
    }

    public String getName() {
        return this.name;
    }

    public Formatting getColorFormatting() {
        return this.color;
    }

    public DragonType getType() {
        return this.dragonType;
    }

    public RegistrySupplier<ArmorMaterial> getMaterial() {
        return this.material;
    }
}
