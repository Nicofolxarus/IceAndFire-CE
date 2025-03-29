package com.iafenvoy.iceandfire.item.ability;

import com.iafenvoy.iceandfire.registry.IafDamageTypes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.registry.tag.TagKey;

public interface DamageBonusAbility extends PostHitAbility {
    float bonus();
    TagKey<EntityType<?>> targetType();

    @Override
    default void active(LivingEntity target, LivingEntity attacker) {
        if (target.getType().isIn(targetType())) {
            target.damage(
                IafDamageTypes.BonusDamage(attacker),
                bonus()
            );
        }
    }
}
