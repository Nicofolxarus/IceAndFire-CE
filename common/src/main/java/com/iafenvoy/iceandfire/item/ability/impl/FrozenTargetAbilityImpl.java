package com.iafenvoy.iceandfire.item.ability.impl;

import com.iafenvoy.iceandfire.config.IafCommonConfig;
import com.iafenvoy.iceandfire.item.ability.FrozenTargetAbility;

public class FrozenTargetAbilityImpl implements FrozenTargetAbility {
    @Override
    public boolean isEnable() {
        return IafCommonConfig.INSTANCE.armors.dragonIceAbility.getValue();
    }
}
