package com.iafenvoy.iceandfire.screen.gui;

import com.iafenvoy.iceandfire.IceAndFire;
import com.iafenvoy.iceandfire.entity.HippocampusEntity;
import com.iafenvoy.iceandfire.screen.handler.HippocampusScreenHandler;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class HippocampusScreen extends HandledScreen<HippocampusScreenHandler> {
    private static final Identifier TEXTURE = Identifier.of(IceAndFire.MOD_ID, "textures/gui/hippogryph.png");

    public HippocampusScreen(HippocampusScreenHandler handler, PlayerInventory playerInv, Text name) {
        super(handler, playerInv, name);
    }

    @Override
    protected void drawForeground(DrawContext context, int mouseX, int mouseY) {
        int k = 0;
        int l = 0;
        context.drawText(this.textRenderer, this.handler.getHippocampus().getDisplayName().getString(), l + 8, 6, 4210752, false);
        context.drawText(this.textRenderer, this.playerInventoryTitle, k + 8, l + this.backgroundHeight - 96 + 2, 4210752, false);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float tickDelta) {
        this.renderBackground(context, mouseX, mouseY, tickDelta);
        super.render(context, mouseX, mouseY, tickDelta);
        this.drawMouseoverTooltip(context, mouseX, mouseY);
    }

    @Override
    protected void drawBackground(DrawContext context, float tickDelta, int mouseX, int mouseY) {
        int i = (this.width - this.backgroundWidth) / 2;
        int j = (this.height - this.backgroundHeight) / 2;
        context.drawTexture(TEXTURE, i, j, 0, 0, this.backgroundWidth, this.backgroundHeight);
        HippocampusEntity hippo = this.handler.getHippocampus();
        if (hippo.isChested()) context.drawTexture(TEXTURE, i + 79, j + 17, 0, this.backgroundHeight, 5 * 18, 54);
        InventoryScreen.drawEntity(context, i + 26, j + 18, i + 77, j + 69, 17, 0.25F, mouseX, mouseY, hippo);
    }
}