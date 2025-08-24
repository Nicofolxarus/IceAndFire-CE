package com.iafenvoy.iceandfire.item;

import com.iafenvoy.iceandfire.IceAndFire;
import com.iafenvoy.iceandfire.data.BestiaryPages;
import com.iafenvoy.iceandfire.registry.IafDataComponents;
import com.iafenvoy.iceandfire.screen.handler.BestiaryScreenHandler;
import dev.architectury.registry.menu.ExtendedMenuProvider;
import dev.architectury.registry.menu.MenuRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtOps;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;
import java.util.Locale;
import java.util.Set;

public class BestiaryItem extends Item {
    public BestiaryItem() {
        super(new Settings().maxCount(1).component(IafDataComponents.BESTIARY_PAGES.get(), List.of(BestiaryPages.INTRODUCTION.getName())));
    }

    @Override
    public TypedActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (playerIn instanceof ServerPlayerEntity serverPlayer)
            MenuRegistry.openExtendedMenu(serverPlayer, new ExtendedMenuProvider() {
                @Override
                public void saveExtraData(PacketByteBuf buf) {
                    ItemStack stack = playerIn.getStackInHand(handIn);
                    NbtCompound compound = new NbtCompound();
                    compound.put("data", ItemStack.OPTIONAL_CODEC.encodeStart(NbtOps.INSTANCE, stack).resultOrPartial(IceAndFire.LOGGER::error).orElse(new NbtCompound()));
                    buf.writeNbt(compound);
                }

                @Override
                public Text getDisplayName() {
                    return Text.translatable("bestiary_gui");
                }

                @Override
                public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
                    return new BestiaryScreenHandler(syncId, playerInventory);
                }
            });
        return new TypedActionResult<>(ActionResult.PASS, playerIn.getStackInHand(handIn));
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        if (InputUtil.isKeyPressed(MinecraftClient.getInstance().getWindow().getHandle(), 340) || InputUtil.isKeyPressed(MinecraftClient.getInstance().getWindow().getHandle(), 344)) {
            tooltip.add(Text.translatable("bestiary.contains").formatted(Formatting.GRAY));
            List<String> list = stack.get(IafDataComponents.BESTIARY_PAGES.get());
            if (list != null) {
                final Set<BestiaryPages> pages = BestiaryPages.containedPages(list);
                for (BestiaryPages page : pages)
                    tooltip.add(Text.literal(Formatting.WHITE + "-").append(Text.translatable("bestiary." + page.getName().toLowerCase(Locale.ROOT))).formatted(Formatting.GRAY));
            }
        } else
            tooltip.add(Text.translatable("bestiary.hold_shift").formatted(Formatting.GRAY));
    }
}
