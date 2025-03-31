package com.iafenvoy.iceandfire.item.ability.impl;

import com.iafenvoy.iceandfire.item.ability.PostHitAbility;
import net.minecraft.entity.LivingEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class MyrmexTool implements PostHitAbility {
    @Override
    public void active(LivingEntity target, LivingEntity attacker) {
        AbilityImpls.ARTHROPOD_DAMAGE_BONUS.active(target, attacker);
        AbilityImpls.DEATHWORM_DAMAGE_BONUS.active(target, attacker);
    }

    @Override
    public void addDescription(List<Text> tooltip) {
        tooltip.add(Text.translatable("myrmextools.hurt").formatted(Formatting.GREEN));
    }
}
