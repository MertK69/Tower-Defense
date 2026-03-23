package ui.UILayers.BottomLayer;

import game.engine.GameEngine;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
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
        HBox towerAndOptionsMenu = new HBox();
        towerAndOptionsMenu.setPadding(new Insets(10));
        towerAndOptionsMenu.setStyle("-fx-background-color: #6B4E31;");
        HBox towerMenu = new HBox(10);
        towerMenu.setPadding(new Insets(10));
        towerMenu.setAlignment(Pos.CENTER_LEFT);
        towerMenu.setStyle("-fx-background-color: #6B4E31;");
        HBox OptionsMenu = new HBox(10);
        OptionsMenu.setPadding(new Insets(10));
        OptionsMenu.setAlignment(Pos.CENTER_RIGHT);
        OptionsMenu.setStyle("-fx-background-color: #6B4E31;");
        Button DefaultTowerMenu= new Button("Default \n Tower Menu");
        DefaultTowerMenu.getStyleClass().add("bottom-menu-buttons");
        DefaultTowerMenu.setOnAction(e -> {
        TowerChanger.setStrategy(new FirstStrategy());
        MenuChange.set(!MenuChange.get());
        });
        Button AdvancedTowerMenu = new Button("Advanced \n Tower Menu");
        AdvancedTowerMenu.getStyleClass().add("bottom-menu-buttons");
        AdvancedTowerMenu.setOnAction(e -> {
        TowerChanger.setStrategy(new SecondStrategy());
        MenuChange.set(!MenuChange.get());
        });
        Button EffectsMenu = new Button("Special \n Attack");
        EffectsMenu.getStyleClass().add("bottom-menu-buttons");
        EffectsMenu.setOnAction(e -> {
        TowerChanger.setStrategy(new ThirdStrategy());
        MenuChange.set(!MenuChange.get());
        });

        Button showTowerRanges = new Button("Show Tower \n Ranges");
        showTowerRanges.getStyleClass().add("bottom-menu-buttons");
        showTowerRanges.setOnAction(e -> showTowerRanges(engine));

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        towerMenu.getChildren().addAll(DefaultTowerMenu, AdvancedTowerMenu, EffectsMenu);

        OptionsMenu.getChildren().add(showTowerRanges);

        towerAndOptionsMenu.getChildren().addAll(towerMenu, spacer, OptionsMenu);

        return towerAndOptionsMenu;
    }

    private void showTowerRanges(GameEngine engine)
    {
        engine.get_showTowerRangesProperty().setValue(!engine.get_showTowerRangesProperty().getValue()); 
    }
}
