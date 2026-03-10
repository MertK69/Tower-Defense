package ui.UILayers.BottomLayer;

import game.engine.GameEngine;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ui.towerExchange.TowerUIChanger;
import ui.towerExchange.TowerStrategies.FirstStrategy;

public class UpperLayerCreator{
    private TowerUIChanger TowerChanger;

    public UpperLayerCreator(TowerUIChanger TowerChanger)
    {
        this.TowerChanger = TowerChanger;
    }

    public HBox create_upper_layer(GameEngine engine, BorderPane MainPane)
    {
            HBox bottomMenu = new HBox(); 
            bottomMenu.setPadding(new Insets(10, 20, 10, 20)); // Abstand nach oben, rechts, unten, links
            bottomMenu.setAlignment(Pos.CENTER_LEFT);
            bottomMenu.getStyleClass().add("bottom-top-border");
            HBox towerMenu = TowerChanger.createTowerUI(engine, MainPane);
            VBox Test = new VBox(10);
            Label life = new Label("Lifes left: ");
            life.textProperty().bind(engine.get_waveProperty().asString());
            Label EnemiesLeft = new Label("Enemies left: ");
            life.getStyleClass().add("half-sized"); 
            EnemiesLeft.getStyleClass().add("half-sized");
            EnemiesLeft.textProperty().bind(engine.get_enemyProperty().asString());
            Test.setPadding(new Insets(10,10,10,10));
            Test.setAlignment(Pos.CENTER_LEFT);
            Test.getChildren().addAll(life, EnemiesLeft);
            Label coinText = new Label();
            coinText.getStyleClass().add("coin-field");
            coinText.textProperty().bind(engine.get_MoneyProperty().asString());
            bottomMenu.getChildren().addAll(coinText, Test,  towerMenu);
            return bottomMenu;
    }
}
