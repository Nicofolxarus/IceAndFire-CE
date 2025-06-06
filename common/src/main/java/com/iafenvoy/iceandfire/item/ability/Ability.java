package com.iafenvoy.iceandfire.item.ability;

import net.minecraft.text.Text;

import java.util.List;

public interface Ability {
    default boolean isEnable() {
        return true;
    }

    default void addDescription(List<Text> tooltip) {}
}
