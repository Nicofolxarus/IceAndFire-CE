package com.iafenvoy.iceandfire.compat.ponder;

import com.iafenvoy.iceandfire.IceAndFire;
import com.iafenvoy.iceandfire.registry.IafBlocks;
import net.createmod.ponder.api.registration.PonderPlugin;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class IceAndFirePonderPlugin implements PonderPlugin {
    @Override
    public @NotNull String getModId() {
        return IceAndFire.MOD_ID;
    }

    @Override
    public void registerScenes(@NotNull PonderSceneRegistrationHelper<Identifier> helper) {
        helper.forComponents(IafBlocks.DRAGONFORGE_FIRE_CORE_DISABLED.getId())
                .addStoryBoard(DragonForgeStoryBoard.ID, new DragonForgeStoryBoard());
    }
}
