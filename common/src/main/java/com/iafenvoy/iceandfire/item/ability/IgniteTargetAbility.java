package com.iafenvoy.iceandfire.item.ability;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

public interface IgniteTargetAbility extends PostHitAbility {
    int getFireTime();

    @Override
    default void active(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (this.isEnable()) {
            target.setOnFireFor(this.getFireTime());
        }
    }
}
