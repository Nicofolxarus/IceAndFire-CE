package com.iafenvoy.iceandfire.item.block;

import com.iafenvoy.iceandfire.item.block.util.IDreadBlock;
import com.iafenvoy.iceandfire.item.block.util.IWallBlock;
import com.iafenvoy.iceandfire.registry.IafBlocks;
import com.iafenvoy.iceandfire.registry.IafParticles;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.MapColor;
import net.minecraft.block.TorchBlock;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class BlockDreadTorch extends TorchBlock implements IDreadBlock, IWallBlock {
    public BlockDreadTorch() {
        super(ParticleTypes.DUST_PLUME, Settings.create().mapColor(MapColor.OAK_TAN).instrument(NoteBlockInstrument.BASS).burnable().luminance((state) -> 5).sounds(BlockSoundGroup.STONE).nonOpaque().dynamicBounds().noCollision());
    }

    @Override
    public void randomDisplayTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        super.randomDisplayTick(stateIn, worldIn, pos, rand);
        double d0 = (double) pos.getX() + 0.5D;
        double d1 = (double) pos.getY() + 0.6D;
        double d2 = (double) pos.getZ() + 0.5D;
        worldIn.addParticle(IafParticles.DREAD_TORCH.get(), d0, d1, d2, 0.0D, 0.0D, 0.0D);
    }

    @Override
    public Block wallBlock() {
        return IafBlocks.DREAD_TORCH_WALL.get();
    }
}