package com.iafenvoy.iceandfire.item.ability;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

public interface PostHitAbility extends Ability {
    void active(ItemStack stack, LivingEntity target, LivingEntity attacker);
}
