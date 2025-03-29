package com.iafenvoy.iceandfire.item.ability.impl;

import com.iafenvoy.iceandfire.config.IafCommonConfig;
import com.iafenvoy.iceandfire.item.ability.IgniteEntityAbility;

public class DragonsteelFireAbilityImpl extends AbilityImpl implements IgniteEntityAbility {
    @Override
    public boolean isEnable() {
        return IafCommonConfig.INSTANCE.armors.dragonFireAbility.getValue();
    }
}
