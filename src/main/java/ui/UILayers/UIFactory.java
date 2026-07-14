package ui.UILayers;

import java.util.function.Consumer;

import game.engine.GameEngine;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class UIFactory {
    private BottomLayerCreator bottomLayerCreator;
    private TopLayerCreator topLayerCreator;
    private GameAreaCreator gameAreaCreator = new GameAreaCreator();
    private Canvas canvas; 
    private GameEngine engine;
    public UIFactory(Canvas canvas, GameEngine engine, BorderPane MainPane, BooleanProperty changeScene,
                     Consumer<Node> addOverlay, Consumer<Node> removeOverlay)
    {
        this.canvas = canvas;
        this.engine = engine;
        this.bottomLayerCreator = new BottomLayerCreator(engine, MainPane);
        this.topLayerCreator = new TopLayerCreator(changeScene, engine, addOverlay, removeOverlay);
    }

   public HBox create_TopLayer()
    {
        return this.topLayerCreator.create_top_layer(); 
    }

   public VBox create_BottomLayer()
    {
        return this.bottomLayerCreator.create_bottom_layer(this.engine);
    }

   public Pane create_GameArea()
    {
        return this.gameAreaCreator.create_game_area(this.canvas, this.engine);
    }


}
