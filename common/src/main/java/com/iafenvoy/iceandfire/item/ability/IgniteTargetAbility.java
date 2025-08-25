package com.iafenvoy.iceandfire.item.ability;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

public record IgniteTargetAbility(int fireTime) implements PostHitAbility {
    @Override
    public void active(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (this.isEnable()) {
            target.setOnFireFor(this.fireTime);
        }
    }
}
