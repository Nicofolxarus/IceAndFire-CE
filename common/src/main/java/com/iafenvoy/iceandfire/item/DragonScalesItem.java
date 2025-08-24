package com.iafenvoy.iceandfire.item;

import com.iafenvoy.iceandfire.data.DragonColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

import java.util.List;
import java.util.Locale;

public class DragonScalesItem extends Item {
    final DragonColor type;

    public DragonScalesItem(DragonColor type) {
        super(new Settings());
        this.type = type;
    }

    @Override
    public String getTranslationKey() {
        return "item.iceandfire.dragonscales";
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        tooltip.add(Text.translatable("dragon." + this.type.name().toLowerCase(Locale.ROOT)).formatted(this.type.color()));
    }
}
