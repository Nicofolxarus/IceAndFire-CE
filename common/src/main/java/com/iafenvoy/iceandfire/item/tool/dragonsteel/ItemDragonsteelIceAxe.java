package com.iafenvoy.iceandfire.item.tool.dragonsteel;

import com.iafenvoy.iceandfire.item.ability.ActivePostHitAxeItem;
import com.iafenvoy.iceandfire.item.ability.DragonsteelIceAbility;
import com.iafenvoy.iceandfire.registry.IafToolMaterials;
import net.minecraft.component.DataComponentTypes;

public class ItemDragonsteelIceAxe extends ActivePostHitAxeItem implements DragonsteelIceAbility {
    public ItemDragonsteelIceAxe() {
        super(
            IafToolMaterials.DRAGONSTEEL_FIRE,
            new Settings().component(
                DataComponentTypes.ATTRIBUTE_MODIFIERS,
                createAttributeModifiers(
                    IafToolMaterials.DRAGONSTEEL_FIRE,
                    5.0F, -3.0F
                )
            )
        );
    }
}
