package ui.UILayers.BottomLayer;

import game.engine.GameEngine;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import ui.towerExchange.TowerUIChanger;
import ui.towerExchange.TowerStrategies.*;

public class LowerLayerCreator{
    private TowerUIChanger TowerChanger;
    public BooleanProperty MenuChange = new SimpleBooleanProperty(false);

    public LowerLayerCreator(TowerUIChanger TowerChanger)
    {
        this.TowerChanger = TowerChanger;
    }

    public HBox create_lower_layer(GameEngine engine)
    {
            HBox towerMenu = new HBox(10);
            towerMenu.setPadding(new Insets(10));
            towerMenu.setAlignment(Pos.CENTER_LEFT);
            towerMenu.setStyle("-fx-background-color: #6B4E31;");
            Button button = new Button();
            button.setPrefSize(120,65);
            button.setOnAction(e -> {
            TowerChanger.setStrategy(new FirstStrategy());
            MenuChange.set(!MenuChange.get());
            });
            Button button2 = new Button();
            button2.setPrefSize(120,65);
            button2.setOnAction(e -> {
            TowerChanger.setStrategy(new SecondStrategy());
            MenuChange.set(!MenuChange.get());
            });
            towerMenu.getChildren().addAll(button, button2);
            return towerMenu;
    }
}
