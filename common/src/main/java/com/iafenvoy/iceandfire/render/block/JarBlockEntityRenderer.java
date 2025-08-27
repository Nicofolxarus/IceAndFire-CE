package com.iafenvoy.iceandfire.render.block;

import com.iafenvoy.iceandfire.item.block.entity.JarBlockEntity;
import com.iafenvoy.iceandfire.item.block.JarBlock;
import com.iafenvoy.iceandfire.render.entity.PixieEntityRenderer;
import com.iafenvoy.iceandfire.render.model.PixieModel;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.RotationAxis;

import java.util.function.Supplier;

public class JarBlockEntityRenderer<T extends JarBlockEntity> implements BlockEntityRenderer<T> {
    public static final RenderLayer TEXTURE_0 = RenderLayer.getEntityCutoutNoCull(PixieEntityRenderer.TEXTURE_0, false);
    public static final RenderLayer TEXTURE_1 = RenderLayer.getEntityCutoutNoCull(PixieEntityRenderer.TEXTURE_1, false);
    public static final RenderLayer TEXTURE_2 = RenderLayer.getEntityCutoutNoCull(PixieEntityRenderer.TEXTURE_2, false);
    public static final RenderLayer TEXTURE_3 = RenderLayer.getEntityCutoutNoCull(PixieEntityRenderer.TEXTURE_3, false);
    public static final RenderLayer TEXTURE_4 = RenderLayer.getEntityCutoutNoCull(PixieEntityRenderer.TEXTURE_4, false);
    public static final RenderLayer TEXTURE_5 = RenderLayer.getEntityCutoutNoCull(PixieEntityRenderer.TEXTURE_5, false);
    public static final RenderLayer TEXTURE_0_GLO = RenderLayer.getEyes(PixieEntityRenderer.TEXTURE_0);
    public static final RenderLayer TEXTURE_1_GLO = RenderLayer.getEyes(PixieEntityRenderer.TEXTURE_1);
    public static final RenderLayer TEXTURE_2_GLO = RenderLayer.getEyes(PixieEntityRenderer.TEXTURE_2);
    public static final RenderLayer TEXTURE_3_GLO = RenderLayer.getEyes(PixieEntityRenderer.TEXTURE_3);
    public static final RenderLayer TEXTURE_4_GLO = RenderLayer.getEyes(PixieEntityRenderer.TEXTURE_4);
    public static final RenderLayer TEXTURE_5_GLO = RenderLayer.getEyes(PixieEntityRenderer.TEXTURE_5);
    private static final Supplier<PixieModel> MODEL_PIXIE = PixieModel::new;

    public JarBlockEntityRenderer(BlockEntityRendererFactory.Context context) {
    }

    @Override
    public void render(T entity, float partialTicks, MatrixStack matrixStackIn, VertexConsumerProvider bufferIn, int combinedLightIn, int combinedOverlayIn) {
        int meta = 0;
        boolean hasPixie = false;
        if (entity.getWorld() != null) {
            if (entity.getCachedState().getBlock() instanceof JarBlock jar) {
                meta = jar.getPixieType();
                hasPixie = !jar.isEmpty();
            } else {
                meta = entity.pixieType;
                hasPixie = entity.hasPixie;
            }
        }
        if (hasPixie) {
            matrixStackIn.push();
            matrixStackIn.translate(0.5F, 1.501F, 0.5F);
            matrixStackIn.multiply(RotationAxis.POSITIVE_X.rotationDegrees(180.0F));
            matrixStackIn.push();
            RenderLayer type = switch (meta) {
                case 1 -> TEXTURE_1;
                case 2 -> TEXTURE_2;
                case 3 -> TEXTURE_3;
                case 4 -> TEXTURE_4;
                default -> TEXTURE_0;
            };
            RenderLayer typeGlow = switch (meta) {
                case 1 -> TEXTURE_1_GLO;
                case 2 -> TEXTURE_2_GLO;
                case 3 -> TEXTURE_3_GLO;
                case 4 -> TEXTURE_4_GLO;
                default -> TEXTURE_0_GLO;
            };
            VertexConsumer buffer = bufferIn.getBuffer(type);
            if (entity.getWorld() != null) {
                if (entity.hasProduced) matrixStackIn.translate(0F, 0.90F, 0F);
                else matrixStackIn.translate(0F, 0.60F, 0F);
                matrixStackIn.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(this.interpolateRotation(entity.prevRotationYaw, entity.rotationYaw, partialTicks)));
                matrixStackIn.scale(0.50F, 0.50F, 0.50F);
                PixieModel model = MODEL_PIXIE.get();
                model.animateInJar(entity.hasProduced, entity, 0);
                model.render(matrixStackIn, buffer, combinedLightIn, combinedOverlayIn, -1);
                model.render(matrixStackIn, bufferIn.getBuffer(typeGlow), combinedLightIn, combinedOverlayIn, -1);
            }
            matrixStackIn.pop();
            matrixStackIn.pop();
        }
    }

    protected float interpolateRotation(float prevYawOffset, float yawOffset, float partialTicks) {
        float f = yawOffset - prevYawOffset;
        while (f < -180) f += 360;
        while (f >= 180.0F) f -= 360.0F;
        return prevYawOffset + partialTicks * f;
    }
}
