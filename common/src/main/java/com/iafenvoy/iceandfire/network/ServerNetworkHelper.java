package com.iafenvoy.iceandfire.network;

import com.iafenvoy.iceandfire.entity.*;
import com.iafenvoy.iceandfire.entity.util.ISyncMount;
import com.iafenvoy.iceandfire.event.ServerEvents;
import com.iafenvoy.iceandfire.network.payload.*;
import dev.architectury.networking.NetworkManager;
import dev.architectury.platform.Platform;
import dev.architectury.utils.Env;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;

public class ServerNetworkHelper {
    public static void registerReceivers() {
        if (Platform.getEnvironment() == Env.SERVER) {
            NetworkManager.registerS2CPayloadType(DragonSetBurnBlockPayload.ID, DragonSetBurnBlockPayload.CODEC);
            NetworkManager.registerS2CPayloadType(LightningBoltS2CPayload.ID, LightningBoltS2CPayload.CODEC);
            NetworkManager.registerS2CPayloadType(StartRidingMobS2CPayload.ID, StartRidingMobS2CPayload.CODEC);
            NetworkManager.registerS2CPayloadType(UpdatePixieHousePayload.ID, UpdatePixieHousePayload.CODEC);
            NetworkManager.registerS2CPayloadType(UpdatePixieJarPayload.ID, UpdatePixieJarPayload.CODEC);
            NetworkManager.registerS2CPayloadType(UpdatePodiumPayload.ID, UpdatePodiumPayload.CODEC);
        }

        NetworkManager.registerReceiver(NetworkManager.Side.C2S, DragonControlPayload.ID, DragonControlPayload.CODEC, (payload, ctx) -> {
            PlayerEntity player = ctx.getPlayer();
            if (player != null) {
                Entity entity = player.getWorld().getEntityById(payload.dragonId());
                if (ServerEvents.isRidingOrBeingRiddenBy(entity, player)) {
                    BlockPos pos = payload.pos();
                        /*
                            For some of these entities the `setPos` is handled in `Entity#move`
                            Doing it here would cause server-side movement checks to fail (resulting in "moved wrongly" messages)
                        */
                    switch (entity) {
                        case DragonBaseEntity dragon -> {
                            if (dragon.isOwner(player))
                                dragon.setControlState(payload.controlState());
                        }
                        case HippogryphEntity hippogryph -> {
                            if (hippogryph.isOwner(player))
                                hippogryph.setControlState(payload.controlState());
                        }
                        case HippocampusEntity hippo -> {
                            if (hippo.isOwner(player))
                                hippo.setControlState(payload.controlState());
                            hippo.setPos(pos.getX(), pos.getY(), pos.getZ());
                        }
                        case DeathWormEntity deathWorm -> {
                            deathWorm.setControlState(payload.controlState());
                            deathWorm.setPos(pos.getX(), pos.getY(), pos.getZ());
                        }
                        case AmphithereEntity amphithere -> {
                            if (amphithere.isOwner(player))
                                amphithere.setControlState(payload.controlState());
                            // TODO :: Is this handled by Entity#move due to recent changes?
                            amphithere.setPos(pos.getX(), pos.getY(), pos.getZ());
                        }
                        default -> {
                        }
                    }
                }
            }
        });
        NetworkManager.registerReceiver(NetworkManager.Side.C2S, MultipartInteractPayload.ID, MultipartInteractPayload.CODEC, (payload, ctx) -> {
            PlayerEntity player = ctx.getPlayer();
            ctx.queue(() -> {
                if (player != null && player.getWorld() instanceof ServerWorld serverWorld) {
                    Entity entity = serverWorld.getEntity(payload.creatureID());
                    if (entity instanceof LivingEntity livingEntity) {
                        double dist = player.distanceTo(livingEntity);
                        if (dist < 100) {
                            float dmg = payload.dmg();
                            if (dmg > 0F) livingEntity.damage(player.getWorld().damageSources.mobAttack(player), dmg);
                            else livingEntity.interact(player, Hand.MAIN_HAND);
                        }
                    }
                }
            });
        });
        NetworkManager.registerReceiver(NetworkManager.Side.C2S, PlayerHitMultipartPayload.ID, PlayerHitMultipartPayload.CODEC, (payload, ctx) -> {
            PlayerEntity player = ctx.getPlayer();
            if (player != null) {
                Entity entity = player.getWorld().getEntityById(payload.entityId());
                if (entity instanceof LivingEntity livingEntity) {
                    double dist = player.distanceTo(livingEntity);
                    if (dist < 100) {
                        player.attack(livingEntity);
                        if (livingEntity instanceof HydraEntity hydra)
                            hydra.triggerHeadFlags(payload.index());
                    }
                }
            }
        });
        NetworkManager.registerReceiver(NetworkManager.Side.C2S, StartRidingMobC2SPayload.ID, StartRidingMobC2SPayload.CODEC, (payload, ctx) -> {
            PlayerEntity player = ctx.getPlayer();
            if (player != null) {
                Entity entity = player.getWorld().getEntityById(payload.dragonId());
                if (entity instanceof ISyncMount && entity instanceof TameableEntity tamable)
                    if (tamable.isOwner(player) && tamable.distanceTo(player) < 14)
                        if (payload.ride()) {
                            if (payload.baby()) tamable.startRiding(player, true);
                            else player.startRiding(tamable, true);
                        } else {
                            if (payload.baby()) tamable.stopRiding();
                            else player.stopRiding();
                        }
            }
        });
    }
}
