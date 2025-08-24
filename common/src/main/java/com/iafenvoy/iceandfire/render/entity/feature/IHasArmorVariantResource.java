package com.iafenvoy.iceandfire.render.entity.feature;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.Identifier;

public interface IHasArmorVariantResource {
    Identifier getArmorResource(int variant, EquipmentSlot slotType);
}
