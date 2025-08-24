package com.iafenvoy.iceandfire.render.item;

import com.iafenvoy.iceandfire.item.block.PixieHouseBlock;
import com.iafenvoy.iceandfire.item.block.entity.DreadPortalBlockEntity;
import com.iafenvoy.iceandfire.item.block.entity.GhostChestBlockEntity;
import com.iafenvoy.iceandfire.registry.IafBlocks;
import com.iafenvoy.iceandfire.render.block.PixieHouseBlockEntityRenderer;
import com.iafenvoy.uranus.client.render.DynamicItemRenderer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;

public class MiscItemRenderer implements DynamicItemRenderer {
    private final PixieHouseBlockEntityRenderer<?> pixieHouseBlockEntityRenderer;
    private final GhostChestBlockEntity chest = new GhostChestBlockEntity(BlockPos.ORIGIN, IafBlocks.GHOST_CHEST.get().getDefaultState());
    private final DreadPortalBlockEntity portal = new DreadPortalBlockEntity(BlockPos.ORIGIN, IafBlocks.DREAD_PORTAL.get().getDefaultState());

    public MiscItemRenderer() {
        this.pixieHouseBlockEntityRenderer = new PixieHouseBlockEntityRenderer<>(null);
    }

    @Override
    public void render(ItemStack stack, ModelTransformationMode type, MatrixStack stackIn, VertexConsumerProvider bufferIn, int combinedLightIn, int combinedOverlayIn) {
        BlockEntityRenderDispatcher blockEntityRenderDispatcher = MinecraftClient.getInstance().getBlockEntityRenderDispatcher();
        if (stack.getItem() == IafBlocks.GHOST_CHEST.get().asItem())
            blockEntityRenderDispatcher.renderEntity(this.chest, stackIn, bufferIn, combinedLightIn, combinedOverlayIn);
        if (stack.getItem() == IafBlocks.DREAD_PORTAL.get().asItem())
            blockEntityRenderDispatcher.renderEntity(this.portal, stackIn, bufferIn, combinedLightIn, combinedOverlayIn);
        if (stack.getItem() instanceof BlockItem && ((BlockItem) stack.getItem()).getBlock() instanceof PixieHouseBlock) {
            this.pixieHouseBlockEntityRenderer.metaOverride = (BlockItem) stack.getItem();
            this.pixieHouseBlockEntityRenderer.render(null, 0, stackIn, bufferIn, combinedLightIn, combinedOverlayIn);
        }
    }
}
