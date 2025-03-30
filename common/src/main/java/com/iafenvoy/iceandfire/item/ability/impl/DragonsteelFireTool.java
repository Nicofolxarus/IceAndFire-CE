package com.iafenvoy.iceandfire.item.ability.impl;

import com.iafenvoy.iceandfire.config.IafCommonConfig;
import com.iafenvoy.iceandfire.item.ability.PostHitAbility;
import net.minecraft.entity.LivingEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class DragonsteelFireTool implements PostHitAbility {
    @Override
    public void active(LivingEntity target, LivingEntity attacker) {
        if (isEnable()) {
            AbilityImpls.IGNITE_TARGET.active(target, attacker);
            AbilityImpls.TAKE_KNOCKBACK.active(target, attacker);
        }
    }

    @Override
    public boolean isEnable() {
        return IafCommonConfig.INSTANCE.armors.dragonFireAbility.getValue();
    }

    @Override
    public void addDescription(List<Text> tooltip) {
        if (isEnable()) {
            tooltip.add(Text.translatable("dragon_sword_fire.hurt2").formatted(Formatting.DARK_RED));
        }
    }
}
