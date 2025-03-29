package com.iafenvoy.iceandfire.item.tool;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

import java.util.List;

public class ItemModAxe extends AxeItem {
    private final ToolMaterial tier;

    public ItemModAxe(ToolMaterial toolmaterial) {
        super(toolmaterial, new Settings().component(DataComponentTypes.ATTRIBUTE_MODIFIERS, createAttributeModifiers(toolmaterial, 5, -3)));
        this.tier = toolmaterial;
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return this.getMaterial().getDurability();
    }
}
