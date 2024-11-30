package com.iafenvoy.iceandfire.mixin;

import com.iafenvoy.iceandfire.world.util.Teleportable;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.TeleportTarget;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class EntityMixin implements Teleportable {
    @Unique
    @Nullable
    protected TeleportTarget iceandfire$customTeleportTarget;

    @Override
    public void iceandfire$setCustomTeleportTarget(TeleportTarget teleportTarget) {
        this.iceandfire$customTeleportTarget = teleportTarget;
    }

    @Inject(method = "getTeleportTarget", at = @At("HEAD"), cancellable = true, allow = 1)
    public void getTeleportTarget(ServerWorld destination, CallbackInfoReturnable<TeleportTarget> cir) {
        TeleportTarget customTarget = this.iceandfire$customTeleportTarget;
        if (customTarget != null)
            cir.setReturnValue(customTarget);
    }
}
