package net.sorenon.mcxr.play.input.actionsets;

import net.sorenon.mcxr.play.MCXRPlayClient;
import net.sorenon.mcxr.play.input.actions.*;
import oshi.util.tuples.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class VanillaGameplayActionSet extends ActionSet {

    public final BoolAction jump = new BoolAction("jump");
    public final BoolAction inventory = new BoolAction("inventory");
    public final BoolAction attack = new BoolAction("attack");
    public final BoolAction use = new BoolAction("use");
    public final BoolAction sprint = new BoolAction("sprint");
    public final BoolAction sneak = new BoolAction("sneak");
    public final BoolAction resetPos = new BoolAction("reset_pos");
    //public final BoolAction teleport = new BoolAction("teleport"); //TODO switch to Alyx-like bindings
    public final BoolAction quickmenu = new BoolAction("quickmenu");
    public final BoolAction chat = new BoolAction("chat");
    public final BoolAction stand = new BoolAction("stand");
    public final BoolAction menu = new BoolAction("menu");

    public final BoolAction teleport = new BoolAction("teleport");

    public final FloatAction turn = new FloatAction("turn");
    //TODO remove this with new input system
    public final FloatAction hotbar = new FloatAction("hotbar");
    public final Vec2fAction move = new Vec2fAction("move");

    public final BoolAction hotbarLeft = new BoolAction("hotbar_left");
    public final BoolAction hotbarRight = new BoolAction("hotbar_right");
    //TODO remove these with new input system
    public final BoolAction turnLeft = new BoolAction("turn_left");
    public final BoolAction turnRight = new BoolAction("turn_right");

    public final FloatAction sneakAnalog = new FloatAction("sneak_analog");
    public final FloatAction sprintAnalog = new FloatAction("sprint_analog");

    public final BoolAction swapHands = new BoolAction("swap_hands");
    public final HapticAction leftHaptic = new HapticAction("left_haptic");
    public final HapticAction rightHaptic = new HapticAction("right_haptic");


    // public final MultiHapticAction haptics = new MultiHapticAction("haptics", new String[]{"/user/hand/left", "/user/hand/right"});

    public boolean turnActivated = false;
    public boolean hotbarActivated;

    public boolean sneakAnalogOn = false;
    public boolean sprintAnalogOn = false;

    public final List<Action> actions = List.of(
            jump,
            inventory,
            attack,
            use,
            sprint,
            sneak,
            chat,
            menu,
            resetPos,
            turn,
            hotbar,
            move,
            quickmenu,
            stand,
            hotbarLeft,
            hotbarRight,
            turnLeft,
            turnRight,
            sneakAnalog,
            sprintAnalog,
            leftHaptic,
            rightHaptic,
            swapHands,
            teleport
    );

    public VanillaGameplayActionSet() {
        super("vanilla_gameplay", 0);
    }

    @Override
    public List<Action> actions() {
        return actions;
    }

    @Override
    public boolean shouldSync() {
        return !MCXRPlayClient.INSTANCE.MCXRGuiManager.isScreenOpen();
    }

    public void getDefaultBindings(HashMap<String, List<Pair<Action, String>>> map) {


        map.computeIfAbsent("/interaction_profiles/oculus/touch_controller", aLong -> new ArrayList<>()).addAll(
                List.of(
                        new Pair<>(use, "/user/hand/left/input/trigger/value"),
                        new Pair<>(attack, "/user/hand/right/input/trigger/value"),
                        new Pair<>(move, "/user/hand/left/input/thumbstick"),
                        new Pair<>(hotbarRight, "/user/hand/right/input/squeeze/value"),
                        new Pair<>(hotbarLeft, "/user/hand/left/input/squeeze/value"),
                        new Pair<>(turn, "/user/hand/right/input/thumbstick/x"),
                        new Pair<>(inventory, "/user/hand/left/input/y/click"),
                        new Pair<>(jump, "/user/hand/right/input/a/click"),
                        //new Pair<>(sprint, "/user/hand/left/input/thumbstick/click"),
                        //new Pair<>(sneak, "/user/hand/right/input/thumbstick/y"),
                        new Pair<>(resetPos, "/user/hand/right/input/thumbstick/click"),
                        new Pair<>(quickmenu, "/user/hand/right/input/b/click"),
                        new Pair<>(menu, "/user/hand/left/input/menu/click"),//does nothing on steamvr default
                        //new Pair<>(chat, "/user/hand/left/input/x/click"),
                        //new Pair<>(stand, "/user/hand/left/input/thumbstick/click"),
                        new Pair<>(stand, "/user/hand/right/input/thumbstick/click"),
                        new Pair<>(sneakAnalog, "/user/hand/right/input/thumbstick/y"),
                        new Pair<>(sprintAnalog, "/user/hand/right/input/thumbstick/y"),
                        new Pair<>(swapHands, "/user/hand/left/input/thumbstick/click"),
                        new Pair<>(teleport, "/user/hand/left/input/x/click")
                ));


        if (!MCXRPlayClient.OPEN_XR_STATE.instance.runtimeName.contains("Oculus")) {

            map.computeIfAbsent("/interaction_profiles/valve/index_controller", aLong -> new ArrayList<>()).addAll(
                    List.of(
                            new Pair<>(use, "/user/hand/left/input/trigger/value"),
                            new Pair<>(attack, "/user/hand/right/input/trigger/value"),
                            new Pair<>(move, "/user/hand/left/input/thumbstick"),
                            new Pair<>(hotbar, "/user/hand/right/input/thumbstick/y"),
                            //new Pair<>(hotbarRight, "/user/hand/right/input/squeeze/value"),
                            //new Pair<>(hotbarLeft, "/user/hand/left/input/squeeze/value"),
                            new Pair<>(turn, "/user/hand/right/input/thumbstick/x"),
                            new Pair<>(inventory,"/user/hand/right/input/b/click"),
                            new Pair<>(jump, "/user/hand/right/input/a/click"),
                            //new Pair<>(sprint, "/user/hand/left/input/thumbstick/click"),
                            //new Pair<>(sneak, "/user/hand/right/input/thumbstick/click"),
                            new Pair<>(sneakAnalog, "/user/hand/right/input/thumbstick/y"),
                            new Pair<>(sprintAnalog, "/user/hand/right/input/thumbstick/y"),
                            new Pair<>(stand, "/user/hand/right/input/thumbstick/click"),
                            new Pair<>(resetPos, "/user/hand/right/input/thumbstick/click"),
                            new Pair<>(quickmenu, "/user/hand/right/input/trackpad/force"),
                            //don't have access to index menu button
                            //new Pair<>(menu, "/user/hand/left/input/menu/click"),
                            //new Pair<>(stand, "/user/hand/left/input/thumbstick/click"),
                            new Pair<>(swapHands, "/user/hand/left/input/thumbstick/click"),
                            new Pair<>(teleport, "/user/hand/left/input/trackpad/force")
                    )
            );



            map.computeIfAbsent("/interaction_profiles/microsoft/motion_controller", aLong -> new ArrayList<>()).addAll(
                    List.of(
                            new Pair<>(use, "/user/hand/left/input/trigger/value"),
                            new Pair<>(attack, "/user/hand/right/input/trigger/value"),
                            new Pair<>(move, "/user/hand/left/input/thumbstick"),
                            new Pair<>(hotbarRight, "/user/hand/right/input/squeeze/click"),
                            new Pair<>(hotbarLeft, "/user/hand/left/input/squeeze/click"),
                            new Pair<>(turn, "/user/hand/right/input/thumbstick/x"),
                            new Pair<>(inventory, "/user/hand/left/input/trackpad/click"),
                            new Pair<>(jump, "/user/hand/right/input/trackpad/click"),
                            //new Pair<>(sprint, "/user/hand/left/input/thumbstick/click"),
                            //new Pair<>(sneak, "/user/hand/right/input/thumbstick"),
                            new Pair<>(sneakAnalog, "/user/hand/right/input/thumbstick/y"),
                            new Pair<>(sprintAnalog, "/user/hand/right/input/thumbstick/y"),
                            //new Pair<>(stand, "/user/hand/right/input/thumbstick"),
                            new Pair<>(resetPos, "/user/hand/right/input/thumbstick/click"),
                            new Pair<>(quickmenu, "/user/hand/right/input/menu/click"),
                            //new Pair<>(menu, "/user/hand/left/input/menu/click"),
                            //new Pair<>(chat, "/user/hand/left/input/menu/click"),
                            new Pair<>(stand, "/user/hand/right/input/thumbstick/click"),
                            new Pair<>(swapHands, "/user/hand/left/input/thumbstick/click"),
                            new Pair<>(teleport, "/user/hand/left/input/menu/click")
                    )
            );

            map.computeIfAbsent("/interaction_profiles/htc/vive_controller", aLong -> new ArrayList<>()).addAll(
                    List.of(
                            new Pair<>(use, "/user/hand/left/input/trigger/value"),
                            new Pair<>(attack, "/user/hand/right/input/trigger/value"),
                            new Pair<>(move, "/user/hand/left/input/trackpad"),
                            new Pair<>(hotbarRight, "/user/hand/right/input/squeeze/click"),
                            new Pair<>(hotbarLeft, "/user/hand/left/input/squeeze/click"),
                            new Pair<>(turn, "/user/hand/right/input/trackpad/x"),
                            new Pair<>(inventory, "/user/hand/left/input/trackpad/click"),
                            new Pair<>(jump, "/user/hand/right/input/trackpad/click"),
                            //new Pair<>(sprint, "/user/hand/left/input/thumbstick/click"),
                            //new Pair<>(sneak, "/user/hand/right/input/thumbstick"),
                            new Pair<>(sneakAnalog, "/user/hand/right/input/trackpad/y"),
                            new Pair<>(sprintAnalog, "/user/hand/right/input/trackpad/y"),
                            //new Pair<>(stand, "/user/hand/right/input/thumbstick"),
                            //new Pair<>(resetPos, "/user/hand/left/input/thumbstick/click"),
                            new Pair<>(quickmenu, "/user/hand/right/input/menu/click"),
                            //new Pair<>(menu, "/user/hand/left/input/menu/click"),
                            //new Pair<>(chat, "/user/hand/left/input/menu/click"),
                            //new Pair<>(stand, "/user/hand/left/input/thumbstick/click"),
                            new Pair<>(teleport, "/user/hand/left/input/menu/click")
                            //out of buttons to enable stand and reset_pos
                    )
            );

        }

        if (MCXRPlayClient.OPEN_XR_STATE.instance.handle.getCapabilities().XR_EXT_hp_mixed_reality_controller) {
            map.computeIfAbsent("/interaction_profiles/hp/mixed_reality_controller", aLong -> new ArrayList<>()).addAll(
                    List.of(
                            new Pair<>(use, "/user/hand/left/input/trigger/value"),
                            new Pair<>(attack, "/user/hand/right/input/trigger/value"),
                            new Pair<>(move, "/user/hand/left/input/thumbstick"),
//                        new Pair<>(hotbarRight, "/user/hand/right/input/squeeze/value"),
//                        new Pair<>(hotbarLeft, "/user/hand/left/input/squeeze/value"),
                        new Pair<>(hotbar, "/user/hand/right/input/thumbstick/y"),
                        new Pair<>(turn, "/user/hand/right/input/thumbstick/x"),
                        new Pair<>(inventory, "/user/hand/left/input/y/click"),
                        new Pair<>(jump, "/user/hand/right/input/a/click"),
                        //new Pair<>(sprint, "/user/hand/left/input/thumbstick/click"),
                        //new Pair<>(sneak, "/user/hand/right/input/thumbstick"),
                        new Pair<>(sneakAnalog, "/user/hand/right/input/thumbstick/y"),
                        new Pair<>(sprintAnalog, "/user/hand/right/input/thumbstick/y"),
                        //new Pair<>(stand, "/user/hand/right/input/thumbstick"),
                        new Pair<>(resetPos, "/user/hand/right/input/thumbstick/click"),
                        new Pair<>(quickmenu, "/user/hand/right/input/b/click"),
                        new Pair<>(menu, "/user/hand/left/input/menu/click"),
                        //new Pair<>(chat, "/user/hand/left/input/x/click"),
                        new Pair<>(stand, "/user/hand/right/input/thumbstick/click"),
                        new Pair<>(swapHands, "/user/hand/left/input/thumbstick/click"),
                        new Pair<>(teleport, "/user/hand/left/input/x/click")
                    )
            );
        }

        if (MCXRPlayClient.OPEN_XR_STATE.instance.handle.getCapabilities().XR_HTC_vive_cosmos_controller_interaction) {
            map.computeIfAbsent("/interaction_profiles/htc/vive_cosmos_controller", aLong -> new ArrayList<>()).addAll(
                    List.of(
                        new Pair<>(use, "/user/hand/left/input/trigger/value"),
                        new Pair<>(attack, "/user/hand/right/input/trigger/value"),
                        new Pair<>(move, "/user/hand/left/input/thumbstick"),
                        new Pair<>(hotbarRight, "/user/hand/right/input/squeeze/click"),
                        new Pair<>(hotbarLeft, "/user/hand/left/input/squeeze/click"),
                        new Pair<>(turn, "/user/hand/right/input/thumbstick/x"),
                        new Pair<>(inventory, "/user/hand/left/input/y/click"),
                        new Pair<>(jump, "/user/hand/right/input/a/click"),
                        //new Pair<>(sprint, "/user/hand/left/input/thumbstick/click"),
                        //new Pair<>(sneak, "/user/hand/right/input/thumbstick"),
                        new Pair<>(sneakAnalog, "/user/hand/right/input/thumbstick/y"),
                        new Pair<>(sprintAnalog, "/user/hand/right/input/thumbstick/y"),
                        //new Pair<>(stand, "/user/hand/right/input/thumbstick"),
                        new Pair<>(resetPos, "/user/hand/right/input/thumbstick/click"),
                        new Pair<>(quickmenu, "/user/hand/right/input/b/click"),
                        new Pair<>(menu, "/user/hand/left/input/menu/click"),
                        //new Pair<>(chat, "/user/hand/left/input/x/click"),
                        new Pair<>(stand, "/user/hand/right/input/thumbstick/click"),
                        new Pair<>(swapHands, "/user/hand/left/input/thumbstick/click"),
                        new Pair<>(teleport, "/user/hand/left/input/x/click")
                    )
            );
        }
    }
}
