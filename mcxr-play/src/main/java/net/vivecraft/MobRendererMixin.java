package net.vivecraft;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import net.sorenon.mcxr.core.Pose;
import net.sorenon.mcxr.play.input.XrInput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static net.sorenon.mcxr.core.JOMLUtil.convert;

@Mixin(MobRenderer.class)
public class MobRendererMixin {

    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;getRopeHoldPosition(F)Lnet/minecraft/world/phys/Vec3;"), method = "renderLeash")
    public Vec3 leash(Entity instance, float f) {
        if (instance == Minecraft.getInstance().player) {
            Pose handPose = XrInput.handsActionSet.gripPoses[1].getMinecraftPose();
            return convert(handPose.getPos());
        }
        return instance.getRopeHoldPosition(f);
    }

}
