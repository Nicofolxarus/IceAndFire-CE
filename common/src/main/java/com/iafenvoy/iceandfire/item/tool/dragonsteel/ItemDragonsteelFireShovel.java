package com.iafenvoy.iceandfire.item.tool.dragonsteel;

import com.iafenvoy.iceandfire.item.ability.ActivePostHitShovelItem;
import com.iafenvoy.iceandfire.item.ability.DragonsteelFireAbility;
import com.iafenvoy.iceandfire.registry.IafToolMaterials;
import net.minecraft.component.DataComponentTypes;

public class ItemDragonsteelFireShovel extends ActivePostHitShovelItem implements DragonsteelFireAbility {
    public ItemDragonsteelFireShovel() {
        super(
            IafToolMaterials.DRAGONSTEEL_FIRE,
            new Settings().component(
                DataComponentTypes.ATTRIBUTE_MODIFIERS,
                createAttributeModifiers(
                    IafToolMaterials.DRAGONSTEEL_FIRE,
                    1.5F, -3.0F
                )
            )
        );
    }
}
