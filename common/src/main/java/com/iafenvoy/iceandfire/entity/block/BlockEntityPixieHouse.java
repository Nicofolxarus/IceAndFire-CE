package com.iafenvoy.iceandfire.entity.block;

import com.iafenvoy.iceandfire.entity.EntityPixie;
import com.iafenvoy.iceandfire.network.payload.UpdatePixieHousePayload;
import com.iafenvoy.iceandfire.registry.IafBlockEntities;
import com.iafenvoy.iceandfire.registry.IafBlocks;
import com.iafenvoy.iceandfire.registry.IafEntities;
import com.iafenvoy.iceandfire.registry.IafParticles;
import com.iafenvoy.uranus.ServerHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Hand;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class BlockEntityPixieHouse extends BlockEntity {
    private static final float PARTICLE_WIDTH = 0.3F;
    private static final float PARTICLE_HEIGHT = 0.6F;
    private final Random rand;
    public int houseType;
    public boolean hasPixie;
    public boolean tamedPixie;
    public UUID pixieOwnerUUID;
    public int pixieType;
    public DefaultedList<ItemStack> pixieItems = DefaultedList.ofSize(1, ItemStack.EMPTY);

    public BlockEntityPixieHouse(BlockPos pos, BlockState state) {
        super(IafBlockEntities.PIXIE_HOUSE.get(), pos, state);
        this.rand = new Random();
    }

    public static int getHouseTypeFromBlock(Block block) {
        if (block == IafBlocks.PIXIE_HOUSE_MUSHROOM_RED.get()) return 1;
        if (block == IafBlocks.PIXIE_HOUSE_MUSHROOM_BROWN.get()) return 0;
        if (block == IafBlocks.PIXIE_HOUSE_OAK.get()) return 3;
        if (block == IafBlocks.PIXIE_HOUSE_BIRCH.get()) return 2;
        if (block == IafBlocks.PIXIE_HOUSE_SPRUCE.get()) return 5;
        if (block == IafBlocks.PIXIE_HOUSE_DARK_OAK.get()) return 4;
        else return 0;
    }

    public static void tickClient(World level, BlockPos pos, BlockState state, BlockEntityPixieHouse entityPixieHouse) {
        if (entityPixieHouse.hasPixie)
            level.addParticle(IafParticles.PIXIE_DUST.get(),
                    pos.getX() + 0.5F + (double) (entityPixieHouse.rand.nextFloat() * PARTICLE_WIDTH * 2F) - PARTICLE_WIDTH,
                    pos.getY() + (double) (entityPixieHouse.rand.nextFloat() * PARTICLE_HEIGHT),
                    pos.getZ() + 0.5F + (double) (entityPixieHouse.rand.nextFloat() * PARTICLE_WIDTH * 2F) - PARTICLE_WIDTH,
                    EntityPixie.PARTICLE_RGB[entityPixieHouse.pixieType][0], EntityPixie.PARTICLE_RGB[entityPixieHouse.pixieType][1],
                    EntityPixie.PARTICLE_RGB[entityPixieHouse.pixieType][2]);
    }

    public static void tickServer(World level, BlockPos pos, BlockState state, BlockEntityPixieHouse entityPixieHouse) {
        if (entityPixieHouse.hasPixie && ThreadLocalRandom.current().nextInt(100) == 0)
            entityPixieHouse.releasePixie();
    }

    @Override
    public void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        nbt.putInt("HouseType", this.houseType);
        nbt.putBoolean("HasPixie", this.hasPixie);
        nbt.putInt("PixieType", this.pixieType);
        nbt.putBoolean("TamedPixie", this.tamedPixie);
        if (this.pixieOwnerUUID != null)
            nbt.putUuid("PixieOwnerUUID", this.pixieOwnerUUID);
        Inventories.writeNbt(nbt, this.pixieItems, registryLookup);
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
    public void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        this.houseType = nbt.getInt("HouseType");
        this.hasPixie = nbt.getBoolean("HasPixie");
        this.pixieType = nbt.getInt("PixieType");
        this.tamedPixie = nbt.getBoolean("TamedPixie");
        if (nbt.containsUuid("PixieOwnerUUID"))
            this.pixieOwnerUUID = nbt.getUuid("PixieOwnerUUID");
        this.pixieItems = DefaultedList.ofSize(1, ItemStack.EMPTY);
        Inventories.readNbt(nbt, this.pixieItems, registryLookup);
    }

    public void releasePixie() {
        EntityPixie pixie = new EntityPixie(IafEntities.PIXIE.get(), this.world);
        pixie.updatePositionAndAngles(this.pos.getX() + 0.5F, this.pos.getY() + 1F, this.pos.getZ() + 0.5F, ThreadLocalRandom.current().nextInt(360), 0);
        pixie.setStackInHand(Hand.MAIN_HAND, this.pixieItems.get(0));
        pixie.setColor(this.pixieType);
        pixie.ticksUntilHouseAI = 500;
        pixie.setTamed(this.tamedPixie, true);
        pixie.setOwnerUuid(this.pixieOwnerUUID);
        assert this.world != null;
        if (!this.world.isClient)
            this.world.spawnEntity(pixie);
        this.hasPixie = false;
        this.pixieType = 0;
        if (!this.world.isClient)
            ServerHelper.sendToAll(new UpdatePixieHousePayload(this.pos, false, 0));
    }
}
