package com.iafenvoy.iceandfire.item.tool.dragonsteel;

import com.iafenvoy.iceandfire.item.ability.ActivePostHitHoeItem;
import com.iafenvoy.iceandfire.item.ability.DragonsteelLightningAbility;
import com.iafenvoy.iceandfire.registry.IafToolMaterials;
import net.minecraft.component.DataComponentTypes;

public class ItemDragonsteelLightningHoe extends ActivePostHitHoeItem implements DragonsteelLightningAbility {
    public ItemDragonsteelLightningHoe() {
        super(
            IafToolMaterials.DRAGONSTEEL_LIGHTNING,
            new Settings().component(
                DataComponentTypes.ATTRIBUTE_MODIFIERS,
                createAttributeModifiers(
                    IafToolMaterials.DRAGONSTEEL_LIGHTNING,
                    -4.0F, 0.0F
                )
            )
        );
    }
}
