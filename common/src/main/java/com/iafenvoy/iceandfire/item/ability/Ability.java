package com.iafenvoy.iceandfire.item.ability;

import net.minecraft.text.Text;

import java.util.List;

public interface Ability {
    boolean isEnable();
    void addDescription(List<Text> tooltip);
}
