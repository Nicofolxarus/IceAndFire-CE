package com.iafenvoy.iceandfire.item.tool.dragonsteel;

import com.iafenvoy.iceandfire.item.ability.ActivePostHitHoeItem;
import com.iafenvoy.iceandfire.item.ability.DragonsteelFireAbility;
import com.iafenvoy.iceandfire.registry.IafToolMaterials;
import net.minecraft.component.DataComponentTypes;

public class ItemDragonsteelFireHoe extends ActivePostHitHoeItem implements DragonsteelFireAbility {
    public ItemDragonsteelFireHoe() {
        super(
            IafToolMaterials.DRAGONSTEEL_FIRE,
            new Settings().component(
                DataComponentTypes.ATTRIBUTE_MODIFIERS,
                createAttributeModifiers(
                    IafToolMaterials.DRAGONSTEEL_FIRE,
                    -4.0F, 0.0F
                )
            )
        );
    }
}
