package net.sorenon.mcxr.play;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Option;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.sorenon.mcxr.play.openxr.OpenXRInstance;
import net.sorenon.mcxr.play.openxr.OpenXRState;
import net.sorenon.mcxr.play.openxr.OpenXRSystem;
import org.apache.commons.lang3.text.WordUtils;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.openxr.XR10;

import java.util.List;

public class MCXROptionsScreen extends Screen {

    @Nullable
    private final Screen previous;

    private Button reloadButton;

    public MCXROptionsScreen(@Nullable Screen previous) {
        super(new TranslatableComponent("mcxr.options.title"));
        this.previous = previous;
    }

    @Override
    protected void init() {
        PlayOptions.load();
        this.reloadButton = this.addRenderableWidget(new Button(
                this.width / 2 - 155,
                this.height / 6 - 12 - 4 + 24,
                150,
                20,
                new TranslatableComponent("mcxr.menu.reload"),
                button -> MCXRPlayClient.OPEN_XR_STATE.tryInitialize()));
        if (PlayOptions.xrUninitialized) {
            reloadButton.active = false;
        }

        this.addRenderableWidget(new Button(
                this.width / 2 + 5,
                this.height / 6 - 12 - 4 + 24,
                150,
                20,
                PlayOptions.xrUninitialized ? new TranslatableComponent("mcxr.options.initialize") : new TranslatableComponent("mcxr.options.uninitialize"),
                button -> {
                    PlayOptions.xrUninitialized = !PlayOptions.xrUninitialized;
                    PlayOptions.save();
                    reloadButton.active = !PlayOptions.xrUninitialized;
                    if (!PlayOptions.xrUninitialized) {
                        MCXRPlayClient.OPEN_XR_STATE.tryInitialize();
                    }
                    button.setMessage(PlayOptions.xrUninitialized ? new TranslatableComponent("mcxr.options.initialize") : new TranslatableComponent("mcxr.options.uninitialize"));
                }));

        this.addRenderableWidget(new Button(
                this.width / 2 - 100,
                this.height / 6 - 12 - 4,
                200,
                20,
                PlayOptions.xrPaused ? new TranslatableComponent("mcxr.options.unpause") : new TranslatableComponent("mcxr.options.pause"),
                button -> {
                    PlayOptions.xrPaused = !PlayOptions.xrPaused;
                    PlayOptions.save();
                    button.setMessage(PlayOptions.xrPaused ?new TranslatableComponent("mcxr.options.unpause") : new TranslatableComponent("mcxr.options.pause"));
                }));

        this.addRenderableWidget(new Button(
                this.width / 2 - 155,
                this.height / 6 + 54 + 12,
                150,
                20,
                new TranslatableComponent("mcxr.options.walk_direction", PlayOptions.walkDirection.toComponent()),
                button -> {
                    PlayOptions.walkDirection = PlayOptions.walkDirection.iterate();
                    PlayOptions.save();
                    button.setMessage(new TranslatableComponent("mcxr.options.walk_direction", PlayOptions.walkDirection.toComponent()));
                }));
        this.addRenderableWidget(new Button(
                this.width / 2 - 155,
                this.height / 6 + 54 + 24 + 12,
                150,
                20,
                new TranslatableComponent("mcxr.options.swim_direction", PlayOptions.swimDirection.toComponent()),
                button -> {
                    PlayOptions.swimDirection = PlayOptions.swimDirection.iterate();
                    PlayOptions.save();
                    button.setMessage(new TranslatableComponent("mcxr.options.swim_direction", PlayOptions.swimDirection.toComponent()));
                }));
        this.addRenderableWidget(new Button(
                this.width / 2 - 155,
                this.height / 6 + 54 + 24 * 2 + 12,
                150,
                20,
                new TranslatableComponent("mcxr.options.fly_direction", PlayOptions.flyDirection.toComponent()),
                button -> {
                    PlayOptions.flyDirection = PlayOptions.flyDirection.iterate();
                    PlayOptions.save();
                    button.setMessage(new TranslatableComponent("mcxr.options.fly_direction", PlayOptions.flyDirection.toComponent()));
                }));

        this.addRenderableWidget(new Button(
                this.width / 2 - 155,
                this.height / 6 + 54 + 24 * 3 + 12,
                150,
                20,
                MCXRPlayClient.heightAdjustStand ? new TranslatableComponent("mcxr.options.height_vanilla") : new TranslatableComponent("mcxr.options.height_real"),
                button -> {
                    MCXRPlayClient.heightAdjustStand = !MCXRPlayClient.heightAdjustStand;
                    button.setMessage(MCXRPlayClient.heightAdjustStand ? new TranslatableComponent("mcxr.options.height_vanilla") : new TranslatableComponent("mcxr.options.height_real"));
                }));

        assert this.minecraft != null;
        this.addRenderableWidget(Option.MAIN_HAND.createButton(this.minecraft.options, this.width / 2 - 155 + 160, this.height / 6 + 54 + 12, 150));

        this.addRenderableWidget(new Button(
                this.width / 2 - 155 + 160,
                this.height / 6 + 54 + 24 + 12,
                150,
                20,
                PlayOptions.smoothTurning ? new TranslatableComponent("mcxr.options.smooth_turning") : new TranslatableComponent("mcxr.options.snap_turning"),
                button -> {
                    PlayOptions.smoothTurning = !PlayOptions.smoothTurning;
                    PlayOptions.save();
                    button.setMessage(PlayOptions.smoothTurning ? new TranslatableComponent("mcxr.options.smooth_turning") : new TranslatableComponent("mcxr.options.snap_turning"));
                }));

        this.addRenderableWidget(new Button(
                this.width / 2 - 155 + 160,
                this.height / 6 + 54 + 24 * 2 + 12,
                150,
                20,
                PlayOptions.immersiveControls ? new TranslatableComponent("mcxr.options.immersive_on") : new TranslatableComponent("mcxr.options.immersive_off"),
                button -> {
                    PlayOptions.immersiveControls = !PlayOptions.immersiveControls;
                    PlayOptions.save();
                    button.setMessage(PlayOptions.immersiveControls ? new TranslatableComponent("mcxr.options.immersive_on") : new TranslatableComponent("mcxr.options.immersive_off"));
                }));

        this.addRenderableWidget(new Button(
                this.width / 2 - 155 + 160,
                this.height / 6 + 54 + 24 * 3 + 12,
                150,
                20,
                PlayOptions.teleportEnabled ? new TranslatableComponent("mcxr.options.teleportEnabled") : new TranslatableComponent("mcxr.options.teleportDisabled"),
                button -> {
                    PlayOptions.teleportEnabled = !PlayOptions.teleportEnabled;
                    PlayOptions.save();
                    button.setMessage(PlayOptions.teleportEnabled ? new TranslatableComponent("mcxr.options.teleportEnabled") : new TranslatableComponent("mcxr.options.teleportDisabled"));
                }));

        this.addRenderableWidget(new Button(this.width / 2 - 100, this.height / 6 + 168, 200, 20, CommonComponents.GUI_DONE, button -> this.minecraft.setScreen(this.previous)));
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float delta) {
        this.renderBackground(poseStack);
        super.render(poseStack, mouseX, mouseY, delta);

        drawCenteredString(poseStack, this.font, this.title, this.width / 2, 13, 16777215);

        int y = this.height / 6 - 4 + 24;
        int x = this.width / 2 - 155;

        MCXROptionsScreen.renderStatus(this, this.font, poseStack, mouseX, mouseY, x, y, 0, 60);
    }

