package com.iafenvoy.iceandfire.item.ability.impl;

import com.iafenvoy.iceandfire.item.ability.PostHitAbility;
import com.iafenvoy.iceandfire.item.ability.SwingHandAbility;
import net.minecraft.registry.tag.EntityTypeTags;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public abstract class AbilityImpls {
    public static final PostHitAbility IGNITE_TARGET = new IgniteTargetAbilityImpl();
    public static final PostHitAbility FROZEN_TARGET = new FrozenTargetAbilityImpl();
    public static final PostHitAbility SUMMON_LIGHTNING = new SummonLightningAbilityImpl();
    public static final PostHitAbility UNDEAD_DAMAGE_BONUS = new DamageBonusAbilityImpl(2.0F, EntityTypeTags.UNDEAD, Text.translatable("silvertools.hurt").formatted(Formatting.GREEN));
    public static final SwingHandAbility SUMMON_GHOST_SWORD = new SummonGhostSwordImpl();
}
