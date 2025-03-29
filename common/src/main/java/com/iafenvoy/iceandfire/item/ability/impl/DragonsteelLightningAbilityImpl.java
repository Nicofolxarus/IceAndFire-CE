package com.iafenvoy.iceandfire.item.ability.impl;

import com.iafenvoy.iceandfire.config.IafCommonConfig;
import com.iafenvoy.iceandfire.item.ability.SummonLightningAbility;

public class DragonsteelLightningAbilityImpl extends AbilityImpl implements SummonLightningAbility {
    @Override
    public boolean isEnable() {
        return IafCommonConfig.INSTANCE.armors.dragonIceAbility.getValue();
    }
}
