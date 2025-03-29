package com.iafenvoy.iceandfire.item.ability.impl;

import com.iafenvoy.iceandfire.config.IafCommonConfig;
import com.iafenvoy.iceandfire.item.ability.IgniteTargetAbility;

public class IgniteTargetAbilityImpl implements IgniteTargetAbility {
    @Override
    public boolean isEnable() {
        return IafCommonConfig.INSTANCE.armors.dragonFireAbility.getValue();
    }
}
