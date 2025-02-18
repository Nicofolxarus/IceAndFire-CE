package com.iafenvoy.iceandfire.item.tool;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

import java.util.List;

public class ItemModPickaxe extends PickaxeItem implements DragonSteelOverrides<ItemModPickaxe> {
    public ItemModPickaxe(ToolMaterial toolmaterial) {
        super(toolmaterial, new Settings());
    }

//    @Override
//    @Deprecated
//    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot equipmentSlot) {
//        return equipmentSlot == EquipmentSlot.MAINHAND && this.isDragonSteel(this.getMaterial()) ? this.bakeDragonsteel() : super.getAttributeModifiers(equipmentSlot);
//    }

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
