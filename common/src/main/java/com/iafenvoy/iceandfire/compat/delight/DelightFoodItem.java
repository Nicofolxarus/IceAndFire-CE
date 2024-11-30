package com.iafenvoy.iceandfire.compat.delight;

import dev.architectury.platform.Platform;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DelightFoodItem extends Item {
    public DelightFoodItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        if (!Platform.isModLoaded("farmersdelight"))
            tooltip.add(Text.translatable("item.iceandfire.tooltip.require.delight"));
    }
}
