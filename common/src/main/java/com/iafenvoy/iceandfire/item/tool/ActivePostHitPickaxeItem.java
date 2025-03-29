package com.iafenvoy.iceandfire.item.tool;

import com.iafenvoy.iceandfire.item.ability.impl.AbilityImpl;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

import java.util.List;

public class ActivePostHitPickaxeItem extends PickaxeItem {
    private final AbilityImpl ability;
    public ActivePostHitPickaxeItem(ToolMaterial material, Settings settings, AbilityImpl ability) {
        super(material, settings);
        this.ability = ability;
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (ability.isEnable()) {
            ability.active(target, attacker);
        }
        return super.postHit(stack, target, attacker);
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        if (ability.isEnable()) {
            ability.addDescription(tooltip);
        }
    }
}
