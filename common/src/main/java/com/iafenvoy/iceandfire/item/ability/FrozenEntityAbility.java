package com.iafenvoy.iceandfire.item.ability;

import com.iafenvoy.iceandfire.config.IafCommonConfig;
import com.iafenvoy.iceandfire.data.component.IafEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public interface FrozenEntityAbility extends Ability {
    @Override
    default void active(LivingEntity target, LivingEntity attacker) {
        if (IafCommonConfig.INSTANCE.armors.dragonIceAbility.getValue()) {
            IafEntityData data = IafEntityData.get(target);
            data.frozenData.setFrozen(target, 300);
            target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 300, 2));
            target.takeKnockback(1F, attacker.getX() - target.getX(), attacker.getZ() - target.getZ());
        }
    };

    @Override
    default void addDescription(List<Text> tooltip) {
        if (IafCommonConfig.INSTANCE.armors.dragonIceAbility.getValue()) {
            tooltip.add(Text.translatable("dragon_sword_ice.hurt2").formatted(Formatting.AQUA));
        }
    }
}
