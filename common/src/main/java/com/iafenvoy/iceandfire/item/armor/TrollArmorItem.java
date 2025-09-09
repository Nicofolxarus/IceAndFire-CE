package com.iafenvoy.iceandfire.item.armor;

import com.iafenvoy.iceandfire.data.TrollType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class TrollArmorItem extends ArmorItem {
    private final TrollType trollType;

    public TrollArmorItem(TrollType trollType, Type type) {
        super(trollType.getMaterial(), type, new Settings().maxDamage(switch (type) {
            case HELMET -> 220;
            case CHESTPLATE -> 320;
            case LEGGINGS -> 300;
            case BOOTS -> 260;
            case BODY -> 0;
        }));
        this.trollType = trollType;
    }

    public static String getName(TrollType trollType, Type type) {
        return "%s_troll_leather_%s".formatted(trollType.getName(), type.getName());
    }

    @Override
    public RegistryEntry<ArmorMaterial> getMaterial() {
        return this.trollType.getMaterial();
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        tooltip.add(Text.translatable("item.iceandfire.troll_leather_armor_%s.desc".formatted(this.type.getName())).formatted(Formatting.GREEN));
    }

    public TrollType getTrollType() {
        return this.trollType;
    }
}
