package com.iafenvoy.iceandfire.item.tool.dragonsteel;

import com.iafenvoy.iceandfire.item.ability.ActivePostHitShovelItem;
import com.iafenvoy.iceandfire.item.ability.DragonsteelLightningAbility;
import com.iafenvoy.iceandfire.registry.IafToolMaterials;
import net.minecraft.component.DataComponentTypes;

public class ItemDragonsteelLightningShovel extends ActivePostHitShovelItem implements DragonsteelLightningAbility {
    public ItemDragonsteelLightningShovel() {
        super(
            IafToolMaterials.DRAGONSTEEL_LIGHTNING,
            new Settings().component(
                DataComponentTypes.ATTRIBUTE_MODIFIERS,
                createAttributeModifiers(
                    IafToolMaterials.DRAGONSTEEL_LIGHTNING,
                    1.5F, -3.0F
                )
            )
        );
    }
}