    public static void renderStatus(Screen screen,
                                    Font font,
                                    PoseStack poseStack,
                                    int mouseX,
                                    int mouseY,
                                    int x,
                                    int y,
                                    int fade,
                                    int wrapLength) {
        if (PlayOptions.xrUninitialized) {
            GuiComponent.drawString(poseStack, font, "MCXR Disabled", x + 1, y + 12, 16777215 | fade);
            return;
        }

        OpenXRState OPEN_XR = MCXRPlayClient.OPEN_XR_STATE;

        if (OPEN_XR.instance != null) {
            OpenXRInstance instance = OPEN_XR.instance;
            GuiComponent.drawString(poseStack, font, instance.runtimeName + " " + instance.runtimeVersionString, x + 1, y + 12, 16777215 | fade);
            y += 12;
        }

        if (OPEN_XR.session != null) {
            OpenXRSystem system = OPEN_XR.session.system;
            for (String line : wordWrap(system.systemName, wrapLength)) {
                GuiComponent.drawString(poseStack, font, line, x + 1, y + 12, 16777215 | fade);
                y += 12;
            }
            GuiComponent.drawString(poseStack, font, I18n.get("openxr.form_factor." + system.formFactor), x + 1, y + 12, 16777215 | fade);
        } else {
            GuiComponent.drawString(poseStack, font, I18n.get("mcxr.menu.session_not_created"), x + 1, y + 12, 16777215 | fade);
            y += 12;
            if (OPEN_XR.createException != null) {
                String message = OPEN_XR.createException.getMessage();
                if (OPEN_XR.createException.result == XR10.XR_ERROR_FORM_FACTOR_UNAVAILABLE) {
                    message = I18n.get("mcxr.error.form_factor_unavailable");
                }
                for (String line : wordWrap(message, wrapLength)) {
                    GuiComponent.drawString(poseStack, Minecraft.getInstance().font, line, x + 1, y + 12, 16733525 | fade);
                    y += 12;
                }
                if (mouseX > x && mouseY < y + 10 && mouseY > screen.height / 4 + 48 + 12 + 10) {
                    screen.renderComponentTooltip(poseStack, wordWrapText(message, 40), mouseX + 14, mouseY);
                }
            }
        }
    }

    private static List<String> wordWrap(String string, int wrapLength) {
        return WordUtils.wrap(string, wrapLength, null, true).lines().toList();
    }

    private static List<Component> wordWrapText(String string, int wrapLength) {
        return WordUtils.wrap(string, wrapLength, null, true).lines().map(s -> (Component) (new TranslatableComponent(s))).toList();
    }
}
