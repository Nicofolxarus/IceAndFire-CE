package com.iafenvoy.iceandfire.item;

import com.iafenvoy.iceandfire.data.SeaSerpentType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

import java.util.List;

public class SeaSerpentScaleItem extends Item {
    private final SeaSerpentType type;

    public SeaSerpentScaleItem(SeaSerpentType type) {
        super(new Settings());
        this.type = type;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        tooltip.add(Text.translatable("sea_serpent." + this.type.getName()).formatted(this.type.getColor()));
    }
}
