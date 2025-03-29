package com.iafenvoy.iceandfire.item.tool.dragonsteel;

import com.iafenvoy.iceandfire.item.ability.ActivePostHitShovelItem;
import com.iafenvoy.iceandfire.item.ability.DragonsteelIceAbility;
import com.iafenvoy.iceandfire.registry.IafToolMaterials;
import net.minecraft.component.DataComponentTypes;

public class ItemDragonsteelIceShovel extends ActivePostHitShovelItem implements DragonsteelIceAbility {
    public ItemDragonsteelIceShovel() {
        super(
            IafToolMaterials.DRAGONSTEEL_ICE,
            new Settings().component(
                DataComponentTypes.ATTRIBUTE_MODIFIERS,
                createAttributeModifiers(
                    IafToolMaterials.DRAGONSTEEL_ICE,
                    1.5F, -3.0F
                )
            )
        );
    }
}
