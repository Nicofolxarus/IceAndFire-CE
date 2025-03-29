package com.iafenvoy.iceandfire.item.tool.dragonsteel;

import com.iafenvoy.iceandfire.item.ability.ActivePostHitSwordItem;
import com.iafenvoy.iceandfire.item.ability.DragonsteelFireAbility;
import com.iafenvoy.iceandfire.registry.IafToolMaterials;
import net.minecraft.component.DataComponentTypes;

public class ItemDragonsteelFireSword extends ActivePostHitSwordItem implements DragonsteelFireAbility {
    public ItemDragonsteelFireSword() {
        super(
            IafToolMaterials.DRAGONSTEEL_FIRE,
            new Settings().component(
                DataComponentTypes.ATTRIBUTE_MODIFIERS,
                createAttributeModifiers(
                    IafToolMaterials.DRAGONSTEEL_FIRE,
                    3, -2.4F
                )
            )
        );
    }
}
