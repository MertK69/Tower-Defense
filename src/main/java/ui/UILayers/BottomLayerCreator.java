package ui.UILayers;

import game.engine.GameEngine;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import ui.UILayers.BottomLayer.LowerLayerCreator;
import ui.UILayers.BottomLayer.UpperLayerCreator;
import ui.towerExchange.TowerUIChanger;
import ui.towerExchange.TowerStrategies.FirstStrategy;

public class BottomLayerCreator{
    private VBox DualBottomLayer = new VBox();
    private TowerUIChanger TowerChanger = new TowerUIChanger();
    private LowerLayerCreator lowerLayerCreator = new LowerLayerCreator(TowerChanger);
    private UpperLayerCreator upperLayerCreator = new UpperLayerCreator(TowerChanger);

    public BottomLayerCreator(GameEngine engine, BorderPane MainPane)
    {
        lowerLayerCreator.MenuChange.addListener(obs -> refreshUI(engine, MainPane));
        this.TowerChanger.setStrategy(new FirstStrategy());
        refreshUI(engine, MainPane);
    }

    public VBox create_bottom_layer(GameEngine engine, BorderPane MainPane)
    {
        refreshUI( engine, MainPane);
        return DualBottomLayer;
    }

    public void refreshUI(GameEngine engine,BorderPane MainPane)
    {
        DualBottomLayer.getChildren().clear();
        DualBottomLayer.getChildren().add(upperLayerCreator.create_upper_layer(engine, MainPane)); 
        DualBottomLayer.getChildren().add(lowerLayerCreator.create_lower_layer(engine));
    }
}
