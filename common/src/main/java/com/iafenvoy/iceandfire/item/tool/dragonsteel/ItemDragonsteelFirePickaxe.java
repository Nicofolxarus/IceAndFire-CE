package com.iafenvoy.iceandfire.item.tool.dragonsteel;

import com.iafenvoy.iceandfire.item.ability.ActivePostHitPickaxeItem;
import com.iafenvoy.iceandfire.item.ability.DragonsteelFireAbility;
import com.iafenvoy.iceandfire.registry.IafToolMaterials;
import net.minecraft.component.DataComponentTypes;

public class ItemDragonsteelFirePickaxe extends ActivePostHitPickaxeItem implements DragonsteelFireAbility {
    public ItemDragonsteelFirePickaxe() {
        super(
            IafToolMaterials.DRAGONSTEEL_FIRE,
            new Settings().component(
                DataComponentTypes.ATTRIBUTE_MODIFIERS,
                createAttributeModifiers(
                    IafToolMaterials.DRAGONSTEEL_FIRE,
                    1.0F, -2.8F
                )
            )
        );
    }
}
