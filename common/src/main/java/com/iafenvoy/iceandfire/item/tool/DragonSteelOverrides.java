package com.iafenvoy.iceandfire.item.tool;

import com.iafenvoy.iceandfire.entity.EntityDeathWorm;
import com.iafenvoy.iceandfire.registry.IafToolMaterials;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.*;
import net.minecraft.registry.tag.EntityTypeTags;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;


public interface DragonSteelOverrides<T extends ToolItem> {
    default float getAttackDamage(T item) {
        if (item instanceof SwordItem swordItem)
            return swordItem.getMaterial().getAttackDamage();
        if (item instanceof MiningToolItem toolItem)
            return toolItem.getMaterial().getAttackDamage();
        return item.getMaterial().getAttackDamage();
    }

    static boolean isDragonSteel(ToolMaterial tier) {
        return isDragonSteelFire(tier) || isDragonSteelIce(tier) || isDragonSteelLightning(tier);
    }

    static boolean isDragonSteelFire(ToolMaterial tier) {
        return tier == IafToolMaterials.DRAGONSTEEL_FIRE;
    }

    static boolean isDragonSteelIce(ToolMaterial tier) {
        return tier == IafToolMaterials.DRAGONSTEEL_ICE;
    }

    static boolean isDragonSteelLightning(ToolMaterial tier) {
        return tier == IafToolMaterials.DRAGONSTEEL_LIGHTNING;
    }

    default void hurtEnemy(T item, ItemStack stack, LivingEntity target, LivingEntity attacker) {

        if (item.getMaterial() == IafToolMaterials.MYRMEX_CHITIN_TOOL_MATERIAL) {
            if (target.getType().isIn(EntityTypeTags.ARTHROPOD))
                target.damage(attacker.getWorld().getDamageSources().generic(), this.getAttackDamage(item) + 5.0F);
            if (target instanceof EntityDeathWorm)
                target.damage(attacker.getWorld().getDamageSources().generic(), this.getAttackDamage(item) + 5.0F);
        }
    }

    default void appendHoverText(ToolMaterial tier, List<Text> tooltip) {
        if (tier == IafToolMaterials.SILVER_TOOL_MATERIAL)
            tooltip.add(Text.translatable("silvertools.hurt").formatted(Formatting.GREEN));
        if (tier == IafToolMaterials.MYRMEX_CHITIN_TOOL_MATERIAL)
            tooltip.add(Text.translatable("myrmextools.hurt").formatted(Formatting.GREEN));
    }
}
