package com.iafenvoy.iceandfire.item.block.entity;

import com.iafenvoy.iceandfire.entity.PixieEntity;
import com.iafenvoy.iceandfire.network.payload.UpdatePixieHouseS2CPayload;
import com.iafenvoy.iceandfire.network.payload.UpdatePixieJarS2CPayload;
import com.iafenvoy.iceandfire.registry.IafBlockEntities;
import com.iafenvoy.iceandfire.registry.IafEntities;
import com.iafenvoy.iceandfire.registry.IafParticles;
import com.iafenvoy.iceandfire.registry.IafSounds;
import com.iafenvoy.uranus.ServerHelper;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Hand;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;
import java.util.UUID;

public class JarBlockEntity extends BlockEntity {
    private static final float PARTICLE_WIDTH = 0.3F;
    private static final float PARTICLE_HEIGHT = 0.6F;
    private final Random rand;
    public boolean hasPixie;
    public boolean prevHasProduced;
    public boolean hasProduced;
    public boolean tamedPixie;
    public UUID pixieOwnerUUID;
    public int pixieType;
    public int ticksExisted;
    public DefaultedList<ItemStack> pixieItems = DefaultedList.ofSize(1, ItemStack.EMPTY);
    public float rotationYaw;
    public float prevRotationYaw;

    public JarBlockEntity(BlockPos pos, BlockState state) {
        super(IafBlockEntities.PIXIE_JAR.get(), pos, state);
        this.rand = new Random();
        this.hasPixie = true;
    }

    public JarBlockEntity(BlockPos pos, BlockState state, boolean empty) {
        super(IafBlockEntities.PIXIE_JAR.get(), pos, state);
        this.rand = new Random();
        this.hasPixie = !empty;
    }

    public static void tick(World level, BlockPos pos, BlockState state, JarBlockEntity entityJar) {
        entityJar.ticksExisted++;
        if (level.isClient && entityJar.hasPixie) {
            level.addParticle(IafParticles.PIXIE_DUST.get(),
                    pos.getX() + 0.5F + (double) (entityJar.rand.nextFloat() * PARTICLE_WIDTH * 2F) - PARTICLE_WIDTH,
                    pos.getY() + (double) (entityJar.rand.nextFloat() * PARTICLE_HEIGHT),
                    pos.getZ() + 0.5F + (double) (entityJar.rand.nextFloat() * PARTICLE_WIDTH * 2F) - PARTICLE_WIDTH, PixieEntity.PARTICLE_RGB[entityJar.pixieType][0], PixieEntity.PARTICLE_RGB[entityJar.pixieType][1], PixieEntity.PARTICLE_RGB[entityJar.pixieType][2]);
        }
        if (entityJar.ticksExisted % 24000 == 0 && !entityJar.hasProduced && entityJar.hasPixie) {
            entityJar.hasProduced = true;
            if (!level.isClient)
                ServerHelper.sendToAll(new UpdatePixieJarS2CPayload(pos, true));
        }
        if (entityJar.hasPixie && entityJar.hasProduced != entityJar.prevHasProduced && entityJar.ticksExisted > 5) {
            if (!level.isClient)
                ServerHelper.sendToAll(new UpdatePixieJarS2CPayload(pos, entityJar.hasProduced));
            else
                level.playSound(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5, IafSounds.PIXIE_HURT.get(), SoundCategory.BLOCKS, 1, 1, false);
        }
        entityJar.prevRotationYaw = entityJar.rotationYaw;
        if (entityJar.rand.nextInt(30) == 0)
            entityJar.rotationYaw = (entityJar.rand.nextFloat() * 360F) - 180F;
        if (entityJar.hasPixie && entityJar.ticksExisted % 40 == 0 && entityJar.rand.nextInt(6) == 0 && level.isClient)
            level.playSound(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5, IafSounds.PIXIE_IDLE.get(), SoundCategory.BLOCKS, 1, 1, false);
        entityJar.prevHasProduced = entityJar.hasProduced;
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        nbt.putBoolean("HasPixie", this.hasPixie);
        nbt.putInt("PixieType", this.pixieType);
        nbt.putBoolean("HasProduced", this.hasProduced);
        nbt.putBoolean("TamedPixie", this.tamedPixie);
        if (this.pixieOwnerUUID != null)
            nbt.putUuid("PixieOwnerUUID", this.pixieOwnerUUID);
        nbt.putInt("TicksExisted", this.ticksExisted);
        Inventories.writeNbt(nbt, this.pixieItems, registryLookup);
    }

    @Override
    public BlockEntityUpdateS2CPacket toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        this.hasPixie = nbt.getBoolean("HasPixie");
        this.pixieType = nbt.getInt("PixieType");
        this.hasProduced = nbt.getBoolean("HasProduced");
        this.ticksExisted = nbt.getInt("TicksExisted");
        this.tamedPixie = nbt.getBoolean("TamedPixie");
        if (nbt.containsUuid("PixieOwnerUUID"))
            this.pixieOwnerUUID = nbt.getUuid("PixieOwnerUUID");
        this.pixieItems = DefaultedList.ofSize(1, ItemStack.EMPTY);
        Inventories.readNbt(nbt, this.pixieItems, registryLookup);
    }

    public void releasePixie() {
        PixieEntity pixie = new PixieEntity(IafEntities.PIXIE.get(), this.world);
        pixie.updatePositionAndAngles(this.pos.getX() + 0.5F, this.pos.getY() + 1F, this.pos.getZ() + 0.5F, new Random().nextInt(360), 0);
        pixie.setStackInHand(Hand.MAIN_HAND, this.pixieItems.getFirst());
        pixie.setColor(this.pixieType);
        pixie.ticksUntilHouseAI = 500;
        pixie.setTamed(this.tamedPixie, false);
        pixie.setOwnerUuid(this.pixieOwnerUUID);
        assert this.world != null;
        this.world.spawnEntity(pixie);
        this.hasPixie = false;
        this.pixieType = 0;
        if (!this.world.isClient)
            ServerHelper.sendToAll(new UpdatePixieHouseS2CPayload(this.pos, false, 0));
    }
}
