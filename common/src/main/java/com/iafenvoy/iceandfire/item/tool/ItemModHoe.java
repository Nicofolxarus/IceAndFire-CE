package com.iafenvoy.iceandfire.item.tool;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

import java.util.List;

public class ItemModHoe extends HoeItem {
    public ItemModHoe(ToolMaterial toolmaterial) {
        super(toolmaterial, new Settings());
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return this.getMaterial().getDurability();
    }
}
