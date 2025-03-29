package com.iafenvoy.iceandfire.item.ability;

import net.minecraft.entity.LivingEntity;
import net.minecraft.text.Text;

import java.util.List;

public interface Ability {
    boolean isEnable();
    void active(LivingEntity target, LivingEntity attacker);
    void addDescription(List<Text> tooltip);
}
