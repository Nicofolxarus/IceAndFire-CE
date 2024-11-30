package com.iafenvoy.iceandfire.render.item;

import com.iafenvoy.iceandfire.render.block.RenderDeathWormGauntlet;
import com.iafenvoy.uranus.client.render.DynamicItemRenderer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.BuiltinModelItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;

public class DeathwormGauntletRenderer implements DynamicItemRenderer {
    @Override
    public void render(ItemStack stack, ModelTransformationMode mode, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        BuiltinModelItemRenderer renderer = new RenderDeathWormGauntlet(MinecraftClient.getInstance().getBlockEntityRenderDispatcher(), MinecraftClient.getInstance().getEntityModelLoader());
        renderer.render(stack, mode, matrices, vertexConsumers, light, overlay);
    }
}
