package com.iafenvoy.iceandfire.world.processor;

import com.iafenvoy.iceandfire.item.block.util.DreadBlock;
import com.iafenvoy.iceandfire.registry.IafBlocks;
import com.iafenvoy.iceandfire.registry.IafEntities;
import com.iafenvoy.iceandfire.registry.IafProcessors;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.StructureTemplate;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.WorldView;

public class DreadRuinProcessor extends StructureProcessor {
    public static final DreadRuinProcessor INSTANCE = new DreadRuinProcessor();
    public static final MapCodec<DreadRuinProcessor> CODEC = MapCodec.unit(() -> INSTANCE);

    public static BlockState getRandomCrackedBlock(Random random) {
        float rand = random.nextFloat();
        if (rand < 0.5)
            return IafBlocks.DREAD_STONE_BRICKS.get().getDefaultState().with(DreadBlock.UNBREAKABLE, true);
        else if (rand < 0.9)
            return IafBlocks.DREAD_STONE_BRICKS_CRACKED.get().getDefaultState().with(DreadBlock.UNBREAKABLE, true);
        else
            return IafBlocks.DREAD_STONE_BRICKS_MOSSY.get().getDefaultState().with(DreadBlock.UNBREAKABLE, true);
    }

    @Override
    public StructureTemplate.StructureBlockInfo process(WorldView world, BlockPos pos, BlockPos pivot, StructureTemplate.StructureBlockInfo originalBlockInfo, StructureTemplate.StructureBlockInfo currentBlockInfo, StructurePlacementData data) {
        Random random = data.getRandom(currentBlockInfo.pos());
        if (currentBlockInfo.state().getBlock() == IafBlocks.DREAD_STONE_BRICKS.get()) {
            BlockState state = getRandomCrackedBlock(random);
            return new StructureTemplate.StructureBlockInfo(currentBlockInfo.pos(), state, null);
        }
        if (currentBlockInfo.state().getBlock() == IafBlocks.DREAD_SPAWNER.get()) {
            NbtCompound tag = new NbtCompound();
            NbtCompound spawnData = new NbtCompound();
            Identifier spawnerMobId = Registries.ENTITY_TYPE.getId(this.getRandomMobForMobSpawner(random));
            NbtCompound entity = new NbtCompound();
            entity.putString("id", spawnerMobId.toString());
            spawnData.put("entity", entity);
            tag.remove("SpawnPotentials");
            tag.put("SpawnData", spawnData.copy());
            return new StructureTemplate.StructureBlockInfo(currentBlockInfo.pos(), IafBlocks.DREAD_SPAWNER.get().getDefaultState(), tag);
        }
        return currentBlockInfo;

    }

    @Override
    protected StructureProcessorType<?> getType() {
        return IafProcessors.DREAD_MAUSOLEUM_PROCESSOR.get();
    }

    private EntityType<?> getRandomMobForMobSpawner(Random random) {
        float rand = random.nextFloat();
        if (rand < 0.3D) return IafEntities.DREAD_THRALL.get();
        else if (rand < 0.5D) return IafEntities.DREAD_GHOUL.get();
        else if (rand < 0.7D) return IafEntities.DREAD_BEAST.get();
        else if (rand < 0.85D) return IafEntities.DREAD_SCUTTLER.get();
        return IafEntities.DREAD_KNIGHT.get();
    }
}
