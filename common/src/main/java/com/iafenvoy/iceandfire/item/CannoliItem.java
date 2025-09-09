package com.iafenvoy.iceandfire.item;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class CannoliItem extends Item {
    public CannoliItem() {
        super(new Settings().food(new FoodComponent.Builder().nutrition(20).saturationModifier(2).alwaysEdible().statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 3600, 2), 1).build()));
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        tooltip.add(Text.translatable("item.iceandfire.cannoli.desc").formatted(Formatting.GRAY));
    }
}
