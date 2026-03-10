package ui.UILayers;

import game.engine.GameEngine;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class UIFactory {
    private BottomLayerCreator bottomLayerCreator;
    private TopLayerCreator topLayerCreator = new TopLayerCreator();
    private GameAreaCreator gameAreaCreator = new GameAreaCreator();
    private Canvas canvas; 
    private GameEngine engine;
    public UIFactory(Canvas canvas, GameEngine engine, BorderPane MainPane)
    {
        this.canvas = canvas;
        this.engine = engine;
        this.bottomLayerCreator = new BottomLayerCreator(engine, MainPane);
    }

   public HBox create_TopLayer()
    {
        return this.topLayerCreator.create_top_layer(); 
    }

   public VBox create_BottomLayer(BorderPane MainPane)
    {
        return this.bottomLayerCreator.create_bottom_layer(this.engine, MainPane);
    }

   public Pane create_GameArea()
    {
        return this.gameAreaCreator.create_game_area(this.canvas);
    }


}
