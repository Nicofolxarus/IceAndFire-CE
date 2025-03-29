package com.iafenvoy.iceandfire.item.ability;

import com.iafenvoy.iceandfire.registry.IafDamageTypes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.EntityTypeTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public interface DamageBonusAbility extends PostHitAbility {
    float bonus();
    TagKey<EntityType<?>> targetType();

    @Override
    default void active(LivingEntity target, LivingEntity attacker) {
        if (target.getType().isIn(EntityTypeTags.UNDEAD)) {
            target.damage(
                IafDamageTypes.BonusDamage(attacker),
                bonus()
            );
        }
    }

    //@Override
    //default void addDescription(List<Text> tooltip) {
    //    tooltip.add(Text.translatable("silvertools.hurt").formatted(Formatting.GREEN));
    //}
}
