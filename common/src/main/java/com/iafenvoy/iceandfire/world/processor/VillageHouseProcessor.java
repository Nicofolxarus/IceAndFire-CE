package com.iafenvoy.iceandfire.world.processor;

import com.iafenvoy.iceandfire.IceAndFire;
import com.iafenvoy.iceandfire.registry.IafProcessors;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.StructureTemplate;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.WorldView;

public class VillageHouseProcessor extends StructureProcessor {
    public static final Identifier LOOT = Identifier.of(IceAndFire.MOD_ID, "chest/village_scribe");
    public static final VillageHouseProcessor INSTANCE = new VillageHouseProcessor();
    public static final MapCodec<VillageHouseProcessor> CODEC = MapCodec.unit(() -> INSTANCE);

    @Override
    public StructureTemplate.StructureBlockInfo process(WorldView world, BlockPos pos, BlockPos pivot, StructureTemplate.StructureBlockInfo originalBlockInfo, StructureTemplate.StructureBlockInfo currentBlockInfo, StructurePlacementData data) {
        Random random = data.getRandom(currentBlockInfo.pos());
        if (currentBlockInfo.state().getBlock() == Blocks.CHEST) {
            NbtCompound tag = new NbtCompound();
            tag.putString("LootTable", LOOT.toString());
            tag.putLong("LootTableSeed", random.nextLong());
            return new StructureTemplate.StructureBlockInfo(currentBlockInfo.pos(), currentBlockInfo.state(), tag);
        }
        return currentBlockInfo;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return IafProcessors.VILLAGE_HOUSE_PROCESSOR.get();
    }
}
