package com.iafenvoy.iceandfire.item.tool.dragonsteel;

import com.iafenvoy.iceandfire.item.ability.ActivePostHitPickaxeItem;
import com.iafenvoy.iceandfire.item.ability.DragonsteelLightningAbility;
import com.iafenvoy.iceandfire.registry.IafToolMaterials;
import net.minecraft.component.DataComponentTypes;

public class ItemDragonsteelLightningPickaxe extends ActivePostHitPickaxeItem implements DragonsteelLightningAbility {
    public ItemDragonsteelLightningPickaxe() {
        super(
            IafToolMaterials.DRAGONSTEEL_LIGHTNING,
            new Settings().component(
                DataComponentTypes.ATTRIBUTE_MODIFIERS,
                createAttributeModifiers(
                    IafToolMaterials.DRAGONSTEEL_LIGHTNING,
                    1.0F, -2.8F
                )
            )
        );
    }
}
