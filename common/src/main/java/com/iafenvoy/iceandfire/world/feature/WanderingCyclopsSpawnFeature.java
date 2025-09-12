package com.iafenvoy.iceandfire.world.feature;

import com.iafenvoy.iceandfire.config.IafCommonConfig;
import com.iafenvoy.iceandfire.entity.CyclopsEntity;
import com.iafenvoy.iceandfire.registry.IafEntities;
import com.iafenvoy.iceandfire.world.DangerousGeneration;
import com.mojang.serialization.Codec;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Heightmap;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class WanderingCyclopsSpawnFeature extends Feature<DefaultFeatureConfig> implements DangerousGeneration {
    public WanderingCyclopsSpawnFeature(Codec<DefaultFeatureConfig> configFactoryIn) {
        super(configFactoryIn);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        StructureWorldAccess world = context.getWorld();
        Random random = context.getRandom();
        BlockPos pos = world.getTopPosition(Heightmap.Type.WORLD_SURFACE_WG, context.getOrigin().add(8, 0, 8));
        if (this.isFarEnoughFromSpawn(world, pos) && random.nextDouble() < IafCommonConfig.INSTANCE.cyclops.spawnWanderingChance.getValue() && random.nextInt(12) == 0) {
            CyclopsEntity cyclops = IafEntities.CYCLOPS.get().create(world.toServerWorld());
            assert cyclops != null;
            cyclops.setPosition(pos.getX() + 0.5F, pos.getY() + 1, pos.getZ() + 0.5F);
            cyclops.initialize(world, world.getLocalDifficulty(pos), SpawnReason.SPAWNER, null);
            world.spawnEntity(cyclops);
            for (int i = 0; i < 3 + random.nextInt(3); i++) {
                SheepEntity sheep = EntityType.SHEEP.create(world.toServerWorld());
                assert sheep != null;
                sheep.setPosition(pos.getX() + 0.5F, pos.getY() + 1, pos.getZ() + 0.5F);
                sheep.setColor(SheepEntity.generateDefaultColor(random));
                world.spawnEntity(sheep);
            }
        }
        return true;
    }
}
