package com.iafenvoy.iceandfire.world.feature;

import com.iafenvoy.iceandfire.config.IafCommonConfig;
import com.iafenvoy.iceandfire.entity.StymphalianBirdEntity;
import com.iafenvoy.iceandfire.registry.IafEntities;
import com.iafenvoy.iceandfire.world.DangerousGeneration;
import com.mojang.serialization.Codec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Heightmap;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class StymphalianBirdSpawnFeature extends Feature<DefaultFeatureConfig> implements DangerousGeneration {
    public StymphalianBirdSpawnFeature(Codec<DefaultFeatureConfig> configFactoryIn) {
        super(configFactoryIn);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        StructureWorldAccess world = context.getWorld();
        Random random = context.getRandom();
        BlockPos pos = world.getTopPosition(Heightmap.Type.WORLD_SURFACE_WG, context.getOrigin().add(8, 0, 8));
        if (this.isFarEnoughFromSpawn(world, pos) && random.nextDouble() < IafCommonConfig.INSTANCE.stymphalianBird.spawnChance.getValue())
            for (int i = 0; i < 4 + random.nextInt(4); i++) {
                BlockPos spawnPos = world.getTopPosition(Heightmap.Type.WORLD_SURFACE_WG, pos.add(random.nextInt(10) - 5, 0, random.nextInt(10) - 5));
                if (world.getBlockState(spawnPos.down()).isOpaque()) {
                    StymphalianBirdEntity bird = IafEntities.STYMPHALIAN_BIRD.get().create(world.toServerWorld());
                    assert bird != null;
                    bird.refreshPositionAndAngles(spawnPos.getX() + 0.5F, spawnPos.getY() + 1.5F, spawnPos.getZ() + 0.5F, 0, 0);
                    world.spawnEntity(bird);
                }
            }
        return true;
    }
}
