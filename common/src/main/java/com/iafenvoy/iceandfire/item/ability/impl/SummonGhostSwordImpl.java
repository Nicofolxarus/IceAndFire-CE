package com.iafenvoy.iceandfire.item.ability.impl;

import com.iafenvoy.iceandfire.config.IafCommonConfig;
import com.iafenvoy.iceandfire.item.ability.SummonGhostSwordAbility;

public class SummonGhostSwordImpl implements SummonGhostSwordAbility {
    @Override
    public boolean isEnable() {
        return IafCommonConfig.INSTANCE.tools.phantasmalBladeAbility.getValue();
    }
}
