package com.iafenvoy.iceandfire.item.block;

import com.iafenvoy.iceandfire.data.DragonType;
import com.iafenvoy.iceandfire.item.block.entity.DragonForgeBlockEntity;
import com.iafenvoy.iceandfire.item.block.entity.DragonForgeInputBlockEntity;
import com.iafenvoy.iceandfire.item.block.util.DragonProof;
import com.iafenvoy.iceandfire.registry.IafBlockEntities;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class DragonForgeInputBlock extends BlockWithEntity implements DragonProof {
    public static final BooleanProperty ACTIVE = BooleanProperty.of("active");
    private final DragonType dragonType;

    public DragonForgeInputBlock(DragonType dragonType) {
        super(Settings.create().mapColor(MapColor.STONE_GRAY).instrument(NoteBlockInstrument.BASEDRUM).dynamicBounds().strength(40, 500).sounds(BlockSoundGroup.METAL));
        this.dragonType = dragonType;
        this.setDefaultState(this.getStateManager().getDefaultState().with(ACTIVE, Boolean.FALSE));
    }

    public static String name(DragonType dragonType) {
        return "dragonforge_%s_input".formatted(dragonType.name());
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (this.getConnectedTileEntity(world, pos) != null) {
            DragonForgeBlockEntity forge = this.getConnectedTileEntity(world, pos);
            if (forge != null && forge.getPropertyDelegate().fireType == this.dragonType.getIntFromType()) {
                if (!world.isClient) {
                    NamedScreenHandlerFactory inamedcontainerprovider = this.createScreenHandlerFactory(forge.getCachedState(), world, forge.getPos());
                    if (inamedcontainerprovider != null) {
                        player.openHandledScreen(inamedcontainerprovider);
                    }
                }
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.SUCCESS;
    }

    private DragonForgeBlockEntity getConnectedTileEntity(World world, BlockPos pos) {
        for (Direction facing : Direction.values())
            if (world.getBlockEntity(pos.offset(facing)) != null && world.getBlockEntity(pos.offset(facing)) instanceof DragonForgeBlockEntity)
                return (DragonForgeBlockEntity) world.getBlockEntity(pos.offset(facing));
        return null;
    }

    public BlockState getStateFromMeta(int meta) {
        return this.getDefaultState().with(ACTIVE, meta > 0);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    public int getMetaFromState(BlockState state) {
        return state.get(ACTIVE) ? 1 : 0;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(ACTIVE);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> entityType) {
        return world.isClient ? null : validateTicker(entityType, IafBlockEntities.DRAGONFORGE_INPUT.get(), DragonForgeInputBlockEntity::tick);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new DragonForgeInputBlockEntity(pos, state);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return MapCodec.unit(this);
    }
}
