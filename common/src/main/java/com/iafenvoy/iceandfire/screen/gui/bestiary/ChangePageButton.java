package com.iafenvoy.iceandfire.screen.gui.bestiary;

import com.iafenvoy.iceandfire.IceAndFire;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ChangePageButton extends ButtonWidget {
    private final boolean right;
    private final int color;

    public ChangePageButton(int x, int y, boolean right, int color, PressAction press) {
        super(x, y, 23, 10, Text.literal(""), press, DEFAULT_NARRATION_SUPPLIER);
        this.right = right;
        this.color = color;
    }

    @Override
    protected void renderWidget(DrawContext context, int mouseX, int mouseY, float delta) {
        if (this.active) {
            Identifier resourceLocation = Identifier.of(IceAndFire.MOD_ID, "textures/gui/bestiary/widgets.png");
            boolean flag = mouseX >= this.getX() && mouseY >= this.getY() && mouseX < this.getX() + this.width && mouseY < this.getY() + this.height;
            int i = 0;
            int j = 64;
            if (flag) i += 23;
            if (!this.right) j += 13;
            j += this.color * 23;
            context.drawTexture(resourceLocation, this.getX(), this.getY(), i, j, this.width, this.height);
        }
    }
}