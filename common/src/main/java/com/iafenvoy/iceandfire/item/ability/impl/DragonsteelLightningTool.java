package com.iafenvoy.iceandfire.item.ability.impl;

import com.iafenvoy.iceandfire.config.IafCommonConfig;
import com.iafenvoy.iceandfire.item.ability.PostHitAbility;
import net.minecraft.entity.LivingEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class DragonsteelLightningTool implements PostHitAbility {
    @Override
    public void active(LivingEntity target, LivingEntity attacker) {
        if (isEnable()) {
            AbilityImpls.SUMMON_LIGHTNING.active(target, attacker);
        }
    }

    @Override
    public boolean isEnable() {
        return IafCommonConfig.INSTANCE.tools.dragonLightningAbility.getValue();
    }

    @Override
    public void addDescription(List<Text> tooltip) {
        if (isEnable()) {
            tooltip.add(Text.translatable("dragon_sword_lightning.hurt2").formatted(Formatting.DARK_PURPLE));
        }
    }
}
