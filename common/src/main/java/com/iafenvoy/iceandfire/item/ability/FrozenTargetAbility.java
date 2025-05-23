package com.iafenvoy.iceandfire.item.ability;

import com.iafenvoy.iceandfire.data.component.IafEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public interface FrozenTargetAbility extends PostHitAbility {
    int getDuration();

    @Override
    default void active(LivingEntity target, LivingEntity attacker) {
        IafEntityData data = IafEntityData.get(target);
        data.frozenData.setFrozen(target, this.getDuration());
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, this.getDuration(), 2));
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, this.getDuration(), 2));
    }
}
