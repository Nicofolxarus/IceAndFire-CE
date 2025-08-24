package com.iafenvoy.iceandfire.item.block;

import com.iafenvoy.iceandfire.item.block.entity.DreadSpawnerBlockEntity;
import com.iafenvoy.iceandfire.item.block.util.DreadBlock;
import com.iafenvoy.iceandfire.registry.IafBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.MapColor;
import net.minecraft.block.SpawnerBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DreadSpawnerBlock extends SpawnerBlock implements DreadBlock {
    public DreadSpawnerBlock() {
        super(Settings.create().mapColor(MapColor.STONE_GRAY).instrument(NoteBlockInstrument.BASEDRUM).strength(10.0F, 10000F).sounds(BlockSoundGroup.METAL).nonOpaque().dynamicBounds());
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new DreadSpawnerBlockEntity(pos, state);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return validateTicker(type, IafBlockEntities.DREAD_SPAWNER.get(), world.isClient ? DreadSpawnerBlockEntity::clientTick : DreadSpawnerBlockEntity::serverTick);
    }
}
