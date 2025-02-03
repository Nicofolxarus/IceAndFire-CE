package com.iafenvoy.iceandfire.item.block;

import com.iafenvoy.iceandfire.entity.block.BlockEntityMyrmexCocoon;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.MapColor;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockMyrmexCocoon extends BlockWithEntity {
    private static final MapCodec<? extends BlockWithEntity> CODEC = createCodec(s -> new BlockMyrmexCocoon());

    public BlockMyrmexCocoon() {
        super(Settings.create().mapColor(MapColor.DIRT_BROWN).strength(2.5F).nonOpaque().dynamicBounds().sounds(BlockSoundGroup.SLIME));
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public void onStateReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        BlockEntity tileentity = worldIn.getBlockEntity(pos);
        if (tileentity instanceof Inventory) {
            ItemScatterer.spawn(worldIn, pos, (Inventory) tileentity);
            worldIn.updateComparators(pos, this);
        }
        super.onStateReplaced(state, worldIn, pos, newState, isMoving);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (!player.isSneaking()) {
            if (!world.isClient) {
                NamedScreenHandlerFactory screenHandlerFactory = this.createScreenHandlerFactory(state, world, pos);
                if (screenHandlerFactory != null)
                    player.openHandledScreen(screenHandlerFactory);
            }
            return ActionResult.SUCCESS;
        }
        return ActionResult.FAIL;
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new BlockEntityMyrmexCocoon(pos, state);
    }
}
