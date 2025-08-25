package com.iafenvoy.iceandfire.item.block.entity;

import com.iafenvoy.iceandfire.item.DragonEggItem;
import com.iafenvoy.iceandfire.network.payload.UpdatePodiumS2CPayload;
import com.iafenvoy.iceandfire.registry.IafBlockEntities;
import com.iafenvoy.iceandfire.screen.handler.PodiumScreenHandler;
import com.iafenvoy.uranus.ServerHelper;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.LockableContainerBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class PodiumBlockEntity extends LockableContainerBlockEntity implements SidedInventory {
    private static final int[] slotsTop = new int[]{0};
    public int ticksExisted;
    public int prevTicksExisted;
    private DefaultedList<ItemStack> stacks = DefaultedList.ofSize(1, ItemStack.EMPTY);

    public PodiumBlockEntity(BlockPos pos, BlockState state) {
        super(IafBlockEntities.PODIUM.get(), pos, state);
    }

    //TODO: This must be easier to do
    public static void tick(World level, BlockPos pos, BlockState state, PodiumBlockEntity entityPodium) {
        entityPodium.prevTicksExisted = entityPodium.ticksExisted;
        entityPodium.ticksExisted++;
    }

    @Override
    public int size() {
        return this.stacks.size();
    }

    @Override
    public ItemStack getStack(int index) {
        return this.stacks.get(index);
    }

    @Override
    public ItemStack removeStack(int index, int count) {
        if (!this.stacks.get(index).isEmpty()) {
            ItemStack itemstack;
            if (this.stacks.get(index).getCount() <= count) {
                itemstack = this.stacks.get(index);
                this.stacks.set(index, ItemStack.EMPTY);
            } else {
                itemstack = this.stacks.get(index).split(count);
                if (this.stacks.get(index).isEmpty())
                    this.stacks.set(index, ItemStack.EMPTY);
            }
            return itemstack;
        } else return ItemStack.EMPTY;
    }

    public ItemStack getStackInSlotOnClosing(int index) {
        if (!this.stacks.get(index).isEmpty()) {
            ItemStack itemstack = this.stacks.get(index);
            this.stacks.set(index, itemstack);
            return itemstack;
        } else return ItemStack.EMPTY;
    }

    @Override
    public void setStack(int index, ItemStack stack) {
        this.stacks.set(index, stack);
        if (!stack.isEmpty() && stack.getCount() > this.getMaxCountPerStack())
            stack.setCount(this.getMaxCountPerStack());
        assert this.world != null;
        if (!this.world.isClient)
            ServerHelper.sendToAll(new UpdatePodiumS2CPayload(this.getPos(), this.stacks.getFirst()));
    }

    @Override
    public void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        this.stacks = DefaultedList.ofSize(this.size(), ItemStack.EMPTY);
        Inventories.readNbt(nbt, this.stacks, registryLookup);
    }

    @Override
    public void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        Inventories.writeNbt(nbt, this.stacks, registryLookup);
    }

    @Override
    public void onOpen(PlayerEntity player) {
    }

    @Override
    public void onClose(PlayerEntity player) {
    }

    @Override
    public boolean canInsert(int index, ItemStack stack, Direction direction) {
        return index != 0 || (stack.getItem() instanceof DragonEggItem);
    }

    @Override
    public int getMaxCountPerStack() {
        return 64;
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return true;
    }

    @Override
    public void clear() {
        this.stacks.clear();
    }

    @Override
    public int[] getAvailableSlots(Direction side) {
        return slotsTop;
    }

    @Override
    public boolean canExtract(int index, ItemStack stack, Direction direction) {
        return false;
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    @Override
    public boolean isValid(int index, ItemStack stack) {
        return false;
    }

    @Override
    public BlockEntityUpdateS2CPacket toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registryLookup) {
        return this.createNbtWithIdentifyingData(registryLookup);
    }

    @Override
    public ItemStack removeStack(int index) {
        return ItemStack.EMPTY;
    }

    @Override
    public Text getDisplayName() {
        return this.getContainerName();
    }

    @Override
    protected Text getContainerName() {
        return Text.translatable("block.iceandfire.podium");
    }

    @Override
    protected DefaultedList<ItemStack> getHeldStacks() {
        return this.stacks;
    }

    @Override
    protected void setHeldStacks(DefaultedList<ItemStack> inventory) {
        this.stacks = inventory;
    }

    @Override
    protected ScreenHandler createScreenHandler(int id, PlayerInventory player) {
        return null;
    }

    @Override
    public boolean isEmpty() {
        for (int i = 0; i < this.size(); i++)
            if (!this.getStack(i).isEmpty())
                return false;
        return true;
    }

    @Override
    public ScreenHandler createMenu(int id, PlayerInventory playerInventory, PlayerEntity player) {
        return new PodiumScreenHandler(id, this, playerInventory);
    }
}
