package com.iafenvoy.iceandfire.item.ability;

import net.minecraft.entity.LivingEntity;
import net.minecraft.text.Text;

import java.util.List;

public interface Ability {
    void activeAbility(LivingEntity target, LivingEntity attacker);
    void addAbilityDescription(List<Text> tooltip);
}
