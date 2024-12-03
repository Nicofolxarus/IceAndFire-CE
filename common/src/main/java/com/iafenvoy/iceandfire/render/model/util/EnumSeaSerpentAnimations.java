package com.iafenvoy.iceandfire.render.model.util;

import com.iafenvoy.iceandfire.IceAndFire;
import net.minecraft.util.Identifier;

public enum EnumSeaSerpentAnimations {
    T_POSE("base"),
    SWIM1("swim1"),
    SWIM2("swim2"),
    SWIM3("swim3"),
    SWIM4("swim4"),
    SWIM5("swim5"),
    SWIM6("swim6"),
    BITE1("bite1"),
    BITE2("bite2"),
    BITE3("bite3"),
    ROAR1("roar1"),
    ROAR2("roar2"),
    ROAR3("roar3"),
    DEAD("dead"),
    JUMPING1("jumping1"),
    JUMPING2("jumping2");

    private final String fileSuffix;

    EnumSeaSerpentAnimations(String fileSuffix) {
        this.fileSuffix = fileSuffix;
    }

    public Identifier getModelId() {
        return new Identifier(IceAndFire.MOD_ID, "seaserpent/seaserpent_" + this.fileSuffix);
    }
}
