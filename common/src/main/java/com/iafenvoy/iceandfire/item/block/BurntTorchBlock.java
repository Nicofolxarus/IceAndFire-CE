package com.iafenvoy.iceandfire.item.block;

import com.iafenvoy.iceandfire.item.block.util.DreadBlock;
import com.iafenvoy.iceandfire.item.block.util.WallBlock;
import com.iafenvoy.iceandfire.registry.IafBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.MapColor;
import net.minecraft.block.TorchBlock;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class BurntTorchBlock extends TorchBlock implements DreadBlock, WallBlock {
    public BurntTorchBlock() {
        //TODO: Particle Type
        super(new SimpleParticleType(false), Settings.create().mapColor(MapColor.OAK_TAN).burnable().luminance((state) -> 0).sounds(BlockSoundGroup.WOOD).nonOpaque().dynamicBounds().noCollision());
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random rand) {
    }

    @Override
    public Block wallBlock() {
        return IafBlocks.BURNT_TORCH_WALL.get();
    }
}