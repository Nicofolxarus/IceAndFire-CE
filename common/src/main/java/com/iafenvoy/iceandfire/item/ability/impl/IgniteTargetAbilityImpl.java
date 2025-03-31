package com.iafenvoy.iceandfire.item.ability.impl;

import com.iafenvoy.iceandfire.config.IafCommonConfig;
import com.iafenvoy.iceandfire.item.ability.IgniteTargetAbility;
import net.minecraft.text.Text;

import java.util.List;

public record IgniteTargetAbilityImpl(int fireTime) implements IgniteTargetAbility {
    @Override
    public int getFireTime() {
        return fireTime;
    }
}
