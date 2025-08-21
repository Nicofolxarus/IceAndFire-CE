package com.iafenvoy.iceandfire.data.component;

import com.iafenvoy.iceandfire.config.IafCommonConfig;
import com.iafenvoy.iceandfire.entity.EntitySiren;
import com.iafenvoy.iceandfire.entity.util.IHearsSiren;
import com.iafenvoy.iceandfire.impl.ComponentManager;
import com.iafenvoy.iceandfire.util.attachment.NeedUpdateData;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Uuids;
import net.minecraft.util.math.MathHelper;

import java.util.Optional;
import java.util.UUID;

@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
public class SirenData extends NeedUpdateData<LivingEntity> {
    public static final Codec<SirenData> CODEC = RecordCodecBuilder.create(i -> i.group(
            Codec.INT.fieldOf("charmTime").forGetter(SirenData::getCharmTime),
            Uuids.CODEC.optionalFieldOf("charmedByUUID").forGetter(SirenData::getCharmedByUUID)
    ).apply(i, SirenData::new));
    public static final PacketCodec<RegistryByteBuf, SirenData> PACKET_CODEC = PacketCodecs.registryCodec(CODEC);

    public int charmTime;
    private Optional<UUID> charmedByUUID = Optional.empty();

    public SirenData() {
    }

    private SirenData(int charmTime, Optional<UUID> charmedByUUID) {
        this.charmTime = charmTime;
        this.charmedByUUID = charmedByUUID;
    }

    @Override
    public void tick(LivingEntity living) {
        if (!(living instanceof PlayerEntity || living instanceof MerchantEntity || living instanceof IHearsSiren))
            return;
        if (this.charmedByUUID.isEmpty() || !(living.getWorld() instanceof ServerWorld world) || !(world.getEntity(this.charmedByUUID.get()) instanceof EntitySiren siren))
            return;

        if (siren.isActuallySinging()) {
            if (EntitySiren.isWearingEarplugs(living) || this.charmTime > IafCommonConfig.INSTANCE.siren.maxSingTime.getValue()) {
                siren.singCooldown = IafCommonConfig.INSTANCE.siren.timeBetweenSongs.getValue();
                this.clearCharm();
                return;
            }

            if (!siren.isAlive() || living.distanceTo(siren) > EntitySiren.SEARCH_RANGE * 2 || living instanceof PlayerEntity player && (player.isCreative() || player.isSpectator())) {
                this.clearCharm();
                return;
            }

            if (living.distanceTo(siren) < 5) {
                siren.singCooldown = IafCommonConfig.INSTANCE.siren.timeBetweenSongs.getValue();
                siren.setSinging(false);
                siren.setTarget(living);
                siren.setAttacking(true);
                siren.triggerOtherSirens(living);
                this.clearCharm();
                return;
            }

            this.charmTime++;
            if (living.getRandom().nextInt(7) == 0)
                for (int i = 0; i < 5; i++)
                    living.getWorld().addParticle(ParticleTypes.HEART,
                            living.getX() + ((living.getRandom().nextDouble() - 0.5D) * 3),
                            living.getY() + ((living.getRandom().nextDouble() - 0.5D) * 3),
                            living.getZ() + ((living.getRandom().nextDouble() - 0.5D) * 3),
                            0, 0, 0);

            if (living.horizontalCollision)
                living.setJumping(true);

            double motionXAdd = (Math.signum(siren.getX() - living.getX()) * 0.5D - living.getVelocity().x) * 0.100000000372529;
            double motionYAdd = (Math.signum(siren.getY() - living.getY() + 1) * 0.5D - living.getVelocity().y) * 0.100000000372529;
            double motionZAdd = (Math.signum(siren.getZ() - living.getZ()) * 0.5D - living.getVelocity().z) * 0.100000000372529;

            living.setVelocity(living.getVelocity().add(motionXAdd, motionYAdd, motionZAdd));
            if (living.hasVehicle()) living.stopRiding();
            if (!(living instanceof PlayerEntity)) {
                double x = siren.getX() - living.getX();
                double y = siren.getY() - 1 - living.getY();
                double z = siren.getZ() - living.getZ();
                double radius = Math.sqrt(x * x + z * z);
                float xRot = (float) (-(MathHelper.atan2(y, radius) * (180D / Math.PI)));
                float yRot = (float) (MathHelper.atan2(z, x) * (180D / Math.PI)) - 90.0F;
                living.setPitch(this.updateRotation(living.getPitch(), xRot));
                living.setYaw(this.updateRotation(living.getYaw(), yRot));
            }
        }
    }

    public boolean isCharmed() {
        return this.charmedByUUID.isPresent();
    }

    public int getCharmTime() {
        return this.charmTime;
    }

    public Optional<UUID> getCharmedByUUID() {
        return this.charmedByUUID;
    }

    public void setCharmed(EntitySiren siren) {
        this.charmedByUUID = Optional.of(siren.getUuid());
        this.markDirty();
    }

    public void clearCharm() {
        this.charmedByUUID = Optional.empty();
        this.charmTime = 0;
        this.markDirty();
    }

    private float updateRotation(float angle, float targetAngle) {
        float f = MathHelper.wrapDegrees(targetAngle - angle);
        if (f > 30) f = 30f;
        if (f < -30) f = -30f;
        return angle + f;
    }

    public static SirenData get(LivingEntity living) {
        return ComponentManager.getSirenData(living);
    }
}
