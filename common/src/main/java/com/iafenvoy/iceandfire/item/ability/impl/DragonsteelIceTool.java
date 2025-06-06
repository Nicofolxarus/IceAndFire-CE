package com.iafenvoy.iceandfire.item.ability.impl;

import com.iafenvoy.iceandfire.config.IafCommonConfig;
import com.iafenvoy.iceandfire.item.ability.PostHitAbility;
import net.minecraft.entity.LivingEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class DragonsteelIceTool implements PostHitAbility {
    private final PostHitAbility frozen = new FrozenTargetAbilityImpl(IafCommonConfig.INSTANCE.tools.dragonsteelFrozenDuration.getValue());
    @Override
    public void active(LivingEntity target, LivingEntity attacker) {
        if (this.isEnable()) {
            this.frozen.active(target, attacker);
        }
    }

    @Override
    public boolean isEnable() {
        return IafCommonConfig.INSTANCE.tools.dragonIceAbility.getValue();
    }

    @Override
    public void addDescription(List<Text> tooltip) {
        if (this.isEnable()) {
            tooltip.add(Text.translatable("dragon_sword_ice.hurt2").formatted(Formatting.AQUA));
        }
    }
}
