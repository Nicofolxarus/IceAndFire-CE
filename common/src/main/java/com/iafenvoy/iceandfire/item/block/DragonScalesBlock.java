package com.iafenvoy.iceandfire.item.block;

import com.iafenvoy.iceandfire.data.DragonColor;
import com.iafenvoy.iceandfire.item.block.util.DragonProof;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.text.Text;

import java.util.List;

public class DragonScalesBlock extends Block implements DragonProof {
    final DragonColor type;

    public DragonScalesBlock(DragonColor type) {
        super(Settings.create().mapColor(MapColor.STONE_GRAY).instrument(NoteBlockInstrument.BASEDRUM).dynamicBounds().strength(30F, 500).sounds(BlockSoundGroup.STONE).requiresTool());
        this.type = type;
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType options) {
        super.appendTooltip(stack, context, tooltip, options);
        tooltip.add(Text.translatable("dragon." + this.type.getName()).formatted(this.type.getColorFormatting()));
    }
}
