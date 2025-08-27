package com.iafenvoy.iceandfire.neoforge.compat;

import com.hollingsworth.arsnouveau.api.mob_jar.JarBehavior;
import com.hollingsworth.arsnouveau.api.registry.JarBehaviorRegistry;
import com.hollingsworth.arsnouveau.common.block.tile.MobJarTile;
import com.iafenvoy.iceandfire.entity.SeaSerpentEntity;
import com.iafenvoy.iceandfire.registry.IafEntities;
import net.minecraft.util.math.Vec3d;

public class IceAndFireArsNouveauCompat {
    public static void init() {
        JarBehaviorRegistry.register(IafEntities.SEA_SERPENT.get(), new SeaSerpentBehavior());
    }

    private static class SeaSerpentBehavior extends JarBehavior<SeaSerpentEntity> {
        @Override
        public Vec3d scaleOffset(MobJarTile pBlockEntity) {
            return new Vec3d(-0.85, -0.85, -0.85);
        }

        @Override
        public Vec3d translate(MobJarTile pBlockEntity) {
            return new Vec3d(0, 0.4, 0);
        }
    }
}
