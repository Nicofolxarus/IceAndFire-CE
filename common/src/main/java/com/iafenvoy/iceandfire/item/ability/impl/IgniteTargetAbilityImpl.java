package com.iafenvoy.iceandfire.item.ability.impl;

import com.iafenvoy.iceandfire.item.ability.IgniteTargetAbility;

public record IgniteTargetAbilityImpl(int fireTime) implements IgniteTargetAbility {
    @Override
    public int getFireTime() {
        return this.fireTime;
    }
}
