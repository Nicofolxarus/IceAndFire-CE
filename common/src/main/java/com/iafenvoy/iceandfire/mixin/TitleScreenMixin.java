package com.iafenvoy.iceandfire.mixin;

import com.iafenvoy.iceandfire.config.IafClientConfig;
import com.iafenvoy.iceandfire.screen.TitleScreenRenderManager;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.SplashTextRenderer;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = TitleScreen.class, priority = 900)
public abstract class TitleScreenMixin extends Screen {
    @Shadow
    @Nullable
    private SplashTextRenderer splashText;

    protected TitleScreenMixin(Text title) {
        super(title);
    }

    @Inject(method = "init", at = @At("RETURN"))
    private void onInit(CallbackInfo ci) {
        if (!IafClientConfig.INSTANCE.customMainMenu.getValue()) return;
        SplashTextRenderer renderer = TitleScreenRenderManager.getSplash();
        if (renderer != null)
            this.splashText = renderer;
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/LogoDrawer;draw(Lnet/minecraft/client/gui/DrawContext;IF)V"))
    private void renderModBrand(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci, @Local(ordinal = 2) int i) {
        if (!IafClientConfig.INSTANCE.customMainMenu.getValue()) return;
        if (MinecraftClient.getInstance().currentScreen instanceof TitleScreen)
            TitleScreenRenderManager.drawModName(context, this.width, this.height, 16777215 | i);
    }
}
