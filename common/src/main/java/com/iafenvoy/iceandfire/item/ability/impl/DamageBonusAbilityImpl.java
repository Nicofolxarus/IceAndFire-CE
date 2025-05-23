package com.iafenvoy.iceandfire.item.ability.impl;

import com.iafenvoy.iceandfire.item.ability.DamageBonusAbility;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public record DamageBonusAbilityImpl(float bonus, TagKey<EntityType<?>> targetType, @Nullable Text tooltip) implements DamageBonusAbility {

    @Override
    public float bonus() {
        return this.bonus;
    }

    @Override
    public TagKey<EntityType<?>> targetType() {
        return this.targetType;
    }

    @Override
    public void addDescription(List<Text> tooltip) {
        if (this.tooltip != null) {
            tooltip.add(this.tooltip);
        }
    }
}
