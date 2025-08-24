package com.iafenvoy.iceandfire.particle;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.particle.ParticleTextureSheet;
import net.minecraft.client.particle.SpriteBillboardParticle;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;
import org.joml.Quaternionf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class LightningBoltParticle extends SpriteBillboardParticle {
    private final LightningBoltParticleType params;
    private final List<BoltQuads> quads;

    public LightningBoltParticle(LightningBoltParticleType params, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
        super(clientWorld, d, e, f, g, h, i);
        this.params = params;
        this.quads = this.generate();
        this.scale = params.getSize();
        this.maxAge = params.getLifespan();
        this.gravityStrength = 0.0F;
        this.collidesWithWorld = false;
        this.setVelocity(0, 0, 0);
    }

    @Override
    public void buildGeometry(VertexConsumer vertexConsumer, Camera camera, float tickDelta) {
        Quaternionf quaternionf = new Quaternionf();
        this.getRotator().setRotation(quaternionf, camera, tickDelta);
        if (this.angle != 0) quaternionf.rotateZ(MathHelper.lerp(tickDelta, this.prevAngle, this.angle));
        MatrixStack matrixstack = new MatrixStack();
        matrixstack.multiply(quaternionf);
        Matrix4f matrix = matrixstack.peek().getPositionMatrix();
        VertexConsumerProvider provider = MinecraftClient.getInstance().getBufferBuilders().getEntityVertexConsumers();
        VertexConsumer consumer = provider.getBuffer(RenderLayer.getLightning());
        for (BoltQuads quad : this.quads)
            quad.getVecs().forEach(v -> consumer.vertex(matrix, (float) v.x, (float) v.y, (float) v.z).color(this.params.getColor().x(), this.params.getColor().y(), this.params.getColor().z(), this.params.getColor().w()));
    }

    @Override
    public int getBrightness(float partialTick) {
        return 240;
    }

    @Override
    public @NotNull ParticleTextureSheet getType() {
        return ParticleTextureSheet.CUSTOM;
    }

    public List<BoltQuads> generate() {
        List<BoltQuads> quads = new ArrayList<>();
        Vec3d pos = new Vec3d(this.x, this.y, this.z), diff = this.params.getTargetPos().subtract(pos);
        float totalDistance = (float) diff.length();
        for (int i = 0; i < this.params.getCount(); i++) {
            LinkedList<BoltInstructions> drawQueue = new LinkedList<>();
            drawQueue.add(new BoltInstructions(pos, 0, new Vec3d(0, 0, 0), null, false));
            while (!drawQueue.isEmpty()) {
                BoltInstructions data = drawQueue.poll();
                Vec3d perpendicularDist = data.perpendicularDist;
                float progress = data.progress + (1F / this.params.getSegments()) * (1 - this.params.getParallelNoise() + this.random.nextFloat() * this.params.getParallelNoise() * 2);
                Vec3d segmentEnd;
                if (progress >= 1) segmentEnd = this.params.getTargetPos();
                else {
                    float segmentDiffScale = MathHelper.sin((float) (Math.PI * progress));
                    float maxDiff = this.params.getSpreadFactor() * segmentDiffScale * totalDistance * (float) this.random.nextGaussian();
                    Vec3d randVec = findRandomOrthogonalVector(diff, this.random);
                    perpendicularDist = SegmentSpreader.memory(this.params.getCloseness()).getSegmentAdd(perpendicularDist, randVec, maxDiff, segmentDiffScale, progress);
                    // new vector is original + current progress through segments + perpendicular change
                    segmentEnd = diff.multiply(progress).add(pos).add(perpendicularDist);
                }
                float boltSize = this.params.getSize() * (0.5F + (1 - progress) * 0.5F);
                Pair<BoltQuads, QuadCache> quadData = this.createQuads(data.cache, data.start, segmentEnd, boltSize);
                quads.add(quadData.getLeft());

                if (segmentEnd == this.params.getTargetPos()) break; // break if we've reached the defined end point
                else if (!data.isBranch)
                    // continue the bolt if this is the primary (non-branch) segment
                    drawQueue.add(new BoltInstructions(segmentEnd, progress, perpendicularDist, quadData.getRight(), false));
                else if (this.random.nextFloat() < this.params.getBranchContinuationFactor())
                    // branch continuation
                    drawQueue.add(new BoltInstructions(segmentEnd, progress, perpendicularDist, quadData.getRight(), true));

                while (this.random.nextFloat() < this.params.getBranchInitiationFactor() * (1 - progress))
                    // branch initiation (probability decreases as progress increases)
                    drawQueue.add(new BoltInstructions(segmentEnd, progress, perpendicularDist, quadData.getRight(), true));
            }
        }
        return quads;
    }

    private static Vec3d findRandomOrthogonalVector(Vec3d vec, Random rand) {
        Vec3d newVec = new Vec3d(-0.5 + rand.nextDouble(), -0.5 + rand.nextDouble(), -0.5 + rand.nextDouble());
        return vec.crossProduct(newVec).normalize();
    }

    private Pair<BoltQuads, QuadCache> createQuads(QuadCache cache, Vec3d startPos, Vec3d end, float size) {
        Vec3d diff = end.subtract(startPos);
        Vec3d rightAdd = diff.crossProduct(new Vec3d(0.5, 0.5, 0.5)).normalize().multiply(size);
        Vec3d backAdd = diff.crossProduct(rightAdd).normalize().multiply(size), rightAddSplit = rightAdd.multiply(0.5F);

        Vec3d start = cache != null ? cache.prevEnd : startPos;
        Vec3d startRight = cache != null ? cache.prevEndRight : start.add(rightAdd);
        Vec3d startBack = cache != null ? cache.prevEndBack : start.add(rightAddSplit).add(backAdd);
        Vec3d endRight = end.add(rightAdd), endBack = end.add(rightAddSplit).add(backAdd);

        BoltQuads quads = new BoltQuads();
        quads.addQuad(start, end, endRight, startRight);
        quads.addQuad(startRight, endRight, end, start);

        quads.addQuad(startRight, endRight, endBack, startBack);
        quads.addQuad(startBack, endBack, endRight, startRight);

        return Pair.of(quads, new QuadCache(end, endRight, endBack));
    }

    public interface SegmentSpreader {

        /**
         * Don't remember where the last segment left off, just randomly move from the straight-line vector.
         */
        SegmentSpreader NO_MEMORY = (perpendicularDist, randVec, maxDiff, scale, progress) -> randVec.multiply(maxDiff);

        /**
         * Move from where the previous segment ended by a certain memory factor. Higher memory will restrict perpendicular movement.
         */
        static SegmentSpreader memory(float memoryFactor) {
            return (perpendicularDist, randVec, maxDiff, spreadScale, progress) -> {
                float nextDiff = maxDiff * (1 - memoryFactor);
                Vec3d cur = randVec.multiply(nextDiff);
                if (progress > 0.5F) {
                    // begin to come back to the center after we pass halfway mark
                    cur = cur.add(perpendicularDist.multiply(-1 * (1 - spreadScale)));
                }
                return perpendicularDist.add(cur);
            };
        }

        Vec3d getSegmentAdd(Vec3d perpendicularDist, Vec3d randVec, float maxDiff, float scale, float progress);
    }

    protected static class BoltInstructions {
        private final Vec3d start;
        private final Vec3d perpendicularDist;
        private final QuadCache cache;
        private final float progress;
        private final boolean isBranch;

        private BoltInstructions(Vec3d start, float progress, Vec3d perpendicularDist, QuadCache cache, boolean isBranch) {
            this.start = start;
            this.perpendicularDist = perpendicularDist;
            this.progress = progress;
            this.cache = cache;
            this.isBranch = isBranch;
        }
    }

    private record QuadCache(Vec3d prevEnd, Vec3d prevEndRight, Vec3d prevEndBack) {
    }

    public static class BoltQuads {
        private final List<Vec3d> vecs = new ArrayList<>();

        protected void addQuad(Vec3d... quadVecs) {
            this.vecs.addAll(Arrays.asList(quadVecs));
        }

        public List<Vec3d> getVecs() {
            return this.vecs;
        }
    }
}
