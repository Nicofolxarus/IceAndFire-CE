package com.iafenvoy.iceandfire.item.block;

import com.iafenvoy.iceandfire.item.block.util.DragonProof;
import com.iafenvoy.iceandfire.item.block.util.DreadBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

public class DreadBaseBlock extends GenericBlock implements DragonProof, DreadBlock {
    public DreadBaseBlock(boolean plank) {
        super(Settings.copy(plank ? Blocks.OAK_PLANKS : Blocks.STONE));
        this.setDefaultState(this.getStateManager().getDefaultState().with(UNBREAKABLE, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(UNBREAKABLE);
    }

    @Override
    public float calcBlockBreakingDelta(BlockState state, PlayerEntity player, BlockView world, BlockPos pos) {
        return state.get(UNBREAKABLE) ? 0 : super.calcBlockBreakingDelta(state, player, world, pos);
    }
}
