package net.sorenon.mcxr.play.rendering;

import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Matrix3f;
import com.mojang.math.Matrix4f;
import com.mojang.math.Quaternion;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.Util;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.sorenon.mcxr.core.JOMLUtil;
import net.sorenon.mcxr.core.MCXRCore;
import net.sorenon.mcxr.core.Pose;
import net.sorenon.mcxr.core.Teleport;
import net.sorenon.mcxr.play.MCXRGuiManager;
import net.sorenon.mcxr.play.MCXRPlayClient;
import net.sorenon.mcxr.play.PlayOptions;
import net.sorenon.mcxr.play.input.XrInput;
import net.sorenon.mcxr.play.openxr.MCXRGameRenderer;
import org.joml.Math;
import org.joml.Quaternionf;
import org.joml.Vector3d;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL11;

import net.tr7zw.MapRenderer;

import java.util.OptionalDouble;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import static net.minecraft.client.gui.GuiComponent.GUI_ICONS_LOCATION;
import static net.minecraft.client.renderer.block.model.ItemTransforms.TransformType.THIRD_PERSON_LEFT_HAND;
import static net.minecraft.world.item.UseAnim.BLOCK;
import static net.sorenon.mcxr.core.JOMLUtil.convert;

//TODO third person renderer
public class VrFirstPersonRenderer {

    private static final MCXRGameRenderer XR_RENDERER = MCXRPlayClient.MCXR_GAME_RENDERER;

    private final MCXRGuiManager FGM;

    private final ModelPart[] slimArmModel = new ModelPart[2];
    private final ModelPart[] armModel = new ModelPart[2];

    private final MultiBufferSource.BufferSource customBufferSource = MultiBufferSource.immediate(new BufferBuilder(1000));

    //private Item filledMap = Registry.ITEM.get(new ResourceLocation("minecraft", "filled_map"));

    public VrFirstPersonRenderer(MCXRGuiManager MCXRGuiManager) {
        this.FGM = MCXRGuiManager;
        for (int slim = 0; slim < 2; slim++) {
            ModelPart[] arr = slim == 0 ? armModel : slimArmModel;
            for (int hand = 0; hand < 2; hand++) {
                CubeListBuilder armBuilder = CubeListBuilder.create();
                CubeListBuilder sleeveBuilder = CubeListBuilder.create();

                if (hand == 0) {
                    armBuilder.texOffs(32, 48);
                    sleeveBuilder.texOffs(48, 48);
                } else {
                    armBuilder.texOffs(40, 16);
                    sleeveBuilder.texOffs(40, 32);
                }

                if (slim == 0) {
                    armBuilder.addBox(0, 0, 0, 4, 12, 4);
                    sleeveBuilder.addBox(0, 0, 0, 4, 12, 4, new CubeDeformation(0.25F));
                } else {
                    armBuilder.addBox(0.5f, 0, 0, 3, 12, 4);
                    sleeveBuilder.addBox(0.5f, 0, 0, 3, 12, 4, new CubeDeformation(0.25F));
                }

                MeshDefinition modelData = new MeshDefinition();
                modelData.getRoot().addOrReplaceChild("arm", armBuilder, PartPose.ZERO);
                modelData.getRoot().addOrReplaceChild("sleeve", sleeveBuilder, PartPose.ZERO);

                arr[hand] = LayerDefinition.create(modelData, 64, 64).bakeRoot();
            }
        }
    }

