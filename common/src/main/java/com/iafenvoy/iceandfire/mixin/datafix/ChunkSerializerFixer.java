package com.iafenvoy.iceandfire.mixin.datafix;

import com.iafenvoy.iceandfire.datafix.BlockDataFix;
import com.mojang.serialization.Codec;
import net.minecraft.world.ChunkSerializer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(ChunkSerializer.class)
public abstract class ChunkSerializerFixer {
    @ModifyArg(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/chunk/PalettedContainer;createPalettedContainerCodec(Lnet/minecraft/util/collection/IndexedIterable;Lcom/mojang/serialization/Codec;Lnet/minecraft/world/chunk/PalettedContainer$PaletteProvider;Ljava/lang/Object;)Lcom/mojang/serialization/Codec;"), index = 1)
    private static <T> Codec<T> fixBlockStateName(Codec<T> codec) {
        return codec.mapResult(BlockDataFix.fixBlockName(codec));
    }
}
