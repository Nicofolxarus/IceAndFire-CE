package com.iafenvoy.iceandfire.item.ability;

import com.iafenvoy.iceandfire.config.IafCommonConfig;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

import java.util.List;

public abstract class ActivePostHitPickaxeItem extends PickaxeItem implements Ability {
    public ActivePostHitPickaxeItem(ToolMaterial material, Settings settings) {
        super(material, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (IafCommonConfig.INSTANCE.armors.dragonIceAbility.getValue()) {
            activeAbility(target, attacker);
        }
        return super.postHit(stack, target, attacker);
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (IafCommonConfig.INSTANCE.armors.dragonIceAbility.getValue()) {
            super.appendTooltip(stack, context, tooltip, type);
        }
        this.addAbilityDescription(tooltip);
    }
}
