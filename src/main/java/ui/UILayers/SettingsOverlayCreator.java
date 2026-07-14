package ui.UILayers;

import game.engine.GameEngine;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * Builds the in-game audio settings overlay (TT-5). Mirrors {@link LoseScreenCreator}: a
 * semi-transparent {@link StackPane} overlay containing a centered {@link VBox} with a Music
 * slider, an SFX slider, and a "Back" button.
 *
 * <p>Slider changes propagate to the {@link GameEngine} audio control surface in real time
 * (AC-2 / AC-3 / Decision 6). The "Back" button runs the supplied {@code onBack} callback, which
 * the caller uses to remove the overlay and resume the game.</p>
 */
public class SettingsOverlayCreator {

    public StackPane create_settings_overlay(GameEngine engine, Runnable onBack) {
        StackPane overlay = new StackPane();
        overlay.getStyleClass().add("settings-overlay");

        Label title = new Label("Audio Settings");
        title.getStyleClass().add("settings-title");

        Label musicLabel = new Label("Music");
        musicLabel.getStyleClass().add("settings-label");
        Slider musicSlider = new Slider(0, 100, engine.getMusicVolume() * 100);
        musicSlider.getStyleClass().add("slider-style");
        musicSlider.valueProperty().addListener((obs, oldVal, newVal) ->
                engine.setMusicVolume(newVal.doubleValue() / 100.0));

        Label sfxLabel = new Label("SFX");
        sfxLabel.getStyleClass().add("settings-label");
        Slider sfxSlider = new Slider(0, 100, engine.getSfxVolume() * 100);
        sfxSlider.getStyleClass().add("slider-style");
        sfxSlider.valueProperty().addListener((obs, oldVal, newVal) ->
                engine.setSfxVolume(newVal.doubleValue() / 100.0));

        Button backButton = new Button("Back");
        backButton.getStyleClass().add("settings-button");
        backButton.setFocusTraversable(false);
        backButton.setOnAction(e -> onBack.run());

        VBox box = new VBox(15);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(30));
        box.getStyleClass().add("settings-box");
        box.getChildren().addAll(title, musicLabel, musicSlider, sfxLabel, sfxSlider, backButton);

        overlay.getChildren().add(box);
        StackPane.setAlignment(box, Pos.CENTER);

        return overlay;
    }
}
