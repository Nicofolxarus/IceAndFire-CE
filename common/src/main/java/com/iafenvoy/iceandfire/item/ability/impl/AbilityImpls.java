package com.iafenvoy.iceandfire.item.ability.impl;

import com.iafenvoy.iceandfire.item.ability.PostHitAbility;
import com.iafenvoy.iceandfire.item.ability.SwingHandAbility;
import net.minecraft.registry.tag.EntityTypeTags;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public abstract class AbilityImpls {
    public static final PostHitAbility TAKE_KNOCKBACK = new TakeKnockbackAbilityImpl();
    public static final PostHitAbility SUMMON_LIGHTNING = new SummonLightningAbilityImpl();
    public static final SwingHandAbility SUMMON_GHOST_SWORD = new SummonGhostSwordImpl();
    public static final PostHitAbility UNDEAD_DAMAGE_BONUS = new DamageBonusAbilityImpl(2.0F, EntityTypeTags.UNDEAD, Text.translatable("silvertools.hurt").formatted(Formatting.GREEN));

    public static final PostHitAbility ICE_DRAGON_BLOOD_TOOL = new IceDragonBloodTool();
    public static final PostHitAbility FIRE_DRAGON_BLOOD_TOOL = new FireDragonBloodTool();
    public static final PostHitAbility DRAGONSTEEL_FIRE_TOOL = new DragonsteelFireTool();
    public static final PostHitAbility DRAGONSTEEL_ICE_TOOL = new DragonsteelIceTool();
    public static final PostHitAbility DRAGONSTEEL_LIGHTNING_TOOL = new DragonsteelLightningTool();
    public static final PostHitAbility LIGHTNING_DRAGON_BLOOD_TOOL = new LightningDragonBloodTool();

}
