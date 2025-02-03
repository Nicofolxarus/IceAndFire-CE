package com.iafenvoy.iceandfire.item;

import com.iafenvoy.iceandfire.IceAndFire;
import com.iafenvoy.iceandfire.registry.IafDataComponents;
import com.iafenvoy.iceandfire.screen.handler.MyrmexAddRoomScreenHandler;
import com.iafenvoy.iceandfire.screen.handler.MyrmexStaffScreenHandler;
import dev.architectury.registry.menu.ExtendedMenuProvider;
import dev.architectury.registry.menu.MenuRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtOps;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class ItemMyrmexStaff extends Item {
    public ItemMyrmexStaff(boolean jungle) {
        super(new Settings().maxCount(1).component(IafDataComponents.UUID.get(), new UUID(0, 0)));
    }

    @Override
    public TypedActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand hand) {
        ItemStack itemStackIn = playerIn.getStackInHand(hand);
        if (playerIn.isSneaking())
            return super.use(worldIn, playerIn, hand);
        if (itemStackIn.contains(IafDataComponents.UUID.get())) {
            UUID id = itemStackIn.get(IafDataComponents.UUID.get());
            if (id != null && !id.equals(new UUID(0, 0)) && playerIn instanceof ServerPlayerEntity serverPlayer)
                MenuRegistry.openExtendedMenu(serverPlayer, new ExtendedMenuProvider() {
                    @Override
                    public void saveExtraData(PacketByteBuf buf) {
                        NbtCompound compound = new NbtCompound();
                        compound.put("data", ItemStack.CODEC.encodeStart(NbtOps.INSTANCE, itemStackIn).resultOrPartial(IceAndFire.LOGGER::error).orElse(new NbtCompound()));
                        buf.writeNbt(compound);
                        buf.writeUuid(id);
                    }

                    @Override
                    public Text getDisplayName() {
                        return Text.translatable("myrmex_staff_screen");
                    }

                    @Override
                    public @NotNull ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
                        return new MyrmexStaffScreenHandler(syncId, playerInventory);
                    }
                });
        }
        playerIn.swingHand(hand);
        return new TypedActionResult<>(ActionResult.PASS, itemStackIn);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        assert context.getPlayer() != null;
        if (!context.getPlayer().isSneaking()) {
            return super.useOnBlock(context);
        } else {
            ItemStack stack = context.getPlayer().getStackInHand(context.getHand());
            if (stack.contains(IafDataComponents.UUID.get())) {
                UUID id = stack.get(IafDataComponents.UUID.get());
                if (id != null && !id.equals(new UUID(0, 0)) && context.getPlayer() instanceof ServerPlayerEntity serverPlayer)
                    MenuRegistry.openExtendedMenu(serverPlayer, new ExtendedMenuProvider() {
                        @Override
                        public void saveExtraData(PacketByteBuf buf) {
                            ItemStack stack = context.getPlayer().getStackInHand(context.getHand());
                            NbtCompound compound = new NbtCompound();
                            compound.put("data", ItemStack.CODEC.encodeStart(NbtOps.INSTANCE, stack).resultOrPartial(IceAndFire.LOGGER::error).orElse(new NbtCompound()));
                            buf.writeNbt(compound);
                            buf.writeLong(context.getBlockPos().asLong());
                            buf.writeEnumConstant(serverPlayer.getHorizontalFacing());
                            buf.writeUuid(id);
                        }

                        @Override
                        public Text getDisplayName() {
                            return Text.translatable("myrmex_add_room");
                        }

                        @Override
                        public @NotNull ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
                            return new MyrmexAddRoomScreenHandler(syncId, playerInventory);
                        }
                    });
            }
            context.getPlayer().swingHand(context.getHand());
            return ActionResult.SUCCESS;
        }
    }
}
