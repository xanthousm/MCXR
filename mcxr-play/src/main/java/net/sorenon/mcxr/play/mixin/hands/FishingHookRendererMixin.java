package net.sorenon.mcxr.play.mixin.hands;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix3f;
import com.mojang.math.Matrix4f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.FishingHookRenderer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.sorenon.mcxr.core.Pose;
import net.sorenon.mcxr.play.MCXRPlayClient;
import net.sorenon.mcxr.play.input.XrInput;
import net.sorenon.mcxr.play.openxr.MCXRGameRenderer;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import static net.sorenon.mcxr.core.JOMLUtil.convert;

@Mixin(FishingHookRenderer.class)
public class FishingHookRendererMixin {

    @Unique
    private static final MCXRGameRenderer XR_RENDERER = MCXRPlayClient.MCXR_GAME_RENDERER;

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/projectile/FishingHook;getX()D"), method = "render(Lnet/minecraft/world/entity/projectile/FishingHook;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", locals = LocalCapture.CAPTURE_FAILHARD)
    public void vrStringPos(FishingHook fishingHook, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, CallbackInfo ci, Player player, PoseStack.Pose pose, Matrix4f matrix4f, Matrix3f matrix3f, VertexConsumer vertexConsumer, int j, ItemStack itemStack, float h, float k, float l, double d, double e, double m, double n, double o, double p, double q, float r) {
        if(XR_RENDERER.isXrMode() && player == Minecraft.getInstance().player) {
            int index = 1;
            if (player.getMainHandItem().getItem() instanceof FishingRodItem) {
                index = 0;
            }

            Pose handPose = XrInput.handsActionSet.gripPoses[index].getMinecraftPose();
            Vec3 handPos = convert(handPose.getPos());
            Vec3 rodVec = convert(handPose.getOrientation().transform(new Vector3f(0, 0.4f, 0)));

            o = handPos.x + rodVec.x;
            p = handPos.y + rodVec.y;
            q = handPos.z + rodVec.z;
            r = 0.0F;
        }
    }
}
