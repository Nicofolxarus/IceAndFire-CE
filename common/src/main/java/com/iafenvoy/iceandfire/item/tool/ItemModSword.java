package com.iafenvoy.iceandfire.item.tool;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

import java.util.List;

public class ItemModSword extends SwordItem implements DragonSteelOverrides<ItemModSword> {
    public ItemModSword(ToolMaterial toolmaterial) {
        super(toolmaterial, new Settings().component(DataComponentTypes.ATTRIBUTE_MODIFIERS, createAttributeModifiers(toolmaterial, 3, -2.4F)));
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return this.getMaterial().getDurability();
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        this.hurtEnemy(this, stack, target, attacker);
        return super.postHit(stack, target, attacker);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        this.appendHoverText(this.getMaterial(), tooltip);
    }
}
