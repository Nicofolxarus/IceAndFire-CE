package com.iafenvoy.iceandfire.particle;

import com.iafenvoy.iceandfire.registry.IafParticles;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;
import net.minecraft.util.math.Vec3d;
import org.joml.Vector4f;

public class LightningBoltParticleType extends ParticleType<LightningBoltParticleType> implements ParticleEffect {
    private static final Codec<Vector4f> VECTOR4F_CODEC = RecordCodecBuilder.create(i -> i.group(
            Codec.FLOAT.fieldOf("x").forGetter(v -> v.x),
            Codec.FLOAT.fieldOf("y").forGetter(v -> v.y),
            Codec.FLOAT.fieldOf("z").forGetter(v -> v.z),
            Codec.FLOAT.fieldOf("w").forGetter(v -> v.w)
    ).apply(i, Vector4f::new));
    private static final MapCodec<LightningBoltParticleType> CODEC = RecordCodecBuilder.mapCodec(i -> i.group(
            Vec3d.CODEC.fieldOf("targetPos").forGetter(LightningBoltParticleType::getTargetPos),
            Codec.INT.optionalFieldOf("segments", 1).forGetter(LightningBoltParticleType::getSegments),
            Codec.INT.optionalFieldOf("count", 1).forGetter(LightningBoltParticleType::getCount),
            Codec.INT.optionalFieldOf("lifespan", 30).forGetter(LightningBoltParticleType::getLifespan),
            Codec.FLOAT.optionalFieldOf("size", 0.1F).forGetter(LightningBoltParticleType::getSize),
            Codec.FLOAT.optionalFieldOf("parallelNoise", 0.5F).forGetter(LightningBoltParticleType::getParallelNoise),
            Codec.FLOAT.optionalFieldOf("spreadFactor", 0.25F).forGetter(LightningBoltParticleType::getSpreadFactor),
            Codec.FLOAT.optionalFieldOf("branchInitiationFactor", 0.25F).forGetter(LightningBoltParticleType::getBranchInitiationFactor),
            Codec.FLOAT.optionalFieldOf("branchContinuationFactor", 0.15F).forGetter(LightningBoltParticleType::getBranchContinuationFactor),
            Codec.FLOAT.optionalFieldOf("closeness", 0.8F).forGetter(LightningBoltParticleType::getCloseness),
            VECTOR4F_CODEC.optionalFieldOf("color", new Vector4f(0.7F, 0.45F, 0.89F, 0.8F)).forGetter(LightningBoltParticleType::getColor)
    ).apply(i, LightningBoltParticleType::new));

    private final Vec3d targetPos;
    private final int segments, count, lifespan;
    private final float size, parallelNoise, spreadFactor, branchInitiationFactor, branchContinuationFactor, closeness;
    private final Vector4f color;

    protected LightningBoltParticleType(Vec3d targetPos, int segments, int count, int lifespan, float size, float parallelNoise, float spreadFactor, float branchInitiationFactor, float branchContinuationFactor, float closeness, Vector4f color) {
        super(true);
        this.targetPos = targetPos;
        this.segments = segments;
        this.count = count;
        this.lifespan = lifespan;
        this.size = size;
        this.parallelNoise = parallelNoise;
        this.spreadFactor = spreadFactor;
        this.branchInitiationFactor = branchInitiationFactor;
        this.branchContinuationFactor = branchContinuationFactor;
        this.closeness = closeness;
        this.color = color;
    }

    public static Builder builder(Vec3d targetPos) {
        return new Builder(targetPos);
    }

    @Override
    public ParticleType<?> getType() {
        return IafParticles.LIGHTNING_BOLT.get();
    }

    @Override
    public MapCodec<LightningBoltParticleType> getCodec() {
        return CODEC;
    }

    @Override
    public PacketCodec<? super RegistryByteBuf, LightningBoltParticleType> getPacketCodec() {
        return PacketCodecs.registryCodec(CODEC.codec());
    }

    public Vec3d getTargetPos() {
        return this.targetPos;
    }

    public int getSegments() {
        return this.segments;
    }

    public int getCount() {
        return this.count;
    }

    public int getLifespan() {
        return this.lifespan;
    }

    public float getSize() {
        return this.size;
    }

    public float getParallelNoise() {
        return this.parallelNoise;
    }

    public float getSpreadFactor() {
        return this.spreadFactor;
    }

    public float getBranchInitiationFactor() {
        return this.branchInitiationFactor;
    }

    public float getBranchContinuationFactor() {
        return this.branchContinuationFactor;
    }

    public float getCloseness() {
        return this.closeness;
    }

    public Vector4f getColor() {
        return this.color;
    }

    public static class Builder {
        private final Vec3d targetPos;
        private int segments = 1;
        private int count = 1;
        private int lifespan = 30;
        private float size = 0.1F;
        /**
         * How much variance is allowed in segment lengths (parallel to straight line).
         */
        private float parallelNoise = 0.5F;
        /**
         * How much variance is allowed perpendicular to the straight line vector. Scaled by distance and spread function.
         */
        private float spreadFactor = 0.25F;
        /**
         * The chance of creating an additional branch after a certain segment.
         */
        private float branchInitiationFactor = 0.25F;
        /**
         * The chance of a branch continuing (post-initiation).
         */
        private float branchContinuationFactor = 0.15F;
        private float closeness = 0.8F;
        private Vector4f color = new Vector4f(0.7F, 0.45F, 0.89F, 0.8F);

        public Builder(Vec3d targetPos) {
            this.targetPos = targetPos;
        }

        public Builder segments(int segments) {
            this.segments = segments;
            return this;
        }

        public Builder count(int count) {
            this.count = count;
            return this;
        }

        public Builder lifespan(int lifespan) {
            this.lifespan = lifespan;
            return this;
        }

        public Builder size(float size) {
            this.size = size;
            return this;
        }

        public Builder parallelNoise(float parallelNoise) {
            this.parallelNoise = parallelNoise;
            return this;
        }

        public Builder spreadFactor(float spreadFactor) {
            this.spreadFactor = spreadFactor;
            return this;
        }

        public Builder branchInitiationFactor(float branchInitiationFactor) {
            this.branchInitiationFactor = branchInitiationFactor;
            return this;
        }

        public Builder branchContinuationFactor(float branchContinuationFactor) {
            this.branchContinuationFactor = branchContinuationFactor;
            return this;
        }

        public Builder closeness(float closeness) {
            this.closeness = closeness;
            return this;
        }

        public Builder color(Vector4f color) {
            this.color = color;
            return this;
        }

        public LightningBoltParticleType build() {
            return new LightningBoltParticleType(this.targetPos, this.segments, this.count, this.lifespan, this.size, this.parallelNoise, this.spreadFactor, this.branchInitiationFactor, this.branchContinuationFactor, this.closeness, this.color);
        }
    }
}