    /**
     * This function contains a lot of depth hackery so each draw call has to be done in a specific order
     */
    public void renderLast(WorldRenderContext context) {
        Camera camera = context.camera();
        Entity camEntity = camera.getEntity();
        MultiBufferSource.BufferSource consumers = customBufferSource;
        PoseStack matrices = context.matrixStack();
        ClientLevel world = context.world();

        //Render gui
        if (FGM.position != null) {
            matrices.pushPose();
            Vec3 pos = FGM.position.subtract(convert(((RenderPass.XrWorld) XR_RENDERER.renderPass).eyePoses.getUnscaledPhysicalPose().getPos()));
            matrices.translate(pos.x, pos.y, pos.z);
            matrices.mulPose(new Quaternion((float) FGM.orientation.x, (float) FGM.orientation.y, (float) FGM.orientation.z, (float) FGM.orientation.w));
            renderGuiQuad(matrices.last(), consumers);
            matrices.popPose();
            consumers.endLastBatch();
        }

        if (camEntity != null) {
            renderShadow(context, camEntity);

            //Render vanilla crosshair ray if controller raytracing is disabled
            if (!FGM.isScreenOpen() && !MCXRCore.getCoreConfig().controllerRaytracing()) {
                Vec3 camPos = context.camera().getPosition();
                matrices.pushPose();

                double x = Mth.lerp(context.tickDelta(), camEntity.xOld, camEntity.getX());
                double y = Mth.lerp(context.tickDelta(), camEntity.yOld, camEntity.getY()) + camEntity.getEyeHeight();
                double z = Mth.lerp(context.tickDelta(), camEntity.zOld, camEntity.getZ());
                matrices.translate(x - camPos.x, y - camPos.y, z - camPos.z);

                matrices.mulPose(com.mojang.math.Vector3f.YP.rotationDegrees(-camEntity.getYRot() + 180.0F));
                matrices.mulPose(com.mojang.math.Vector3f.XP.rotationDegrees(90 - camEntity.getXRot()));

                Matrix4f model = matrices.last().pose();
                Matrix3f normal = matrices.last().normal();

                VertexConsumer consumer = consumers.getBuffer(LINE_CUSTOM_ALWAYS.apply(4.0));
                consumer.vertex(model, 0, 0, 0).color(0f, 0f, 0f, 1f).normal(normal, 0, -1, 0).endVertex();
                consumer.vertex(model, 0, -5, 0).color(0f, 0f, 0f, 1f).normal(normal, 0, -1, 0).endVertex();

                consumer = consumers.getBuffer(LINE_CUSTOM.apply(2.0, true));
                consumer.vertex(model, 0, 0, 0).color(1f, 0f, 0f, 1f).normal(normal, 0, -1, 0).endVertex();
                consumer.vertex(model, 0, -5, 0).color(0.7f, 0.7f, 0.7f, 1f).normal(normal, 0, -1, 0).endVertex();

                matrices.popPose();
            }

            var hitResult = Minecraft.getInstance().hitResult;
            if (hitResult != null && !FGM.isScreenOpen()) {
                Vec3 camPos = context.camera().getPosition();
                matrices.pushPose();

                double x = hitResult.getLocation().x();
                double y = hitResult.getLocation().y();
                double z = hitResult.getLocation().z();
                matrices.translate(x - camPos.x, y - camPos.y, z - camPos.z);

                if (hitResult.getType() == HitResult.Type.BLOCK) {
                    matrices.mulPose(((BlockHitResult) hitResult).getDirection().getRotation());
                } else {
                    matrices.mulPose(camera.rotation());
                    matrices.mulPose(com.mojang.math.Vector3f.XP.rotationDegrees(90.0F));
                }

                //cursor render part 1 (over water) - also in MCXRPlayClient.java
                matrices.scale(0.5f, 1, 0.5f);
                RenderType SHADOW_LAYER = RenderType.entityCutoutNoCull(GUI_ICONS_LOCATION);
                VertexConsumer vertexConsumer = consumers.getBuffer(SHADOW_LAYER);

                float hitC = 1.0f;
                if(XrInput.lastHit!=null){
                    matrices.mulPose(com.mojang.math.Vector3f.YP.rotationDegrees(45.0F));
                    hitC=0.0f;
                }
                PoseStack.Pose entry = matrices.last();

                vertexConsumer.vertex(entry.pose(), -0.3f + (0.5f / 16f), 0.005f, -0.3f + (0.5f / 16f)).color(1.0F, hitC, hitC, 1.0f).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).normal(0.0F, 0.0F, 1.0F).endVertex();
                vertexConsumer.vertex(entry.pose(), -0.3f + (0.5f / 16f), 0.005f, 0.3f + (0.5f / 16f)).color(1.0F, hitC, hitC, 1.0f).uv(0, 0.0625f).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).normal(0.0F, 0.0F, 1.0F).endVertex();
                vertexConsumer.vertex(entry.pose(), 0.3f + (0.5f / 16f), 0.005f, 0.3f + (0.5f / 16f)).color(1.0F, hitC, hitC, 1.0f).uv(0.0625f, 0.0625f).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).normal(0.0F, 0.0F, 1.0F).endVertex();
                vertexConsumer.vertex(entry.pose(), 0.3f + (0.5f / 16f), 0.005f, -0.3f + (0.5f / 16f)).color(1.0F, hitC, hitC, 1.0f).uv(0.0625f, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).normal(0.0F, 0.0F, 1.0F).endVertex();

