package com.iafenvoy.iceandfire.item.ability;

import com.iafenvoy.iceandfire.data.component.FrozenData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;

public interface FrozenTargetAbility extends PostHitAbility {
    int getDuration();

    @Override
    default void active(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        FrozenData.get(target).setFrozen(target, this.getDuration());
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, this.getDuration(), 2));
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, this.getDuration(), 2));
    }
}
