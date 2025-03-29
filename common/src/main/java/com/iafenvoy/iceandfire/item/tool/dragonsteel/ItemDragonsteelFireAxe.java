package com.iafenvoy.iceandfire.item.tool.dragonsteel;

import com.iafenvoy.iceandfire.item.ability.ActivePostHitAxeItem;
import com.iafenvoy.iceandfire.item.ability.DragonsteelFireAbility;
import com.iafenvoy.iceandfire.registry.IafToolMaterials;
import net.minecraft.component.DataComponentTypes;

public class ItemDragonsteelFireAxe extends ActivePostHitAxeItem implements DragonsteelFireAbility {
    public ItemDragonsteelFireAxe() {
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
