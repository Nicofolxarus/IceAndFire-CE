package com.iafenvoy.iceandfire.item;

import com.iafenvoy.iceandfire.registry.IafItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class GenericItem extends Item {
    private final int description;

    public GenericItem(int textLength) {
        super(new Settings());
        this.description = textLength;
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        if (this == IafItems.CREATIVE_DRAGON_MEAL.get()) return true;
        else return super.hasGlint(stack);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        if (this.description > 0)
            for (int i = 0; i < this.description; i++)
                tooltip.add(Text.translatable(this.getTranslationKey() + ".desc_" + i).formatted(Formatting.GRAY));
    }
}
