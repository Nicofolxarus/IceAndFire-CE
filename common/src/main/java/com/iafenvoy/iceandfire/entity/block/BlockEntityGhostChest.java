package com.iafenvoy.iceandfire.entity.block;

import com.iafenvoy.iceandfire.entity.EntityGhost;
import com.iafenvoy.iceandfire.registry.IafBlockEntities;
import com.iafenvoy.iceandfire.registry.IafEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;

import java.util.concurrent.ThreadLocalRandom;

public class BlockEntityGhostChest extends ChestBlockEntity {
    public BlockEntityGhostChest(BlockPos pos, BlockState state) {
        super(IafBlockEntities.GHOST_CHEST.get(), pos, state);
    }

    @Override
    public void onOpen(PlayerEntity player) {
        super.onOpen(player);
        assert this.world != null;
        if (this.world.getDifficulty() != Difficulty.PEACEFUL) {
            EntityGhost ghost = IafEntities.GHOST.get().create(this.world);
            assert ghost != null;
            ghost.updatePositionAndAngles(this.pos.getX() + 0.5F, this.pos.getY() + 0.5F, this.pos.getZ() + 0.5F, ThreadLocalRandom.current().nextFloat() * 360F, 0);
            if (this.world instanceof ServerWorld serverWorld) {
                ghost.initialize(serverWorld, this.world.getLocalDifficulty(this.pos), SpawnReason.SPAWNER, null);
                if (!player.isCreative()) ghost.setTarget(player);
                ghost.setPersistent();
                this.world.spawnEntity(ghost);
            }
            ghost.setAnimation(EntityGhost.ANIMATION_SCARE);
            ghost.setPositionTarget(this.pos, 4);
            ghost.setFromChest(true);
        }
    }

    @Override
    protected void onViewerCountUpdate(World level, BlockPos pos, BlockState state, int p_155336_, int p_155337_) {
        super.onViewerCountUpdate(level, pos, state, p_155336_, p_155337_);
        level.updateNeighborsAlways(pos.down(), state.getBlock());
    }
}
