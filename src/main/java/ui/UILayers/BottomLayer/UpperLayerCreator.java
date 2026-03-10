package ui.UILayers.BottomLayer;

import game.animation.enemyAnimationen.LoadSystems;
import game.engine.GameEngine;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ui.towerExchange.TowerUIChanger;

public class UpperLayerCreator{
    private TowerUIChanger TowerChanger;
    private LoadSystems Loader;

    public UpperLayerCreator(TowerUIChanger TowerChanger)
    {
        this.TowerChanger = TowerChanger;
    }

    public HBox create_upper_layer(GameEngine engine, BorderPane MainPane)
    {
            HBox bottomMenu = new HBox(); 
            bottomMenu.setPadding(new Insets(12, 25, 12, 25)); // Abstand nach oben, rechts, unten, links
            bottomMenu.setAlignment(Pos.CENTER_LEFT);
            bottomMenu.getStyleClass().add("bottom-top-border");

            HBox towerMenu = TowerChanger.createTowerUI(engine, MainPane);

            Label waveNumber = new Label();
            Image WavePicture = Loader.loadImage("/images/static-images/WavePicture");
            ImageView WaveView = new ImageView(WavePicture);
            waveNumber.setGraphic(WaveView);
            waveNumber.textProperty().bind(engine.get_waveProperty().asString());
            waveNumber.getStyleClass().add("half-sized"); 

            Label EnemiesLeft = new Label();
            Image Soldierimage = Loader.loadImage("/images/static-images/SoldierPicture");
            ImageView SoldierView = new ImageView(Soldierimage);
            EnemiesLeft.setGraphic(SoldierView);
            EnemiesLeft.textProperty().bind(engine.get_enemyProperty().asString());
            EnemiesLeft.getStyleClass().add("half-sized");

            VBox waveAndSoliderMenu = new VBox(10);
            waveAndSoliderMenu.setPadding(new Insets(10,10,10,10));
            waveAndSoliderMenu.setAlignment(Pos.CENTER_LEFT);
            waveAndSoliderMenu.getChildren().addAll(waveNumber, EnemiesLeft);

            Label coinText = new Label();
            Image CoinImage = Loader.loadImage("/images/static-images/coins");
            ImageView iV = new ImageView(CoinImage);
            coinText.getStyleClass().add("coin-field");
            coinText.setGraphic(iV);
            coinText.textProperty().bind(engine.get_MoneyProperty().asString());

            bottomMenu.getChildren().addAll(coinText, waveAndSoliderMenu,  towerMenu);
            return bottomMenu;
    }
}
