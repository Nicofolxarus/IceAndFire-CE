package com.iafenvoy.iceandfire.mixin;

import com.iafenvoy.iceandfire.config.IafClientConfig;
import com.iafenvoy.iceandfire.screen.TitleScreenRenderManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.RotatingCubeMapRenderer;
import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = RotatingCubeMapRenderer.class, priority = 900)
public abstract class RotatingCubeMapRendererMixin {
    @Unique
    private int iceandfire$slowTick = 0;

    @Inject(method = "render", at = @At(value = "HEAD"), cancellable = true)
    private void onRenderBackground(DrawContext context, int width, int height, float alpha, float tickDelta, CallbackInfo ci) {
        if (!IafClientConfig.INSTANCE.customMainMenu.getValue()) return;
        this.iceandfire$slowTick++;
        if (this.iceandfire$slowTick >= 3) {
            this.iceandfire$slowTick = 0;
            TitleScreenRenderManager.tick();
        }
        TitleScreenRenderManager.renderBackground(context, width, height);
        if (MinecraftClient.getInstance().currentScreen instanceof TitleScreen)
            TitleScreenRenderManager.drawModName(context, width, height, 0xFF000000);
        ci.cancel();
    }
}
