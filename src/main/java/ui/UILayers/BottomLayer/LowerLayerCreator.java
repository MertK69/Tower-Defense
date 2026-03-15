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
            Button DefaultTowerMenu= new Button("Default \n Tower Menu");
            DefaultTowerMenu.getStyleClass().add("bottom-menu-buttons");
            DefaultTowerMenu.setPrefSize(120,65);
            DefaultTowerMenu.setOnAction(e -> {
            TowerChanger.setStrategy(new FirstStrategy());
            MenuChange.set(!MenuChange.get());
            });
            Button AdvancedTowerMenu = new Button("Advanced \n Tower Menu");
            AdvancedTowerMenu.getStyleClass().add("bottom-menu-buttons");
            AdvancedTowerMenu.setPrefSize(120,65);
            AdvancedTowerMenu.setOnAction(e -> {
            TowerChanger.setStrategy(new SecondStrategy());
            MenuChange.set(!MenuChange.get());
            });
            Button EffectsMenu = new Button("Special \n Attack");
            EffectsMenu.getStyleClass().add("bottom-menu-buttons");
            EffectsMenu.setPrefSize(120,65);
            EffectsMenu.setOnAction(e -> {
            TowerChanger.setStrategy(new ThirdStrategy());
            MenuChange.set(!MenuChange.get());
            });

            towerMenu.getChildren().addAll(DefaultTowerMenu, AdvancedTowerMenu, EffectsMenu);
            return towerMenu;
    }
}
