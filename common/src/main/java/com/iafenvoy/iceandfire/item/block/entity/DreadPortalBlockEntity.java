package com.iafenvoy.iceandfire.item.block.entity;

import com.iafenvoy.iceandfire.registry.IafBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class DreadPortalBlockEntity extends BlockEntity {
    public DreadPortalBlockEntity(BlockPos pos, BlockState state) {
        super(IafBlockEntities.DREAD_PORTAL.get(), pos, state);
    }
}
