package com.iafenvoy.iceandfire.world.structure;

import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.util.Identifier;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.heightprovider.HeightProvider;
import net.minecraft.world.gen.structure.Structure;
import org.jetbrains.annotations.Nullable;

public abstract class IafJigsawStructure extends Structure {
    protected final RegistryEntry<StructurePool> startPool;
    @Nullable
    protected final Identifier startJigsawName;
    protected final int size;
    protected final HeightProvider startHeight;
    @Nullable
    protected final Heightmap.Type projectStartToHeightmap;
    protected final int maxDistanceFromCenter;

    public IafJigsawStructure(Config config, RegistryEntry<StructurePool> startPool, @Nullable Identifier startJigsawName, int size, HeightProvider startHeight, Heightmap.@Nullable Type projectStartToHeightmap, int maxDistanceFromCenter) {
        super(config);
        this.startPool = startPool;
        this.startJigsawName = startJigsawName;
        this.size = size;
        this.startHeight = startHeight;
        this.projectStartToHeightmap = projectStartToHeightmap;
        this.maxDistanceFromCenter = maxDistanceFromCenter;
    }
}
