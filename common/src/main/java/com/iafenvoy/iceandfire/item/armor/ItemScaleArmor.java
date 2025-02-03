package com.iafenvoy.iceandfire.item.armor;

import com.iafenvoy.iceandfire.data.DragonArmor;
import com.iafenvoy.iceandfire.data.DragonColor;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;
import java.util.Locale;

public class ItemScaleArmor extends ArmorItem implements IProtectAgainstDragonItem {
    public final DragonArmor armorType;
    public final DragonColor eggType;

    public ItemScaleArmor(DragonColor eggType, DragonArmor armorType, RegistryEntry<ArmorMaterial> material, Type slot) {
        super(material, slot, new Settings());
        this.armorType = armorType;
        this.eggType = eggType;
    }

    @Override
    public String getTranslationKey() {
        return switch (this.type) {
            case HELMET -> "item.iceandfire.dragon_helmet";
            case CHESTPLATE -> "item.iceandfire.dragon_chestplate";
            case LEGGINGS -> "item.iceandfire.dragon_leggings";
            case BOOTS -> "item.iceandfire.dragon_boots";
            case BODY -> "???";
        };
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("dragon." + this.eggType.name().toLowerCase(Locale.ROOT)).formatted(this.eggType.color()));
        tooltip.add(Text.translatable("item.dragonscales_armor.desc").formatted(Formatting.GRAY));
    }
}
