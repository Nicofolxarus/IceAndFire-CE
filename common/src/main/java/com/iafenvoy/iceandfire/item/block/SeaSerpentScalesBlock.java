package com.iafenvoy.iceandfire.item.block;

import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class SeaSerpentScalesBlock extends Block {
    final Formatting color;
    final String name;

    public SeaSerpentScalesBlock(String name, Formatting color) {
        super(Settings.create().mapColor(MapColor.STONE_GRAY).strength(30F, 500F).sounds(BlockSoundGroup.STONE).requiresTool());
        this.color = color;
        this.name = name;
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType options) {
        super.appendTooltip(stack, context, tooltip, options);
        tooltip.add(Text.translatable("sea_serpent." + this.name).formatted(this.color));
    }
}
