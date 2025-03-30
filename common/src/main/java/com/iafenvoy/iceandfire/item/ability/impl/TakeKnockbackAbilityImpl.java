package com.iafenvoy.iceandfire.item.ability.impl;

import com.iafenvoy.iceandfire.item.ability.TakeKnockbackAbility;
import net.minecraft.text.Text;

import java.util.List;

public class TakeKnockbackAbilityImpl implements TakeKnockbackAbility {
    @Override
    public boolean isEnable() {
        return true;
    }

    @Override
    public void addDescription(List<Text> tooltip) {}
}
