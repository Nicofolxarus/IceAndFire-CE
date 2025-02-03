package com.iafenvoy.iceandfire.world;

import com.iafenvoy.iceandfire.config.IafCommonConfig;
import com.iafenvoy.uranus.ServerHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.Optional;

public class GenerationConstants {
    public static final Direction[] HORIZONTALS = new Direction[]{Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST};

    public static boolean isFarEnoughFromSpawn(final BlockPos position) {
        BlockPos spawnRelative = Optional.ofNullable(ServerHelper.server.getWorld(World.OVERWORLD)).map(World::getLevelProperties).map(x -> new BlockPos(x.getSpawnPos().getX(), position.getY(), x.getSpawnPos().getZ())).orElse(new BlockPos(0, position.getY(), 0));
        return !spawnRelative.isWithinDistance(position, IafCommonConfig.INSTANCE.worldGen.dangerousDistanceLimit.getValue().floatValue());
    }
}
