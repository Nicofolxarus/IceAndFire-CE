package com.iafenvoy.iceandfire.world.processor;

import com.iafenvoy.iceandfire.registry.IafProcessors;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.StructureTemplate;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.WorldView;

public class GraveyardProcessor extends StructureProcessor {
    public static final GraveyardProcessor INSTANCE = new GraveyardProcessor();
    public static final MapCodec<GraveyardProcessor> CODEC = MapCodec.unit(() -> INSTANCE);

    public static BlockState getRandomCobblestone(Random random) {
        float rand = random.nextFloat();
        if (rand < 0.5) return Blocks.COBBLESTONE.getDefaultState();
        else if (rand < 0.9) return Blocks.MOSSY_COBBLESTONE.getDefaultState();
        else return Blocks.INFESTED_COBBLESTONE.getDefaultState();
    }

    public static BlockState getRandomCrackedBlock(Random random) {
        float rand = random.nextFloat();
        if (rand < 0.5) return Blocks.STONE_BRICKS.getDefaultState();
        else if (rand < 0.9) return Blocks.CRACKED_STONE_BRICKS.getDefaultState();
        else return Blocks.MOSSY_STONE_BRICKS.getDefaultState();
    }

    @Override
    public StructureTemplate.StructureBlockInfo process(WorldView world, BlockPos pos, BlockPos pivot, StructureTemplate.StructureBlockInfo originalBlockInfo, StructureTemplate.StructureBlockInfo currentBlockInfo, StructurePlacementData data) {
        Random random = data.getRandom(currentBlockInfo.pos());
        if (currentBlockInfo.state().getBlock() == Blocks.STONE_BRICKS) {
            BlockState state = getRandomCrackedBlock(random);
            return new StructureTemplate.StructureBlockInfo(currentBlockInfo.pos(), state, null);
        }
        if (currentBlockInfo.state().getBlock() == Blocks.COBBLESTONE) {
            BlockState state = getRandomCobblestone(random);
            return new StructureTemplate.StructureBlockInfo(currentBlockInfo.pos(), state, null);
        }
        return currentBlockInfo;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return IafProcessors.GRAVEYARD_PROCESSOR.get();
    }
}
