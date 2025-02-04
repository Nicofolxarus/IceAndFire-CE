package com.iafenvoy.iceandfire.mixin;

import com.iafenvoy.iceandfire.event.RegistryEvents;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.ParticleManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(ParticleManager.class)
public abstract class ParticleManagerMixin {
    @Inject(method = "registerDefaultFactories", at = @At("RETURN"))
    private void registerParticleFactories(CallbackInfo ci) {
        RegistryEvents.PARTICLE.invoker().accept((ParticleManager) (Object) this);
    }
}
