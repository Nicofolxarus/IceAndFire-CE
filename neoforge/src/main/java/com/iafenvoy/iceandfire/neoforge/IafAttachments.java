package com.iafenvoy.iceandfire.neoforge;

import com.iafenvoy.iceandfire.IceAndFire;
import com.iafenvoy.iceandfire.neoforge.component.EntityDataStorage;
import com.iafenvoy.iceandfire.neoforge.component.PortalDataStorage;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class IafAttachments {
    public static final DeferredRegister<AttachmentType<?>> REGISTRY = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, IceAndFire.MOD_ID);

    public static final DeferredHolder<AttachmentType<?>, AttachmentType<EntityDataStorage>> IAF_ENTITY_DATA = register("iaf_entity_data", () -> AttachmentType.serializable(holder -> new EntityDataStorage(holder instanceof LivingEntity living ? living : null)).copyOnDeath().build());
    public static final DeferredHolder<AttachmentType<?>, AttachmentType<PortalDataStorage>> PORTAL_DATA = register("portal_data", () -> AttachmentType.serializable(holder -> new PortalDataStorage(holder instanceof PlayerEntity player ? player : null)).copyOnDeath().build());

    private static <T> DeferredHolder<AttachmentType<?>, AttachmentType<T>> register(String id, Supplier<AttachmentType<T>> type) {
        return REGISTRY.register(id, type);
    }
}
