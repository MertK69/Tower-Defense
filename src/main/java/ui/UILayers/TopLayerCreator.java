package ui.UILayers;

import java.util.function.Consumer;

import game.engine.GameEngine;
import javafx.beans.property.BooleanProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class TopLayerCreator{
    private BooleanProperty property;
    private final GameEngine engine;
    private final Consumer<Node> addOverlay;
    private final Consumer<Node> removeOverlay;

    // Held reference to the currently-open settings overlay; also acts as the
    // double-open guard (TT-5 finding C-L1): a non-null value means the overlay is open.
    private StackPane settingsOverlay = null;

    public TopLayerCreator(BooleanProperty property, GameEngine engine,
                           Consumer<Node> addOverlay, Consumer<Node> removeOverlay)
    {
        this.property = property;
        this.engine = engine;
        this.addOverlay = addOverlay;
        this.removeOverlay = removeOverlay;
    }

    public HBox create_top_layer()
    {
            HBox TopLayer = new HBox();
            TopLayer.setAlignment(Pos.CENTER_LEFT);
            TopLayer.setPadding(new Insets(1));
            TopLayer.getStyleClass().add("top-bottom-border");
            Button GameButton = new Button("GameSettings");
            GameButton.setPrefSize(120,0.6);
            GameButton.setOnAction(e -> openSettings());
            Button OptionsButton = new Button("Options");
            OptionsButton.setPrefSize(120,0.6);
            Button ExitButton = new Button("Exit Game");
            ExitButton.setOnAction(e -> jumpToMenu());
            ExitButton.setPrefSize(120,0.6);

            GameButton.setFocusTraversable(false);
            OptionsButton.setFocusTraversable(false);
            ExitButton.setFocusTraversable(false);
        
            TopLayer.getChildren().addAll(GameButton, OptionsButton, ExitButton);
            return TopLayer;
    }

    /**
     * Opens the audio settings overlay and pauses the game. Guards against stacking a
     * second overlay if one is already open (C-L1).
     */
    public void openSettings()
    {
        if (this.settingsOverlay != null) return; // already open — do not stack duplicates
        if (this.engine == null || this.addOverlay == null) return;

        this.engine.setPaused(true);
        StackPane overlay = new SettingsOverlayCreator().create_settings_overlay(this.engine, this::closeSettings);
        this.settingsOverlay = overlay;
        this.addOverlay.accept(overlay);
    }

    /** Closes the overlay (if open) and resumes the game. */
    public void closeSettings()
    {
        if (this.settingsOverlay == null) return;
        if (this.removeOverlay != null) this.removeOverlay.accept(this.settingsOverlay);
        this.settingsOverlay = null;
        if (this.engine != null) this.engine.setPaused(false);
    }

    public void jumpToMenu()
    {
        this.property.setValue(false);
    }
}
