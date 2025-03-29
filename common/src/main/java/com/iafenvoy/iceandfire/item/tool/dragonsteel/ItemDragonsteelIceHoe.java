package com.iafenvoy.iceandfire.item.tool.dragonsteel;

import com.iafenvoy.iceandfire.item.ability.ActivePostHitHoeItem;
import com.iafenvoy.iceandfire.item.ability.DragonsteelIceAbility;
import com.iafenvoy.iceandfire.registry.IafToolMaterials;
import net.minecraft.component.DataComponentTypes;

public class ItemDragonsteelIceHoe extends ActivePostHitHoeItem implements DragonsteelIceAbility {
    public ItemDragonsteelIceHoe() {
        super(
            IafToolMaterials.DRAGONSTEEL_ICE,
            new Settings().component(
                DataComponentTypes.ATTRIBUTE_MODIFIERS,
                createAttributeModifiers(
                    IafToolMaterials.DRAGONSTEEL_ICE,
                    -4.0F, 0.0F
                )
            )
        );
    }
}
