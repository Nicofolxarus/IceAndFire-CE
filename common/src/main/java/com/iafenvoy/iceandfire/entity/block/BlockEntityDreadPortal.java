package com.iafenvoy.iceandfire.entity.block;

import com.iafenvoy.iceandfire.registry.IafBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class BlockEntityDreadPortal extends BlockEntity {
    public BlockEntityDreadPortal(BlockPos pos, BlockState state) {
        super(IafBlockEntities.DREAD_PORTAL.get(), pos, state);
    }
}
