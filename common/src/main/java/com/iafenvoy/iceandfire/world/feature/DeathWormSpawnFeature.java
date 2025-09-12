package com.iafenvoy.iceandfire.world.feature;

import com.iafenvoy.iceandfire.config.IafCommonConfig;
import com.iafenvoy.iceandfire.entity.DeathWormEntity;
import com.iafenvoy.iceandfire.registry.IafEntities;
import com.iafenvoy.iceandfire.world.DangerousGeneration;
import com.mojang.serialization.Codec;
import net.minecraft.entity.SpawnReason;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class DeathWormSpawnFeature extends Feature<DefaultFeatureConfig> implements DangerousGeneration {
    public DeathWormSpawnFeature(Codec<DefaultFeatureConfig> configFactoryIn) {
        super(configFactoryIn);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos pos = world.getTopPosition(Heightmap.Type.WORLD_SURFACE_WG, context.getOrigin().add(8, 0, 8));
        if (this.isFarEnoughFromSpawn(world, pos) && context.getRandom().nextDouble() < IafCommonConfig.INSTANCE.deathworm.spawnChance.getValue()) {
            DeathWormEntity deathWorm = IafEntities.DEATH_WORM.get().create(world.toServerWorld());
            assert deathWorm != null;
            deathWorm.setPosition(pos.getX() + 0.5F, pos.getY() + 1, pos.getZ() + 0.5F);
            deathWorm.initialize(world, world.getLocalDifficulty(pos), SpawnReason.CHUNK_GENERATION, null);
            world.spawnEntity(deathWorm);
        }
        return true;
    }
}
