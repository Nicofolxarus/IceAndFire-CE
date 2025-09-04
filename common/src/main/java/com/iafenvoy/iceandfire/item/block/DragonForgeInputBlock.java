package com.iafenvoy.iceandfire.item.block;

import com.iafenvoy.iceandfire.data.DragonType;
import com.iafenvoy.iceandfire.item.block.entity.DragonForgeBlockEntity;
import com.iafenvoy.iceandfire.item.block.entity.DragonForgeInputBlockEntity;
import com.iafenvoy.iceandfire.item.block.util.DragonProof;
import com.iafenvoy.iceandfire.registry.IafBlockEntities;
import com.iafenvoy.iceandfire.registry.IafBlocks;
import com.iafenvoy.iceandfire.util.DragonTypeProvider;
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

import java.util.HashMap;
import java.util.Map;

public class DragonForgeInputBlock extends BlockWithEntity implements DragonProof, DragonTypeProvider {
    private static final Map<DragonType, Block> TYPE_MAP = new HashMap<>();
    public static final BooleanProperty ACTIVE = BooleanProperty.of("active");
    private final DragonType dragonType;

    public DragonForgeInputBlock(DragonType dragonType) {
        super(Settings.create().mapColor(MapColor.STONE_GRAY).instrument(NoteBlockInstrument.BASEDRUM).dynamicBounds().strength(40, 500).sounds(BlockSoundGroup.METAL));
        this.dragonType = dragonType;
        this.setDefaultState(this.getStateManager().getDefaultState().with(ACTIVE, Boolean.FALSE));
        TYPE_MAP.put(dragonType, this);
    }

    public static String name(DragonType dragonType) {
        return "dragonforge_%s_input".formatted(dragonType.name());
    }

    public static Block getBlockByType(DragonType type) {
        return TYPE_MAP.getOrDefault(type, IafBlocks.DRAGONFORGE_FIRE_BRICK.get());
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (this.getConnectedTileEntity(world, pos) != null) {
            DragonForgeBlockEntity forge = this.getConnectedTileEntity(world, pos);
            if (forge != null && forge.getDragonType() == this.dragonType) {
                if (!world.isClient) {
                    NamedScreenHandlerFactory factory = this.createScreenHandlerFactory(forge.getCachedState(), world, forge.getPos());
                    if (factory != null)
                        player.openHandledScreen(factory);
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

    @Override
    public DragonType getDragonType() {
        return this.dragonType;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
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
