package com.iafenvoy.iceandfire.item.ability;

import net.minecraft.entity.LivingEntity;

public interface TakeKnockbackAbility extends PostHitAbility {
    @Override
    default void active(LivingEntity target, LivingEntity attacker) {
        target.takeKnockback(1F, attacker.getX() - target.getX(), attacker.getZ() - target.getZ());
    }
}
