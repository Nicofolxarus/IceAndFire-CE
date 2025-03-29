package com.iafenvoy.iceandfire.item.ability.impl;

import com.iafenvoy.iceandfire.config.IafCommonConfig;
import com.iafenvoy.iceandfire.item.ability.FrozenEntityAbility;

public class DragonsteelIceAbilityImpl extends AbilityImpl implements FrozenEntityAbility {
    @Override
    public boolean isEnable() {
        return IafCommonConfig.INSTANCE.armors.dragonIceAbility.getValue();
    }
}
