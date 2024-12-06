package com.iafenvoy.iceandfire.registry;

import com.iafenvoy.iceandfire.IceAndFire;
import com.iafenvoy.iceandfire.world.processor.DreadPortalProcessor;
import com.iafenvoy.iceandfire.world.processor.DreadRuinProcessor;
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

    public static final DeferredSupplier<StructureProcessorType<GraveyardProcessor>> GRAVEYARD_PROCESSOR = registerProcessor("graveyard_processor", () -> () -> GraveyardProcessor.CODEC);
    public static final DeferredSupplier<StructureProcessorType<VillageHouseProcessor>> VILLAGE_HOUSE_PROCESSOR = registerProcessor("village_house_processor", () -> () -> VillageHouseProcessor.CODEC);
    public static final DeferredSupplier<StructureProcessorType<DreadRuinProcessor>> DREAD_MAUSOLEUM_PROCESSOR = registerProcessor("dread_mausoleum_processor", () -> () -> DreadRuinProcessor.CODEC);
    public static final DeferredSupplier<StructureProcessorType<DreadPortalProcessor>> DREAD_PORTAL_PROCESSOR = registerProcessor("dread_portal_processor", () -> () -> DreadPortalProcessor.CODEC);

    private static <P extends StructureProcessor> DeferredSupplier<StructureProcessorType<P>> registerProcessor(String name, Supplier<StructureProcessorType<P>> processor) {
        return REGISTRY.register(name, processor);
    }
}
