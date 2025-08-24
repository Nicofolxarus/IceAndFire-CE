package com.iafenvoy.iceandfire.item.block;

import com.iafenvoy.iceandfire.item.block.util.DragonProof;
import com.iafenvoy.iceandfire.item.block.util.DreadBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.sound.BlockSoundGroup;

public class DreadWoodLogBlock extends PillarBlock implements DragonProof, DreadBlock {
    public DreadWoodLogBlock() {
        super(Settings.create().mapColor(MapColor.OAK_TAN).instrument(NoteBlockInstrument.BASS).burnable().strength(2F, 10000F).sounds(BlockSoundGroup.WOOD));
    }
}
