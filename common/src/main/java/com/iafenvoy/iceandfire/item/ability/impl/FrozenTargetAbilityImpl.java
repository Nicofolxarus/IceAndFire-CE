package com.iafenvoy.iceandfire.item.ability.impl;

import com.iafenvoy.iceandfire.config.IafCommonConfig;
import com.iafenvoy.iceandfire.item.ability.FrozenTargetAbility;
import net.minecraft.text.Text;

import java.util.List;

public record FrozenTargetAbilityImpl(int duration) implements FrozenTargetAbility {
    @Override
    public int getDuration() {
        return duration;
    }
}
