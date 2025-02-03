package com.iafenvoy.iceandfire.item.block;

import com.iafenvoy.iceandfire.entity.block.BlockEntityPixieHouse;
import com.iafenvoy.iceandfire.registry.IafBlockEntities;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class BlockPixieHouse extends BlockWithEntity {
    private static final MapCodec<? extends BlockWithEntity> CODEC = createCodec(s -> new BlockPixieHouse());
    public static final DirectionProperty FACING = DirectionProperty.of("facing", Direction.Type.HORIZONTAL);

    public BlockPixieHouse() {
        super(Settings.create().mapColor(MapColor.OAK_TAN).instrument(NoteBlockInstrument.BASS).burnable().nonOpaque().dynamicBounds().strength(2.0F, 5.0F).ticksRandomly());
        this.setDefaultState(this.getStateManager().getDefaultState().with(FACING, Direction.NORTH));
    }

    public static String name(String type) {
        return "pixie_house_%s".formatted(type);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        return this.getDefaultState().with(FACING, context.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    public void onStateReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        this.dropPixie(worldIn, pos);
        dropStack(worldIn, pos, new ItemStack(this, 0));
        super.onStateReplaced(state, worldIn, pos, newState, isMoving);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    public void dropPixie(World world, BlockPos pos) {
        if (world.getBlockEntity(pos) != null && world.getBlockEntity(pos) instanceof BlockEntityPixieHouse house && house.hasPixie)
            house.releasePixie();
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World level, BlockState state, BlockEntityType<T> entityType) {
        return level.isClient ? validateTicker(entityType, IafBlockEntities.PIXIE_HOUSE.get(), BlockEntityPixieHouse::tickClient) : validateTicker(entityType, IafBlockEntities.PIXIE_HOUSE.get(), BlockEntityPixieHouse::tickServer);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new BlockEntityPixieHouse(pos, state);
    }
}
