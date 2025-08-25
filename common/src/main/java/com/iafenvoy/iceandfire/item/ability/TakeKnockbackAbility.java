package com.iafenvoy.iceandfire.item.ability;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

public interface TakeKnockbackAbility extends PostHitAbility {
    @Override
    default void active(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.takeKnockback(1F, attacker.getX() - target.getX(), attacker.getZ() - target.getZ());
    }
}
