package com.iafenvoy.iceandfire.item.tool.dragonsteel;

import com.iafenvoy.iceandfire.item.ability.ActivePostHitAxeItem;
import com.iafenvoy.iceandfire.item.ability.DragonsteelLightningAbility;
import com.iafenvoy.iceandfire.registry.IafToolMaterials;
import net.minecraft.component.DataComponentTypes;

public class ItemDragonsteelLightningAxe extends ActivePostHitAxeItem implements DragonsteelLightningAbility {
    public ItemDragonsteelLightningAxe() {
        super(
            IafToolMaterials.DRAGONSTEEL_LIGHTNING,
            new Settings().component(
                DataComponentTypes.ATTRIBUTE_MODIFIERS,
                createAttributeModifiers(
                    IafToolMaterials.DRAGONSTEEL_LIGHTNING,
                    5.0F, -3.0F
                )
            )
        );
    }
}
