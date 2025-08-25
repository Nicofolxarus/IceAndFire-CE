package com.iafenvoy.iceandfire.item.tool;

import com.iafenvoy.iceandfire.item.ability.BuiltinAbilities;
import com.iafenvoy.iceandfire.registry.IafToolMaterials;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class GhostSwordItem extends SwordItem {
    public GhostSwordItem() {
        super(IafToolMaterials.GHOST_SWORD_TOOL_MATERIAL, new Settings().component(DataComponentTypes.ATTRIBUTE_MODIFIERS, createAttributeModifiers(IafToolMaterials.GHOST_SWORD_TOOL_MATERIAL, 5, -1.0F)));
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("item.iceandfire.legendary_weapon.desc").formatted(Formatting.GRAY));
        super.appendTooltip(stack, context, tooltip, type);
        BuiltinAbilities.SUMMON_GHOST_SWORD.addDescription(tooltip);
    }
}
