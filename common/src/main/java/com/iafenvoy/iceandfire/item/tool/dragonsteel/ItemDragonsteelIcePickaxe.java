package com.iafenvoy.iceandfire.item.tool.dragonsteel;

import com.iafenvoy.iceandfire.item.ability.ActivePostHitPickaxeItem;
import com.iafenvoy.iceandfire.item.ability.DragonsteelIceAbility;
import com.iafenvoy.iceandfire.registry.IafToolMaterials;
import net.minecraft.component.DataComponentTypes;

public class ItemDragonsteelIcePickaxe extends ActivePostHitPickaxeItem implements DragonsteelIceAbility {
    public ItemDragonsteelIcePickaxe() {
        super(
            IafToolMaterials.DRAGONSTEEL_ICE,
            new Settings().component(
                DataComponentTypes.ATTRIBUTE_MODIFIERS,
                createAttributeModifiers(
                    IafToolMaterials.DRAGONSTEEL_ICE,
                    1.0F, -2.8F
                )
            )
        );
    }
}
