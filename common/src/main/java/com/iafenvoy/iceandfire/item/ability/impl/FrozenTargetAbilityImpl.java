package com.iafenvoy.iceandfire.item.ability.impl;

import com.iafenvoy.iceandfire.item.ability.FrozenTargetAbility;

public record FrozenTargetAbilityImpl(int duration) implements FrozenTargetAbility {
    @Override
    public int getDuration() {
        return this.duration;
    }
}
