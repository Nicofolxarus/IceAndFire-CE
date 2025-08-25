package com.iafenvoy.iceandfire.item.ability;

import com.iafenvoy.iceandfire.registry.IafDamageTypes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public record DamageBonusAbility(float bonus, TagKey<EntityType<?>> targetType,
                                 @Nullable Text tooltip) implements PostHitAbility {
    @Override
    public void active(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (attacker instanceof PlayerEntity player && player.getAttackCooldownProgress(0) != 1.0F) return;
        if (target.getType().isIn(this.targetType))
            target.damage(IafDamageTypes.bonusDamage(attacker), this.bonus);
    }

    @Override
    public void addDescription(List<Text> tooltip) {
        if (this.tooltip != null) tooltip.add(this.tooltip);
    }
}
