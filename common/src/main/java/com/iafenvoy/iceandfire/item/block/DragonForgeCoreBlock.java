package com.iafenvoy.iceandfire.item.block;

import com.iafenvoy.iceandfire.data.DragonType;
import com.iafenvoy.iceandfire.item.block.entity.DragonForgeBlockEntity;
import com.iafenvoy.iceandfire.item.block.util.DragonProof;
import com.iafenvoy.iceandfire.registry.IafBlockEntities;
import com.iafenvoy.iceandfire.registry.IafBlocks;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.MapColor;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DragonForgeCoreBlock extends BlockWithEntity implements DragonProof {
    private final int isFire;

    public DragonForgeCoreBlock(int isFire, boolean activated) {
        super(Settings.create().mapColor(MapColor.IRON_GRAY).dynamicBounds().strength(40, 500).sounds(BlockSoundGroup.METAL).luminance((state) -> activated ? 15 : 0));
        this.isFire = isFire;
    }

    public static String name(int dragonType, boolean activated) {
        return "dragonforge_%s_core%s".formatted(DragonType.getNameFromInt(dragonType), activated ? "" : "_disabled");
    }

    public static void setState(int dragonType, boolean active, World worldIn, BlockPos pos) {
        BlockEntity blockEntity = worldIn.getBlockEntity(pos);
        if (active)
            switch (dragonType) {
                case 0 -> worldIn.setBlockState(pos, IafBlocks.DRAGONFORGE_FIRE_CORE.get().getDefaultState(), 3);
                case 1 -> worldIn.setBlockState(pos, IafBlocks.DRAGONFORGE_ICE_CORE.get().getDefaultState(), 3);
                case 2 -> worldIn.setBlockState(pos, IafBlocks.DRAGONFORGE_LIGHTNING_CORE.get().getDefaultState(), 3);
            }
        else
            switch (dragonType) {
                case 0 ->
                        worldIn.setBlockState(pos, IafBlocks.DRAGONFORGE_FIRE_CORE_DISABLED.get().getDefaultState(), 3);
                case 1 ->
                        worldIn.setBlockState(pos, IafBlocks.DRAGONFORGE_ICE_CORE_DISABLED.get().getDefaultState(), 3);
                case 2 ->
                        worldIn.setBlockState(pos, IafBlocks.DRAGONFORGE_LIGHTNING_CORE_DISABLED.get().getDefaultState(), 3);
            }
        if (blockEntity != null) {
            blockEntity.cancelRemoval();
            worldIn.addBlockEntity(blockEntity);
        }
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

    public ItemStack getItem(World world, BlockPos pos, BlockState state) {
        return switch (this.isFire) {
            case 1 -> new ItemStack(IafBlocks.DRAGONFORGE_ICE_CORE_DISABLED.get().asItem());
            case 2 -> new ItemStack(IafBlocks.DRAGONFORGE_LIGHTNING_CORE_DISABLED.get().asItem());
            default -> new ItemStack(IafBlocks.DRAGONFORGE_FIRE_CORE_DISABLED.get().asItem());
        };
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean isMoving) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof DragonForgeBlockEntity) {
            ItemScatterer.spawn(world, pos, (DragonForgeBlockEntity) blockEntity);
            world.updateComparators(pos, this);
            world.removeBlockEntity(pos);
        }
    }

    @Override
    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return ScreenHandler.calculateComparatorOutput(world.getBlockEntity(pos));
    }

    @Override
    public boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> entityType) {
        return validateTicker(entityType, IafBlockEntities.DRAGONFORGE_CORE.get(), DragonForgeBlockEntity::tick);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new DragonForgeBlockEntity(pos, state, this.isFire);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return MapCodec.unit(this);
    }
}
