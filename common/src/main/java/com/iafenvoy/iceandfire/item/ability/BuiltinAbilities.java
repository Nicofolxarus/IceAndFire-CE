package com.iafenvoy.iceandfire.item.ability;

import net.minecraft.registry.tag.EntityTypeTags;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public final class BuiltinAbilities {
    public static final PostHitAbility TAKE_KNOCKBACK = new TakeKnockbackAbility();
    public static final PostHitAbility SUMMON_LIGHTNING = new SummonLightningAbility();
    public static final SwingHandAbility SUMMON_GHOST_SWORD = new SummonGhostSwordAbility();
    public static final PostHitAbility UNDEAD_DAMAGE_BONUS = new DamageBonusAbility(2.0F, EntityTypeTags.UNDEAD, Text.translatable("silvertools.hurt").formatted(Formatting.GREEN));

    public static final PostHitAbility ICE_DRAGON_BLOOD_TOOL = new IceDragonBloodToolAbility();
    public static final PostHitAbility FIRE_DRAGON_BLOOD_TOOL = new FireDragonBloodToolAbility();
    public static final PostHitAbility DRAGONSTEEL_FIRE_TOOL = new DragonsteelFireToolAbility();
    public static final PostHitAbility DRAGONSTEEL_ICE_TOOL = new DragonsteelIceToolAbility();
    public static final PostHitAbility DRAGONSTEEL_LIGHTNING_TOOL = new LightningMultihitAbility();
    public static final PostHitAbility LIGHTNING_DRAGON_BLOOD_TOOL = new LightningDragonBloodToolAbility();
}
