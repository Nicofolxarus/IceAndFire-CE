package com.iafenvoy.iceandfire.compat.ponder;

import com.iafenvoy.iceandfire.IceAndFire;
import com.iafenvoy.iceandfire.entity.FireDragonEntity;
import com.iafenvoy.iceandfire.registry.IafEntities;
import net.createmod.ponder.api.scene.PonderStoryBoard;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.NotNull;

public class DragonForgeStoryBoard implements PonderStoryBoard {
    public static final Identifier ID = Identifier.of(IceAndFire.MOD_ID, "dragon_forge");

    @Override
    public void program(@NotNull SceneBuilder scene, @NotNull SceneBuildingUtil util) {
        scene.title("dragon_forge", "Dragon Forge");
        scene.configureBasePlate(0, 0, 5);
        scene.removeShadow();
        scene.world().showSection(util.select().layer(0), Direction.UP);//Show floor
        scene.idle(20);
        scene.world().showSection(util.select().layer(1), Direction.DOWN);
        scene.idle(20);
        scene.world().showSection(util.select().layer(2), Direction.DOWN);
        scene.idle(20);
        scene.overlay().showText(50)
                .text("Dragon need to see this block to power the forge")
                .attachKeyFrame()
                .placeNearTarget()
                .pointAt(util.vector().centerOf(2, 2, 1));
        scene.overlay().showText(50)
                .text("This is the core of forge, all items put in here")
                .attachKeyFrame()
                .placeNearTarget()
                .pointAt(util.vector().centerOf(2, 2, 2));
        scene.idle(60);
        scene.world().showSection(util.select().layer(3), Direction.DOWN);
        scene.idle(20);
        scene.rotateCameraY(-45);
        scene.idle(20);
        scene.world().createEntity(IafEntities.FIRE_DRAGON.get()::create);
        scene.world().modifyEntities(FireDragonEntity.class, dragon -> MinecraftClient.getInstance().execute(() -> {
            dragon.setPos(3, 0.5, -5);
            dragon.setAgeInDays(50);//Stage 3 is enough
            dragon.setBreathingFire(true);
        }));
    }
}
