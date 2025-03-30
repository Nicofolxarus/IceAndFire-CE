package com.iafenvoy.iceandfire.item.ability.impl;

import com.iafenvoy.iceandfire.config.IafCommonConfig;
import com.iafenvoy.iceandfire.item.ability.IgniteTargetAbility;
import net.minecraft.text.Text;

import java.util.List;

public record IgniteTargetAbilityImpl(int fireTime) implements IgniteTargetAbility {
    @Override
    public boolean isEnable() {
        return IafCommonConfig.INSTANCE.armors.dragonFireAbility.getValue();
    }

    @Override
    public void addDescription(List<Text> tooltip) {}

    @Override
    public int getFireTime() {
        return fireTime;
    }
}
