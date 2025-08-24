package com.iafenvoy.iceandfire.render.item;

import com.iafenvoy.iceandfire.item.block.entity.DreadPortalBlockEntity;
import com.iafenvoy.iceandfire.item.block.entity.GhostChestBlockEntity;
import com.iafenvoy.iceandfire.item.block.PixieHouseBlock;
import com.iafenvoy.iceandfire.registry.IafBlocks;
import com.iafenvoy.iceandfire.render.block.PixieHouseBlockEntityRenderer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.render.item.BuiltinModelItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;

public class IceAndFireTEISR extends BuiltinModelItemRenderer {
    private final PixieHouseBlockEntityRenderer<?> PIXIE_HOUSE_RENDERER;
    private final BlockEntityRenderDispatcher blockEntityRenderDispatcher;
    private final GhostChestBlockEntity chest = new GhostChestBlockEntity(BlockPos.ORIGIN, IafBlocks.GHOST_CHEST.get().getDefaultState());
    private final DreadPortalBlockEntity portal = new DreadPortalBlockEntity(BlockPos.ORIGIN, IafBlocks.DREAD_PORTAL.get().getDefaultState());

    public IceAndFireTEISR(BlockEntityRenderDispatcher dispatcher, EntityModelLoader modelSet) {
        super(dispatcher, modelSet);
        this.blockEntityRenderDispatcher = dispatcher;
        this.PIXIE_HOUSE_RENDERER = new PixieHouseBlockEntityRenderer<>(null);
    }

    @Override
    public void render(ItemStack stack, ModelTransformationMode type, MatrixStack stackIn, VertexConsumerProvider bufferIn, int combinedLightIn, int combinedOverlayIn) {
        if (stack.getItem() == IafBlocks.GHOST_CHEST.get().asItem())
            this.blockEntityRenderDispatcher.renderEntity(this.chest, stackIn, bufferIn, combinedLightIn, combinedOverlayIn);
        if (stack.getItem() instanceof BlockItem blockItem)
            blockItem.getBlock();
        if (stack.getItem() == IafBlocks.DREAD_PORTAL.get().asItem())
            this.blockEntityRenderDispatcher.renderEntity(this.portal, stackIn, bufferIn, combinedLightIn, combinedOverlayIn);
        if (stack.getItem() instanceof BlockItem && ((BlockItem) stack.getItem()).getBlock() instanceof PixieHouseBlock) {
            this.PIXIE_HOUSE_RENDERER.metaOverride = (BlockItem) stack.getItem();
            this.PIXIE_HOUSE_RENDERER.render(null, 0, stackIn, bufferIn, combinedLightIn, combinedOverlayIn);
        }
    }
}