                matrices.popPose();
            }
        }

        if (camEntity instanceof LocalPlayer player && FGM.isScreenOpen()) {
            render(player, getLight(camera, world), matrices, consumers, context.tickDelta());
        }

        for (int handIndex = 0; handIndex < 2; handIndex++) {
            if (!XrInput.handsActionSet.grip.isActive[handIndex]) {
                continue;
            }

            //Draw the hand ray and debug lines
            matrices.pushPose(); //1

            Pose pose = XrInput.handsActionSet.gripPoses[handIndex].getMinecraftPose();
            Vec3 gripPos = convert(pose.getPos());
            Vec3 camPos = context.camera().getPosition();

            if (handIndex != MCXRPlayClient.getMainHand() && XrInput.vanillaGameplayActionSet.teleport.currentState && PlayOptions.teleportEnabled) {
                matrices.pushPose();

                matrices.translate(-camPos.x, -camPos.y, -camPos.z);

                Matrix4f model = matrices.last().pose();
                Matrix3f normal = matrices.last().normal();

                //Draw teleport ray
                float maxDistHor = 7;
                float maxDistVer = 1.5f;

                Player player = Minecraft.getInstance().player;

                Vector3f dir = pose.getOrientation().rotateX((float) java.lang.Math.toRadians(PlayOptions.handPitchAdjust), new Quaternionf()).transform(new Vector3f(0, -1, 0));

                var stage1 = Teleport.fireRayFromHand(player, JOMLUtil.convert(pose.getPos()), JOMLUtil.convert(dir));
                Vec3 hitPos1 = stage1.getA();
                Vec3 finalPos = stage1.getB();

                boolean blocked;
                if (finalPos != null) {
                    blocked = false;
                    if (hitPos1 == null) {
                        hitPos1 = finalPos;
                    }
                } else {
                    hitPos1 = hitPos1.subtract(JOMLUtil.convert(dir).scale(0.05));
                    var stage2 = Teleport.fireFallRay(player, hitPos1);
                    finalPos = stage2.getA();
                    blocked = !stage2.getB();

                    VertexConsumer consumer = consumers.getBuffer(LINE_CUSTOM.apply(2.0, true));
                    if (blocked) {
                        consumer.vertex(model, (float) hitPos1.x, (float) hitPos1.y, (float) hitPos1.z).color(0.7f, 0.1f, 0.1f, 1).normal(normal, 0, -1, 0).endVertex();
                        consumer.vertex(model, (float) hitPos1.x, (float) finalPos.y, (float) hitPos1.z).color(0.7f, 0.1f, 0.1f, 1).normal(normal, 0, -1, 0).endVertex();
                    } else {
                        consumer.vertex(model, (float) hitPos1.x, (float) hitPos1.y, (float) hitPos1.z).color(0.1f, 0.1f, 0.7f, 1).normal(normal, 0, -1, 0).endVertex();
                        consumer.vertex(model, (float) hitPos1.x, (float) finalPos.y, (float) hitPos1.z).color(0.1f, 0.1f, 0.7f, 1).normal(normal, 0, -1, 0).endVertex();
                    }
                }

                matrices.pushPose();

                if (gripPos.y > hitPos1.y) {
                    matrices.translate((float) gripPos.x, (float) gripPos.y, (float) gripPos.z);
                    for (int i = 0; i <= 16; ++i) {
                        stringVertex((float) hitPos1.x - (float) gripPos.x, (float) hitPos1.y - (float) gripPos.y, (float) hitPos1.z - (float) gripPos.z, consumers.getBuffer(LINE_CUSTOM_ALWAYS.apply(5.)), matrices.last(), i / 16f, (i + 1) / 16f, blocked);
                    }
                } else {
                    matrices.translate((float) hitPos1.x, (float) hitPos1.y, (float) hitPos1.z);
                    for (int i = 0; i <= 16; ++i) {
                        stringVertex((float) gripPos.x - (float) hitPos1.x, (float) gripPos.y - (float) hitPos1.y, (float) gripPos.z - (float) hitPos1.z, consumers.getBuffer(LINE_CUSTOM_ALWAYS.apply(5.)), matrices.last(), i / 16f, (i + 1) / 16f, blocked);
                    }
                }

                matrices.popPose();
                matrices.pushPose();
                matrices.translate(finalPos.x, finalPos.y, finalPos.z);
                PoseStack.Pose entry = matrices.last();

                VertexConsumer vertexConsumer = context.consumers().getBuffer(RenderType.entityTranslucent(new ResourceLocation("textures/misc/shadow.png")));

                float alpha = 0.6f;
                float radius = camEntity.getBbWidth() / 2;
                float y0 = 0.005f;

                vertexConsumer.vertex(entry.pose(), -radius, y0, -radius).color(0.1F, 0.1F, 1.0F, alpha).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).normal(entry.normal(), 0.0F, 1.0F, 0.0F).endVertex();
                vertexConsumer.vertex(entry.pose(), -radius, y0, radius).color(0.1F, 0.1F, 1.0F, alpha).uv(0, 1).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).normal(entry.normal(), 0.0F, 1.0F, 0.0F).endVertex();
                vertexConsumer.vertex(entry.pose(), radius, y0, radius).color(0.1F, 0.1F, 1.0F, alpha).uv(1, 1).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).normal(entry.normal(), 0.0F, 1.0F, 0.0F).endVertex();
                vertexConsumer.vertex(entry.pose(), radius, y0, -radius).color(0.1F, 0.1F, 1.0F, alpha).uv(1, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).normal(entry.normal(), 0.0F, 1.0F, 0.0F).endVertex();

                matrices.popPose();
                matrices.popPose();
            }

            matrices.translate(gripPos.x - camPos.x, gripPos.y - camPos.y, gripPos.z - camPos.z);

            float scale = MCXRPlayClient.getCameraScale();
            matrices.scale(scale, scale, scale);

            matrices.pushPose(); //2
            matrices.mulPose(
                    convert(
                            pose.getOrientation()
                                    .rotateX(Math.toRadians(PlayOptions.handPitchAdjust), new Quaternionf())
                    )
            );
            boolean debug = Minecraft.getInstance().options.renderDebug;

            if (handIndex == MCXRPlayClient.getMainHand()) {
                //Draw crosshair ray
                Matrix4f model = matrices.last().pose();
                Matrix3f normal = matrices.last().normal();

                if (debug) {
                    VertexConsumer consumer = consumers.getBuffer(LINE_CUSTOM_ALWAYS.apply(4.0));
                    consumer.vertex(model, 0, 0, 0).color(0f, 0f, 0f, 1f).normal(normal, 0, -1, 0).endVertex();
                    consumer.vertex(model, 0, -5, 0).color(0f, 0f, 0f, 1f).normal(normal, 0, -1, 0).endVertex();

                    consumer = consumers.getBuffer(LINE_CUSTOM.apply(2.0, false));
                    consumer.vertex(model, 0, 0, 0).color(1f, 0f, 0f, 1f).normal(normal, 0, -1, 0).endVertex();
                    consumer.vertex(model, 0, -5, 0).color(0.7f, 0.7f, 0.7f, 1f).normal(normal, 0, -1, 0).endVertex();
                }
                if (FGM.isScreenOpen()) {
                    VertexConsumer consumer = consumers.getBuffer(LINE_CUSTOM_ALWAYS.apply(2.0));
                    consumer.vertex(model, 0, 0, 0).color(0.1f, 0.1f, 0.1f, 1f).normal(normal, 0, -1, 0).endVertex();
                    consumer.vertex(model, 0, -0.5f, 0).color(0.1f, 0.1f, 0.1f, 1f).normal(normal, 0, -1, 0).endVertex();

                    consumer = consumers.getBuffer(LINE_CUSTOM.apply(4.0, false));
                    consumer.vertex(model, 0, 0, 0).color(1f, 1f, 1f, 1f).normal(normal, 0, -1, 0).endVertex();
                    consumer.vertex(model, 0, -1, 0).color(1f, 1f, 1f, 1f).normal(normal, 0, -1, 0).endVertex();
                }
            }


            if (debug) {
                renderCrosshair(consumers, context.matrixStack(), 0.05f, false);
            }

            matrices.popPose(); //2

            if (debug) {
                matrices.mulPose(
                        convert(
                                pose.getOrientation()
                        )
                );
                renderCrosshair(consumers, context.matrixStack(), 0.1f, false);
            }

            matrices.popPose(); //1
        }

        consumers.endBatch();

        //Render HUD
        if (!FGM.isScreenOpen() && XrInput.handsActionSet.grip.isActive[0]) {
            matrices.pushPose();

            transformToHand(matrices, 0, context.tickDelta());

            matrices.mulPose(com.mojang.math.Vector3f.XP.rotationDegrees(-90.0F));
            matrices.mulPose(com.mojang.math.Vector3f.YP.rotationDegrees(180.0F));

            matrices.translate(-2 / 16f, -12 / 16f, 0);

            matrices.pushPose();
            matrices.translate(2 / 16f, 9 / 16f, -1 / 16f);
            matrices.mulPose(com.mojang.math.Vector3f.XP.rotationDegrees(-75f));
            renderGuiQuad(matrices.last(), consumers);
            consumers.endLastBatch();
            matrices.popPose();

            matrices.popPose();
        }
    }

    private static void stringVertex(float x,
                                     float y,
                                     float z,
                                     VertexConsumer buffer,
                                     PoseStack.Pose normal,
                                     float startPercent,
                                     float endPercent,
                                     boolean blocked) {
        float x1 = x * startPercent;
        float y1 = y * (startPercent * startPercent + startPercent) * 0.5F;
        float z1 = z * startPercent;

        float x2 = x * endPercent;
        float y2 = y * (endPercent * endPercent + endPercent) * 0.5F;
        float z2 = z * endPercent;

        float dx = x2 - x1;
        float dy = y2 - y1;
        float dz = z2 - z1;
        float n1 = Mth.sqrt(dx * dx * dy * dy + dz * dz);
        dx /= n1;
        dy /= n1;
        dz /= n1;
        if (blocked) {
            buffer.vertex(normal.pose(), x1, y1, z1).color(1, 0.3f, 0.3f, 1).normal(normal.normal(), dx, dy, dz).endVertex();
            buffer.vertex(normal.pose(), x2, y2, z2).color(1, 0.3f, 0.3f, 1).normal(normal.normal(), dx, dy, dz).endVertex();
        } else {
            buffer.vertex(normal.pose(), x1, y1, z1).color(0.3f, 0.3f, 1, 1).normal(normal.normal(), dx, dy, dz).endVertex();
            buffer.vertex(normal.pose(), x2, y2, z2).color(0.3f, 0.3f, 1, 1).normal(normal.normal(), dx, dy, dz).endVertex();
        }
    }

    public static int getLight(Camera camera, Level world) {
        return LightTexture.pack(world.getBrightness(LightLayer.BLOCK, camera.getBlockPosition()), world.getBrightness(LightLayer.SKY, camera.getBlockPosition()));
    }

    public void transformToHand(PoseStack matrices, int hand, float tickDelta) {
        Pose pose = XrInput.handsActionSet.gripPoses[hand].getMinecraftPose();
        Vec3 gripPos = convert(pose.getPos());
        Vector3f eyePos = ((RenderPass.XrWorld) XR_RENDERER.renderPass).eyePoses.getMinecraftPose().getPos();

        //Transform to controller
        matrices.translate(gripPos.x - eyePos.x(), gripPos.y - eyePos.y(), gripPos.z - eyePos.z());
        matrices.mulPose(convert(pose.getOrientation()));

        //Apply adjustments
        matrices.mulPose(com.mojang.math.Vector3f.XP.rotationDegrees(-90.0F));
        matrices.scale(0.4f, 0.4f, 0.4f);

        float scale = MCXRPlayClient.getCameraScale(tickDelta);
        matrices.scale(scale, scale, scale);

        matrices.translate(0, 1 / 16f, -1.5f / 16f);
        matrices.mulPose(com.mojang.math.Vector3f.XP.rotationDegrees(PlayOptions.handPitchAdjust));

        if (hand == MCXRPlayClient.getMainHand()) {
            float swing = -0.1f * Mth.sin((float) (Math.sqrt(Minecraft.getInstance().player.getAttackAnim(tickDelta)) * Math.PI * 2));
            matrices.translate(0,0, swing);
        }
    }

    public void renderShadow(WorldRenderContext context, Entity camEntity) {
        PoseStack matrices = context.matrixStack();
        Vec3 camPos = context.camera().getPosition();
        matrices.pushPose();
        double x = Mth.lerp(context.tickDelta(), camEntity.xOld, camEntity.getX());
        double y = Mth.lerp(context.tickDelta(), camEntity.yOld, camEntity.getY());
        double z = Mth.lerp(context.tickDelta(), camEntity.zOld, camEntity.getZ());
        matrices.translate(x - camPos.x, y - camPos.y, z - camPos.z);
        PoseStack.Pose entry = matrices.last();

        RenderType SHADOW_LAYER = RenderType.entityShadow(new ResourceLocation("textures/misc/shadow.png"));
        VertexConsumer vertexConsumer = context.consumers().getBuffer(SHADOW_LAYER);

        float alpha = Mth.clamp((float) Math.sqrt(camPos.distanceToSqr(x, y, z)) / 2f - 0.5f, 0.25f, 1);
        float radius = camEntity.getBbWidth() / 2;
        float y0 = 0.005f;

        vertexConsumer.vertex(entry.pose(), -radius, y0, -radius).color(1.0F, 1.0F, 1.0F, alpha).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).normal(entry.normal(), 0.0F, 1.0F, 0.0F).endVertex();
        vertexConsumer.vertex(entry.pose(), -radius, y0, radius).color(1.0F, 1.0F, 1.0F, alpha).uv(0, 1).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).normal(entry.normal(), 0.0F, 1.0F, 0.0F).endVertex();
        vertexConsumer.vertex(entry.pose(), radius, y0, radius).color(1.0F, 1.0F, 1.0F, alpha).uv(1, 1).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).normal(entry.normal(), 0.0F, 1.0F, 0.0F).endVertex();
        vertexConsumer.vertex(entry.pose(), radius, y0, -radius).color(1.0F, 1.0F, 1.0F, alpha).uv(1, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).normal(entry.normal(), 0.0F, 1.0F, 0.0F).endVertex();

        matrices.popPose();
    }

    private void renderGuiQuad(PoseStack.Pose transform, MultiBufferSource consumers) {
        RenderTarget guiFramebuffer = FGM.guiPostProcessRenderTarget;

        float x = FGM.size / 2;
        float y = FGM.size * guiFramebuffer.height / guiFramebuffer.width;

        VertexConsumer consumer;
        Matrix4f modelMatrix = transform.pose();
        Matrix3f normalMatrix = transform.normal();

//        consumer = consumers.getBuffer(GUI_SHADOW.apply(MCXRPlayClient.INSTANCE.flatGuiManager.texture));
//        consumer.vertex(modelMatrix, -x - 0.005f, y - 0.005f, 0).color(255, 255, 255, 255).uv(1, 1).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(0).normal(normalMatrix, 0, 0, -1).endVertex();
//        consumer.vertex(modelMatrix, x - 0.005f, y - 0.005f, 0).color(255, 255, 255, 255).uv(0, 1).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(0).normal(normalMatrix, 0, 0, -1).endVertex();
//        consumer.vertex(modelMatrix, x - 0.005f, 0 - 0.005f, 0).color(255, 255, 255, 255).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(0).normal(normalMatrix, 0, 0, -1).endVertex();
//        consumer.vertex(modelMatrix, -x - 0.005f, 0 - 0.005f, 0).color(255, 255, 255, 255).uv(1, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(0).normal(normalMatrix, 0, 0, -1).endVertex();
        consumer = consumers.getBuffer(GUI_NO_DEPTH_TEST.apply(MCXRPlayClient.INSTANCE.MCXRGuiManager.guiRenderTexture));
        consumer.vertex(modelMatrix, -x, y, 0).color(255, 255, 255, 255).uv(1, 1).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).normal(normalMatrix, 0, 0, -1).endVertex();
        consumer.vertex(modelMatrix, x, y, 0).color(255, 255, 255, 255).uv(0, 1).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).normal(normalMatrix, 0, 0, -1).endVertex();
        consumer.vertex(modelMatrix, x, 0, 0).color(255, 255, 255, 255).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).normal(normalMatrix, 0, 0, -1).endVertex();
        consumer.vertex(modelMatrix, -x, 0, 0).color(255, 255, 255, 255).uv(1, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).normal(normalMatrix, 0, 0, -1).endVertex();
        consumer = consumers.getBuffer(DEPTH_ONLY.apply(MCXRPlayClient.INSTANCE.MCXRGuiManager.guiRenderTexture));
        consumer.vertex(modelMatrix, -x, y, 0).color(255, 255, 255, 255).uv(1, 1).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).normal(normalMatrix, 0, 0, -1).endVertex();
        consumer.vertex(modelMatrix, x, y, 0).color(255, 255, 255, 255).uv(0, 1).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).normal(normalMatrix, 0, 0, -1).endVertex();
        consumer.vertex(modelMatrix, x, 0, 0).color(255, 255, 255, 255).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).normal(normalMatrix, 0, 0, -1).endVertex();
        consumer.vertex(modelMatrix, -x, 0, 0).color(255, 255, 255, 255).uv(1, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).normal(normalMatrix, 0, 0, -1).endVertex();
    }



    public void render(LocalPlayer player,
                       int light,
                       PoseStack matrices,
                       MultiBufferSource consumers,
                       float deltaTick) {
        //Render held items
        for (int handIndex = 0; handIndex < 2; handIndex++) {
            if (!XrInput.handsActionSet.grip.isActive[handIndex]) {
                continue;
            }

            if (!FGM.isScreenOpen()) {
                ItemStack stack = handIndex == 0 ? player.getOffhandItem() : player.getMainHandItem();
                if (player.getMainArm() == HumanoidArm.LEFT) {
                    stack = handIndex == 1 ? player.getOffhandItem() : player.getMainHandItem();
                }

                if (!stack.isEmpty()) {
                    matrices.pushPose();
                    transformToHand(matrices, handIndex, deltaTick);

                    if (handIndex == MCXRPlayClient.getMainHand()) {
                        float swing = -0.6f * Mth.sin((float) (Math.sqrt(player.getAttackAnim(deltaTick)) * Math.PI * 2));
                        matrices.mulPose(com.mojang.math.Vector3f.XP.rotation(swing));
                    }

                    if (stack.getItem() == Items.CROSSBOW) {
                        float f = handIndex == 0 ? -1 : 1;
                        matrices.translate(f * -1.5 / 16f, 0, 0);
                        matrices.mulPose(Quaternion.fromXYZ(0, f * Math.toRadians(15), 0));
                    }

                    if (stack.getItem() == Items.TRIDENT && player.getUseItem() == stack) {
                        float k = (float) stack.getUseDuration() - ((float) player.getUseItemRemainingTicks() - deltaTick + 1);
                        float l = Math.min(k / 10, 1);
                        if (l > 0.1F) {
                            float m = Mth.sin((k - 0.1f) * 1.3f);
                            float n = l - 0.1f;
                            float o = m * n;
                            matrices.translate(0, o * 0.004, 0);
                        }
                        matrices.translate(0, 0, l * 0.2);
                        matrices.mulPose(Quaternion.fromXYZ(Math.toRadians(90), 0, 0));
                    }

                    if (stack.getItem() == Items.FILLED_MAP) {
                        MapRenderer.renderFirstPersonMap(matrices, consumers, light, stack, false, handIndex== 0);
                    }
                    else {
                        matrices.scale(1.5f,1.5f,1.5f);
                        //held item animations
                        InteractionHand curHand= handIndex == 0 ? InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND;
                        if(player.getUsedItemHand()==curHand && player.getUseItemRemainingTicks() > 0 && player.isUsingItem()) {
                            switch (stack.getUseAnimation()) {
                                case EAT:
                                case DRINK:
                                    float f = (float)player.getUseItemRemainingTicks() - deltaTick + 1.0F;
                                    float g = f / (float)stack.getUseDuration();
                                    if (g < 0.8F) {
                                        float h = Mth.abs(Mth.cos(f / 4.0F * 3.1415927F) * -0.07F);
                                        matrices.translate(0.0, (double)h, 0.0);
                                    }
                                    matrices.mulPose(Quaternion.fromXYZ(Math.toRadians(20), 0, 0));
                                    break;
                                case BLOCK: //hacky shield pose fix
                                    matrices.translate((2 * handIndex - 1) * -0.2 - 0.0465, 0.06 * (1 - handIndex), 0);
                                    matrices.mulPose(Quaternion.fromXYZ(0, 0, Math.toRadians((2 * handIndex - 1) * -3)));
                                    matrices.mulPose(Quaternion.fromXYZ(0, Math.toRadians((2 * handIndex - 1) * 45), 0));
                                    matrices.mulPose(Quaternion.fromXYZ(Math.toRadians(-50), 0, 0));
                            }
                        }

                        Minecraft.getInstance().getEntityRenderDispatcher().getItemInHandRenderer().renderItem(
                                player,
                                stack,
                                handIndex == 0 ? THIRD_PERSON_LEFT_HAND : ItemTransforms.TransformType.THIRD_PERSON_RIGHT_HAND,
                                handIndex == 0,
                                matrices,
                                consumers,
                                light
                        );
                    }
                    matrices.popPose();
                }
            }

            //Draw hand
            matrices.pushPose();

            transformToHand(matrices, handIndex, deltaTick);

            matrices.mulPose(com.mojang.math.Vector3f.XP.rotationDegrees(-90.0F));
            matrices.mulPose(com.mojang.math.Vector3f.YP.rotationDegrees(180.0F));

            matrices.translate(-2 / 16f, -12 / 16f, 0);

            matrices.pushPose();
            ModelPart armModel;
            if (player.getModelName().equals("slim")) {
                armModel = this.slimArmModel[handIndex];
            } else {
                armModel = this.armModel[handIndex];
            }

            VertexConsumer consumer = consumers.getBuffer(RenderType.entityTranslucent(player.getSkinTextureLocation()));
            armModel.render(matrices, consumer, light, OverlayTexture.NO_OVERLAY);
            matrices.popPose();

            matrices.popPose();

            consumers.getBuffer(RenderType.LINES); //Hello I'm a hack ;)
        }
    }

    public static void renderCrosshair(MultiBufferSource consumerProvider, PoseStack poseStack, float size, boolean depthTest) {
        renderCrosshair(consumerProvider, poseStack, size, depthTest, true, true, true);
    }

    public static void renderCrosshair(MultiBufferSource consumerProvider, PoseStack poseStack, float size, boolean depthTest, boolean drawX, boolean drawY, boolean drawZ) {
        Matrix4f model = poseStack.last().pose();
        Matrix3f normal = poseStack.last().normal() ;

        VertexConsumer consumer = consumerProvider.getBuffer(LINE_COLOR_ONLY.apply(4d, depthTest));
        if (drawX) {
            consumer.vertex(model, 0.0f, 0.0f, 0.0f).color(0, 0, 0, 255).normal(normal, 1.0F, 0.0F, 0.0F).endVertex();
            consumer.vertex(model, size, 0.0f, 0.0f).color(0, 0, 0, 255).normal(normal, 1.0F, 0.0F, 0.0F).endVertex();
        }

        if (drawY) {
            consumer.vertex(model, 0.0f, 0.0f, 0.0f).color(0, 0, 0, 255).normal(normal, 0.0F, 1.0F, 0.0F).endVertex();
            consumer.vertex(model, 0.0f, size, 0.0f).color(0, 0, 0, 255).normal(normal, 0.0F, 1.0F, 0.0F).endVertex();
        }

        if (drawZ) {
            consumer.vertex(model, 0.0f, 0.0f, 0.0f).color(0, 0, 0, 255).normal(normal, 0.0F, 0.0F, 1.0F).endVertex();
            consumer.vertex(model, 0.0f, 0.0f, size).color(0, 0, 0, 255).normal(normal, 0.0F, 0.0F, 1.0F).endVertex();
        }

        consumer = consumerProvider.getBuffer(LINE_CUSTOM.apply(2d, depthTest));

        if (drawX) {
            consumer.vertex(model, 0.0f, 0.0f, 0.0f).color(255, 0, 0, 255).normal(normal, 1.0F, 0.0F, 0.0F).endVertex();
            consumer.vertex(model, size, 0.0f, 0.0f).color(255, 0, 0, 255).normal(normal, 1.0F, 0.0F, 0.0F).endVertex();
        }

        if (drawY) {
            consumer.vertex(model, 0.0f, 0.0f, 0.0f).color(0, 255, 0, 255).normal(normal, 0.0F, 1.0F, 0.0F).endVertex();
            consumer.vertex(model, 0.0f, size, 0.0f).color(0, 255, 0, 255).normal(normal, 0.0F, 1.0F, 0.0F).endVertex();
        }

        if (drawZ) {
            consumer.vertex(model, 0.0f, 0.0f, 0.0f).color(127, 127, 255, 255).normal(normal, 0.0F, 0.0F, 1.0F).endVertex();
            consumer.vertex(model, 0.0f, 0.0f, size).color(127, 127, 255, 255).normal(normal, 0.0F, 0.0F, 1.0F).endVertex();
        }
    }


    public static final Function<ResourceLocation, RenderType> DEPTH_ONLY = Util.memoize((texture) -> {
        RenderTypeBuilder renderTypeBuilder = new RenderTypeBuilder(MCXRPlayClient.id("depth_only"), DefaultVertexFormat.NEW_ENTITY, VertexFormat.Mode.QUADS, 256, false, false);
        renderTypeBuilder.innerBuilder
                .setWriteMaskState(RenderStateShard.DEPTH_WRITE)
                .setShaderState(new RenderStateShard.ShaderStateShard(GameRenderer::getRendertypeEntityCutoutShader))
                .setTextureState(new RenderStateShard.TextureStateShard(texture, false, false))
                .setTransparencyState(RenderStateShard.NO_TRANSPARENCY)
                .setLightmapState(RenderStateShard.LIGHTMAP)
                .setOverlayState(RenderStateShard.OVERLAY);
        return renderTypeBuilder.build(true);
    });

    public static final Function<ResourceLocation, RenderType> GUI_NO_DEPTH_TEST = Util.memoize((texture) -> {
        Supplier<ShaderInstance> shader = GameRenderer::getNewEntityShader;
        if (FabricLoader.getInstance().isModLoaded("iris")) {
            shader = GameRenderer::getRendertypeEntityTranslucentShader;
        }

        RenderTypeBuilder renderTypeBuilder = new RenderTypeBuilder(MCXRPlayClient.id("gui_no_depth_test"), DefaultVertexFormat.NEW_ENTITY, VertexFormat.Mode.QUADS, 256, false, false);
        renderTypeBuilder.innerBuilder.
                setShaderState(new RenderStateShard.ShaderStateShard(shader))
                .setTextureState(new RenderStateShard.TextureStateShard(texture, false, false))
                .setTransparencyState(RenderStateShard.TRANSLUCENT_TRANSPARENCY)
                .setCullState(RenderStateShard.NO_CULL)
                .setLightmapState(RenderStateShard.LIGHTMAP)
                .setOverlayState(RenderStateShard.OVERLAY)
                .setDepthTestState(RenderStateShard.NO_DEPTH_TEST);
        return renderTypeBuilder.build(true);
    });

    public static final Function<ResourceLocation, RenderType> GUI_SHADOW = Util.memoize((texture) -> {
        RenderTypeBuilder renderTypeBuilder = new RenderTypeBuilder(MCXRPlayClient.id("gui_no_depth_test"), DefaultVertexFormat.NEW_ENTITY, VertexFormat.Mode.QUADS, 256, false, false);
        renderTypeBuilder.innerBuilder.
                setShaderState(new RenderStateShard.ShaderStateShard(GameRenderer::getRendertypeEntityTranslucentShader))
                .setTextureState(new RenderStateShard.TextureStateShard(texture, false, false))
                .setTransparencyState(RenderStateShard.TRANSLUCENT_TRANSPARENCY)
                .setCullState(RenderStateShard.NO_CULL)
                .setLightmapState(RenderStateShard.LIGHTMAP)
                .setOverlayState(RenderStateShard.OVERLAY)
                .setDepthTestState(new RenderStateShard.DepthTestStateShard("GL_GREATER", GL11.GL_GREATER));
        return renderTypeBuilder.build(true);
    });

    public static final Function<Double, RenderType> LINE_CUSTOM_ALWAYS = Util.memoize(aDouble -> {
        RenderTypeBuilder builder = new RenderTypeBuilder(MCXRPlayClient.id("line_always"), DefaultVertexFormat.POSITION_COLOR_NORMAL, VertexFormat.Mode.LINES, 16, false, false);
        builder.innerBuilder
                .setShaderState(new RenderStateShard.ShaderStateShard(GameRenderer::getRendertypeLinesShader))
                .setLineState(new RenderStateShard.LineStateShard(OptionalDouble.of(aDouble)))
                .setLayeringState(RenderStateShard.VIEW_OFFSET_Z_LAYERING)
                .setTransparencyState(RenderStateShard.TRANSLUCENT_TRANSPARENCY)
                .setWriteMaskState(RenderStateShard.COLOR_DEPTH_WRITE)
                .setCullState(RenderStateShard.NO_CULL)
                .setDepthTestState(RenderStateShard.NO_DEPTH_TEST);
        return builder.build(true);
    });

    public static final BiFunction<Double, Boolean, RenderType> LINE_CUSTOM = Util.memoize((aDouble, depthTest) -> {
        var depthTest1 = RenderStateShard.NO_DEPTH_TEST;
        if (depthTest) {
            depthTest1 = RenderStateShard.LEQUAL_DEPTH_TEST;
        }

        RenderTypeBuilder builder = new RenderTypeBuilder(MCXRPlayClient.id("line"), DefaultVertexFormat.POSITION_COLOR_NORMAL, VertexFormat.Mode.LINES, 16, false, false);
        builder.innerBuilder
                .setShaderState(new RenderStateShard.ShaderStateShard(GameRenderer::getRendertypeLinesShader))
                .setLineState(new RenderStateShard.LineStateShard(OptionalDouble.of(aDouble)))
                .setLayeringState(RenderStateShard.VIEW_OFFSET_Z_LAYERING)
                .setTransparencyState(RenderStateShard.TRANSLUCENT_TRANSPARENCY)
                .setWriteMaskState(RenderStateShard.COLOR_DEPTH_WRITE)
                .setCullState(RenderStateShard.NO_CULL)
                .setDepthTestState(depthTest1);
        return builder.build(true);
    });

    public static final BiFunction<Double, Boolean, RenderType> LINE_COLOR_ONLY = Util.memoize((lineWidth, depthTest) -> {
        var depthTest1 = RenderStateShard.NO_DEPTH_TEST;
        if (depthTest) {
            depthTest1 = RenderStateShard.LEQUAL_DEPTH_TEST;
        }

        RenderTypeBuilder builder = new RenderTypeBuilder(MCXRPlayClient.id("line_color_only"), DefaultVertexFormat.POSITION_COLOR_NORMAL, VertexFormat.Mode.LINES, 16, false, false);
        builder.innerBuilder
                .setShaderState(new RenderStateShard.ShaderStateShard(GameRenderer::getRendertypeLinesShader))
                .setLineState(new RenderStateShard.LineStateShard(OptionalDouble.of(lineWidth)))
                .setLayeringState(RenderStateShard.VIEW_OFFSET_Z_LAYERING)
                .setTransparencyState(RenderStateShard.TRANSLUCENT_TRANSPARENCY)
                .setWriteMaskState(RenderStateShard.COLOR_WRITE)
                .setCullState(RenderStateShard.NO_CULL)
                .setDepthTestState(depthTest1);
        return builder.build(true);
    });
}
