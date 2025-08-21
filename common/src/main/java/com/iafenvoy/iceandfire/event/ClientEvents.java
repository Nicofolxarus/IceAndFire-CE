package com.iafenvoy.iceandfire.event;

import com.iafenvoy.iceandfire.config.IafClientConfig;
import com.iafenvoy.iceandfire.data.component.ChainData;
import com.iafenvoy.iceandfire.data.component.FrozenData;
import com.iafenvoy.iceandfire.data.component.MiscData;
import com.iafenvoy.iceandfire.data.component.SirenData;
import com.iafenvoy.iceandfire.entity.EntityDragonBase;
import com.iafenvoy.iceandfire.entity.EntityMultipartPart;
import com.iafenvoy.iceandfire.entity.EntitySiren;
import com.iafenvoy.iceandfire.entity.util.ICustomMoveController;
import com.iafenvoy.iceandfire.network.payload.DragonControlPayload;
import com.iafenvoy.iceandfire.particle.CockatriceBeamRender;
import com.iafenvoy.iceandfire.registry.IafKeybindings;
import com.iafenvoy.iceandfire.registry.IafParticles;
import com.iafenvoy.iceandfire.render.block.RenderFrozenState;
import com.iafenvoy.iceandfire.render.entity.RenderChain;
import com.iafenvoy.uranus.event.Event;
import dev.architectury.event.EventResult;
import dev.architectury.networking.NetworkManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.Perspective;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;

import java.util.UUID;
import java.util.function.Consumer;

@Environment(EnvType.CLIENT)
public class ClientEvents {
    public static final Event<Consumer<LivingEntity>> LIVING_TICK = new Event<>(listeners -> living -> listeners.forEach(x -> x.accept(living)));
    private static final Identifier SIREN_SHADER = Identifier.of("iceandfire", "shaders/post/siren.json");
    public static int currentView = 0;

    public static void onCameraSetup(Camera camera) {
        PlayerEntity player = MinecraftClient.getInstance().player;
        if (player != null && player.getVehicle() instanceof EntityDragonBase) {
            float scale = ((EntityDragonBase) player.getVehicle()).getRenderSize() / 3;
            if (MinecraftClient.getInstance().options.getPerspective() == Perspective.THIRD_PERSON_BACK ||
                    MinecraftClient.getInstance().options.getPerspective() == Perspective.THIRD_PERSON_FRONT) {
                if (currentView == 1) camera.moveBy(-camera.clipToSpace(scale * 1.2F), 0F, 0);
                else if (currentView == 2) camera.moveBy(-camera.clipToSpace(scale * 3F), 0F, 0);
                else if (currentView == 3) camera.moveBy(-camera.clipToSpace(scale * 5F), 0F, 0);
            }
        }
    }

    public static EventResult onEntityInteract(PlayerEntity player, Entity entity, Hand hand) {
        // Hook multipart
        if (entity instanceof EntityMultipartPart) return EventResult.interruptTrue();
        return EventResult.pass();
    }

    public static void onLivingUpdate(LivingEntity entity) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (entity instanceof ICustomMoveController moveController) {
            if (entity.getVehicle() != null && entity.getVehicle() == mc.player) {
                byte previousState = moveController.getControlState();
                moveController.dismount(mc.options.sneakKey.isPressed());
                byte controlState = moveController.getControlState();
                if (controlState != previousState)
                    NetworkManager.sendToServer(new DragonControlPayload(entity.getId(), controlState, entity.getBlockPos()));
            }
        }
        if (entity instanceof PlayerEntity player && player.getWorld().isClient) {
            if (player.getVehicle() instanceof ICustomMoveController controller) {
                Entity vehicle = player.getVehicle();
                byte previousState = controller.getControlState();
                controller.up(mc.options.jumpKey.isPressed());
                controller.down(IafKeybindings.DRAGON_DOWN.isPressed());
                controller.attack(IafKeybindings.DRAGON_STRIKE.isPressed());
                controller.dismount(mc.options.sneakKey.isPressed());
                controller.strike(IafKeybindings.DRAGON_BREATH.isPressed());
                byte controlState = controller.getControlState();
                if (controlState != previousState)
                    NetworkManager.sendToServer(new DragonControlPayload(vehicle.getId(), controlState, vehicle.getBlockPos()));
            }
            GameRenderer renderer = MinecraftClient.getInstance().gameRenderer;
            SirenData sirenData = SirenData.get(player);
            if (IafClientConfig.INSTANCE.sirenShader.getValue() && sirenData.getCharmedByUUID().isEmpty() && renderer.getPostProcessor() != null)
                if (SIREN_SHADER.toString().equals(renderer.getPostProcessor().getName()))
                    renderer.disablePostProcessor();
            if (sirenData.getCharmedByUUID().isEmpty()) return;
            if (IafClientConfig.INSTANCE.sirenShader.getValue() && !sirenData.isCharmed() && renderer.getPostProcessor() != null && SIREN_SHADER.toString().equals(renderer.getPostProcessor().getName()))
                renderer.disablePostProcessor();
            if (sirenData.isCharmed()) {
                if (entity.getRandom().nextInt(40) == 0) {
                    Entity e = mc.world.getEntityLookup().get(sirenData.getCharmedByUUID().get());
                    if (e instanceof EntitySiren siren)
                        entity.getWorld().addParticle(IafParticles.SIREN_APPEARANCE.get(), player.getX(), player.getY(), player.getZ(), siren.getHairColor(), 0, 0);
                }
                if (IafClientConfig.INSTANCE.sirenShader.getValue() && renderer.getPostProcessor() == null)
                    renderer.loadPostProcessor(SIREN_SHADER);
            }
        }
    }

    public static void onPostRenderLiving(LivingEntity entity, float partialRenderTick, MatrixStack matrixStack, VertexConsumerProvider buffers, int light) {
        MiscData miscData = MiscData.get(entity);
        ClientWorld world = MinecraftClient.getInstance().world;
        miscData.checkScepterTarget(world.getEntityLookup()::get);
        for (UUID target : miscData.getTargetedByScepters())
            CockatriceBeamRender.render(entity, world.getEntityLookup().get(target), matrixStack, buffers, partialRenderTick);
        FrozenData frozenData = FrozenData.get(entity);
        if (frozenData.isFrozen)
            RenderFrozenState.render(entity, matrixStack, buffers, light, frozenData.frozenTicks);
        ChainData chainData = ChainData.get(entity);
        RenderChain.render(entity, matrixStack, buffers, light, chainData.getChainedTo());
    }
}