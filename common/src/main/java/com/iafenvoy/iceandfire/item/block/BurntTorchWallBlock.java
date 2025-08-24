package com.iafenvoy.iceandfire.item.block;

import com.iafenvoy.iceandfire.item.block.util.DreadBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.MapColor;
import net.minecraft.block.WallTorchBlock;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class BurntTorchWallBlock extends WallTorchBlock implements DreadBlock {
    public BurntTorchWallBlock() {
        //TODO: Particle Type
        super(new SimpleParticleType(false), Settings.create().mapColor(MapColor.OAK_TAN).burnable().luminance((state) -> 0).sounds(BlockSoundGroup.WOOD).nonOpaque().dynamicBounds().noCollision());
        //                    .lootFrom(IafBlockRegistry.BURNT_TORCH)
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random rand) {
    }
}