package com.iafenvoy.iceandfire.registry;

import com.iafenvoy.iceandfire.IceAndFire;
import com.iafenvoy.iceandfire.world.processor.DreadRuinProcessor;
import com.iafenvoy.iceandfire.world.processor.GorgonTempleProcessor;
import com.iafenvoy.iceandfire.world.processor.GraveyardProcessor;
import com.iafenvoy.iceandfire.world.processor.VillageHouseProcessor;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.DeferredSupplier;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorType;

import java.util.function.Supplier;

public final class IafProcessors {
    public static final DeferredRegister<StructureProcessorType<?>> REGISTRY = DeferredRegister.create(IceAndFire.MOD_ID, RegistryKeys.STRUCTURE_PROCESSOR);

    public static final DeferredSupplier<StructureProcessorType<DreadRuinProcessor>> DREAD_MAUSOLEUM_PROCESSOR = registerProcessor("dread_mausoleum_processor", () -> () -> DreadRuinProcessor.CODEC);
    public static final DeferredSupplier<StructureProcessorType<GorgonTempleProcessor>> GORGON_TEMPLE_PROCESSOR = registerProcessor("gorgon_temple_processor", () -> () -> GorgonTempleProcessor.CODEC);
    public static final DeferredSupplier<StructureProcessorType<GraveyardProcessor>> GRAVEYARD_PROCESSOR = registerProcessor("graveyard_processor", () -> () -> GraveyardProcessor.CODEC);
    public static final DeferredSupplier<StructureProcessorType<VillageHouseProcessor>> VILLAGE_HOUSE_PROCESSOR = registerProcessor("village_house_processor", () -> () -> VillageHouseProcessor.CODEC);

    private static <P extends StructureProcessor> DeferredSupplier<StructureProcessorType<P>> registerProcessor(String name, Supplier<StructureProcessorType<P>> processor) {
        return REGISTRY.register(name, processor);
    }
}
