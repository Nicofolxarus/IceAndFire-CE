package com.iafenvoy.iceandfire.item.ability;

import net.minecraft.entity.LivingEntity;

public interface PostHitAbility extends Ability {
    void active(LivingEntity target, LivingEntity attacker);
}
