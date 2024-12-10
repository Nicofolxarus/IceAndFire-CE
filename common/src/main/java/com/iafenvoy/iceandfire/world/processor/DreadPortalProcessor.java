package com.iafenvoy.iceandfire.world.processor;

import com.iafenvoy.iceandfire.registry.IafBlocks;
import com.iafenvoy.iceandfire.registry.IafProcessors;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.StructureTemplate;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.WorldView;

public class DreadPortalProcessor extends StructureProcessor {
    public static final DreadPortalProcessor INSTANCE = new DreadPortalProcessor();
    public static final Codec<DreadPortalProcessor> CODEC = Codec.unit(() -> INSTANCE);

    public static BlockState getRandomCrackedBlock(BlockState prev, Random random) {
        float rand = random.nextFloat();
        if (rand < 0.3) return IafBlocks.DREAD_STONE_BRICKS.get().getDefaultState();
        else if (rand < 0.6) return IafBlocks.DREAD_STONE_BRICKS_CRACKED.get().getDefaultState();
        else return IafBlocks.DREAD_STONE_BRICKS_MOSSY.get().getDefaultState();
    }

    @Override
    public StructureTemplate.StructureBlockInfo process(WorldView world, BlockPos pos, BlockPos pivot, StructureTemplate.StructureBlockInfo originalBlockInfo, StructureTemplate.StructureBlockInfo currentBlockInfo, StructurePlacementData data) {
        Random random = data.getRandom(pos);
        float integrity = 1.0F;
        if (random.nextFloat() <= integrity) {
            if (currentBlockInfo.state().isOf(IafBlocks.DREAD_STONE_BRICKS.get())) {
                BlockState state = getRandomCrackedBlock(null, random);
                return new StructureTemplate.StructureBlockInfo(pos, state, null);
            }
            return currentBlockInfo;
        }
        return currentBlockInfo;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return IafProcessors.DREAD_PORTAL_PROCESSOR.get();
    }
}
