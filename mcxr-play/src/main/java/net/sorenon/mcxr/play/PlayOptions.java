package net.sorenon.mcxr.play;

import com.electronwill.nightconfig.core.file.FileConfig;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import org.lwjgl.glfw.GLFW;

public class PlayOptions {
    private static FileConfig fileConfig;

    public static boolean xrUninitialized = false;
    public static boolean xrPaused = false;

    public static MoveDirectionPose walkDirection = MoveDirectionPose.LeftHand;
    public static MoveDirectionPose swimDirection = MoveDirectionPose.RightHand;
    public static MoveDirectionPose flyDirection = MoveDirectionPose.RightHand;

    public static boolean smoothTurning = false;
    public static float snapTurnAmount = 30f;
    public static float smoothTurnRate = 120f;
    public static boolean continuousSnapTurning = true;
    public static boolean snapTurnSound = true;
    public static float SnapTurnDelay = 0.25f;
    public static boolean immersiveControls = true;
    public static boolean fullMirror = false;
    public static boolean teleportEnabled=true;
    /**
     * The angle to rotate the player's in-game hand for a more comfortable experience
     * May be different for different controllers -> needs testing
     */
    public static float handPitchAdjust = 30;

    public static float SSAA = 1;

    public static void init() {
        fileConfig = FileConfig.of(FabricLoader.getInstance().getConfigDir().resolve(MCXRPlayClient.MODID + ".toml"));
    }

    public static void save() {
        fileConfig.set("disableMCXR", xrUninitialized);
        fileConfig.set("pauseMCXR", xrPaused);

        fileConfig.set("walkDirection", walkDirection);
        fileConfig.set("swimDirection", swimDirection);
        fileConfig.set("flyDirection", flyDirection);

        fileConfig.set("handPitchAdjust", handPitchAdjust);

        fileConfig.set("smoothTurning", smoothTurning);
        fileConfig.set("snapTurnAmount", snapTurnAmount);
        fileConfig.set("smoothTurnRate", smoothTurnRate);

        //====
        fileConfig.set("continuousSnapTurning", continuousSnapTurning);
        fileConfig.set("snapTurnSound", snapTurnSound);
        fileConfig.set("SnapTurnDelay", SnapTurnDelay);
        fileConfig.set("immersiveControls", immersiveControls);
        fileConfig.set("fullMirror",fullMirror);

        fileConfig.set("teleportEnabled", teleportEnabled);

        fileConfig.set("SSAA", SSAA);
        fileConfig.save();
    }

    public static void load() {
        fileConfig.load();
        xrUninitialized = fileConfig.getOrElse("disableMCXR", false);
        xrPaused = fileConfig.getOrElse("pauseMCXR", false);

        walkDirection = fileConfig.getEnumOrElse("walkDirection", MoveDirectionPose.LeftHand);
        swimDirection = fileConfig.getEnumOrElse("swimDirection", MoveDirectionPose.RightHand);
        flyDirection = fileConfig.getEnumOrElse("flyDirection", MoveDirectionPose.RightHand);

        handPitchAdjust = fileConfig.<Number>getOrElse("handPitchAdjust", 30f).floatValue();

        smoothTurning = fileConfig.getOrElse("smoothTurning", false);
        snapTurnAmount = fileConfig.<Number>getOrElse("snapTurnAmount", 22f).floatValue();
        smoothTurnRate = fileConfig.<Number>getOrElse("smoothTurnRate", 120f).floatValue();

        //====
        continuousSnapTurning=fileConfig.getOrElse("continuousSnapTurning",true);
        snapTurnSound=fileConfig.getOrElse("snapTurnSound",true);
        SnapTurnDelay=fileConfig.<Number>getOrElse("SnapTurnDelay",0.25f).floatValue();
        immersiveControls=fileConfig.getOrElse("immersiveControls",true);
        fullMirror=fileConfig.getOrElse("fullMirror",false);

        teleportEnabled = fileConfig.getOrElse("teleportEnabled", true);

        SSAA = fileConfig.<Number>getOrElse("SSAA", 1).floatValue();
    }
}
