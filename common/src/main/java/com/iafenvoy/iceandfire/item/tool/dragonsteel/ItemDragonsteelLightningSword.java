package com.iafenvoy.iceandfire.item.tool.dragonsteel;

import com.iafenvoy.iceandfire.item.ability.ActivePostHitSwordItem;
import com.iafenvoy.iceandfire.item.ability.DragonsteelLightningAbility;
import com.iafenvoy.iceandfire.registry.IafToolMaterials;
import net.minecraft.component.DataComponentTypes;

public class ItemDragonsteelLightningSword extends ActivePostHitSwordItem implements DragonsteelLightningAbility {
    public ItemDragonsteelLightningSword() {
        super(
            IafToolMaterials.DRAGONSTEEL_LIGHTNING,
            new Settings().component(
                DataComponentTypes.ATTRIBUTE_MODIFIERS,
                createAttributeModifiers(
                    IafToolMaterials.DRAGONSTEEL_LIGHTNING,
                    3, -2.4F
                )
            )
        );
    }
}
