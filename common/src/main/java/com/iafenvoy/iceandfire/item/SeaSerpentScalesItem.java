package com.iafenvoy.iceandfire.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class SeaSerpentScalesItem extends Item {
    private final Formatting color;
    private final String colorName;

    public SeaSerpentScalesItem(String colorName, Formatting color) {
        super(new Settings());
        this.color = color;
        this.colorName = colorName;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        tooltip.add(Text.translatable("sea_serpent." + this.colorName).formatted(this.color));
    }
}
