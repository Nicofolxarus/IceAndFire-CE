package com.iafenvoy.iceandfire.item.ability.impl;

import com.iafenvoy.iceandfire.item.ability.DamageBonusAbility;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.text.Text;

import java.util.List;

public record DamageBonusAbilityImpl(float bonus, TagKey<EntityType<?>> targetType, Text tooltip) implements DamageBonusAbility {

    @Override
    public float bonus() {
        return bonus;
    }

    @Override
    public TagKey<EntityType<?>> targetType() {
        return targetType;
    }

    @Override
    public boolean isEnable() {
        return true;
    }

    @Override
    public void addDescription(List<Text> tooltip) {
        tooltip.add(this.tooltip);
    }
}
