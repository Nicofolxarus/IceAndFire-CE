package com.iafenvoy.iceandfire.render.item;

import com.iafenvoy.iceandfire.registry.IafItems;
import com.iafenvoy.iceandfire.render.entity.TideTridentEntityRenderer;
import com.iafenvoy.iceandfire.render.model.TideTridentModel;
import com.iafenvoy.uranus.client.render.DynamicItemRenderer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RotationAxis;

public class TideTridentItemRenderer implements DynamicItemRenderer {
    private static final TideTridentModel MODEL = new TideTridentModel();

    @Override
    public void render(ItemStack stack, ModelTransformationMode mode, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        matrices.translate(0.5F, 0.5f, 0.5f);
        if (mode == ModelTransformationMode.GUI || mode == ModelTransformationMode.FIXED || mode == ModelTransformationMode.NONE || mode == ModelTransformationMode.GROUND) {
            ItemStack tridentInventory = new ItemStack(IafItems.TIDE_TRIDENT_INVENTORY.get());
            if (stack.hasEnchantments())
                tridentInventory.set(DataComponentTypes.ENCHANTMENTS, stack.get(DataComponentTypes.ENCHANTMENTS));
            MinecraftClient.getInstance().getItemRenderer().renderItem(tridentInventory, mode, mode == ModelTransformationMode.GROUND ? light : 240, overlay, matrices, vertexConsumers, MinecraftClient.getInstance().world, 0);
        } else {
            matrices.push();
            matrices.translate(0, 0.2F, -0.15F);
            if (mode.isFirstPerson())
                matrices.translate(mode == ModelTransformationMode.FIRST_PERSON_LEFT_HAND ? -0.3F : 0.3F, 0.2F, -0.2F);
            else matrices.translate(0, 0.6F, 0.0F);
            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(160.0F));
            VertexConsumer glintVertexBuilder = ItemRenderer.getDirectItemGlintConsumer(vertexConsumers, RenderLayer.getEntityCutoutNoCull(TideTridentEntityRenderer.TRIDENT), false, stack.hasGlint());
            MODEL.render(matrices, glintVertexBuilder, light, overlay, -1);
            matrices.pop();
        }
    }
}
