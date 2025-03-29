package com.iafenvoy.iceandfire.item.tool.dragonsteel;

import com.iafenvoy.iceandfire.item.ability.ActivePostHitSwordItem;
import com.iafenvoy.iceandfire.item.ability.DragonsteelIceAbility;
import com.iafenvoy.iceandfire.registry.IafToolMaterials;
import net.minecraft.component.DataComponentTypes;

public class ItemDragonsteelIceSword extends ActivePostHitSwordItem implements DragonsteelIceAbility {
    public ItemDragonsteelIceSword() {
        super(
            IafToolMaterials.DRAGONSTEEL_ICE,
            new Settings().component(
                DataComponentTypes.ATTRIBUTE_MODIFIERS,
                createAttributeModifiers(
                    IafToolMaterials.DRAGONSTEEL_ICE,
                    3, -2.4F
                )
            )
        );
    }
}
