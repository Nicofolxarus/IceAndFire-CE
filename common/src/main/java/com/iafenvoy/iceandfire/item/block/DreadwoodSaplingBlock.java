package com.iafenvoy.iceandfire.item.block;

import com.iafenvoy.iceandfire.registry.IafFeatures;
import net.minecraft.block.Blocks;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.SaplingGenerator;

import java.util.Optional;

public class DreadwoodSaplingBlock extends SaplingBlock {
    public DreadwoodSaplingBlock() {
        super(new SaplingGenerator("dread_wood", 0.1F, Optional.empty(), Optional.empty(), Optional.of(IafFeatures.DREADWOOD), Optional.of(IafFeatures.DREADWOOD_LARGE), Optional.empty(), Optional.empty()), Settings.copy(Blocks.OAK_SAPLING));
    }
}
