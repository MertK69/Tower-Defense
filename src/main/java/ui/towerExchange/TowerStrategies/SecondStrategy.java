package ui.towerExchange.TowerStrategies;

import game.animation.enemyAnimationen.LoadSystems;
import game.engine.GameEngine;
import game.tower.TowerType;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import ui.towerExchange.TowerStrategy;
import util.Vector2;

public class SecondStrategy implements TowerStrategy{

        @Override
        public HBox changeTower(GameEngine engine, BorderPane MainPane) {
                HBox towerMenu = new HBox(15); // 15 Pixel Abstand zwischen den Buttons
                towerMenu.setAlignment(Pos.CENTER_LEFT);
                towerMenu.setPadding(new Insets(0, 0, 0, 25)); // Etwas Abstand nach links zu den Münzen
                Image image = LoadSystems.loadImage("/images/static-images/rocket-launcher");
                ImageView iV = new ImageView(image);
                Image image2 = LoadSystems.loadImage("/images/static-images/rocket-launcher2");
                ImageView iV2 = new ImageView(image2);
                Image image3 = LoadSystems.loadImage("/images/static-images/rocket-launcher3");
                ImageView iV3 = new ImageView(image3);

                Label tower1priceTag = new Label(String.valueOf(TowerType.ROCKETLAUNCHERBASIC.price()));
                tower1priceTag.getStyleClass().add("tower-price-label");
                Label tower2priceTag = new Label(String.valueOf(TowerType.ROCKETLAUNCHERADVANCED.price()));
                tower2priceTag.getStyleClass().add("tower-price-label");
                Label tower3priceTag = new Label(String.valueOf(TowerType.ROCKETLAUNCHEREXPERT.price()));
                tower3priceTag.getStyleClass().add("tower-price-label");

                VBox tower1box = new VBox();
                tower1box.getStyleClass().add("tower-box");
                VBox tower2box = new VBox();
                tower2box.getStyleClass().add("tower-box");
                VBox tower3box = new VBox();
                tower3box.getStyleClass().add("tower-box");

                Button tower1 = new Button("", iV);
                tower1.setOnAction(e -> buyTower(e, TowerType.ROCKETLAUNCHERBASIC, engine, MainPane));
                tower1.getStyleClass().add("tower-border");
                tower1.setPrefSize(80, 50);
                Button tower2 = new Button("", iV2);
                tower2.setOnAction(e -> buyTower(e, TowerType.ROCKETLAUNCHERADVANCED, engine, MainPane));
                tower2.getStyleClass().add("tower-border");
                tower2.setPrefSize(80, 50);
                Button tower3 = new Button("", iV3);
                tower3.setOnAction(e -> buyTower(e, TowerType.ROCKETLAUNCHEREXPERT, engine, MainPane));
                tower3.getStyleClass().add("tower-border");
                tower3.setPrefSize(80, 50);

                tower1box.getChildren().addAll(tower1, tower1priceTag);
                tower1box.getStyleClass().add("tower-border");
                tower2box.getChildren().addAll(tower2, tower2priceTag);
                tower2box.getStyleClass().add("tower-border");
                tower3box.getChildren().addAll(tower3, tower3priceTag);
                tower3box.getStyleClass().add("tower-border");

                towerMenu.getChildren().addAll(tower1box, tower2box, tower3box);
            
                return towerMenu;
        }

        public void buyTower(ActionEvent e, TowerType type, GameEngine engine, BorderPane MainPane ) {
            Button sourceButton = (Button) e.getSource();
            Scene scene = sourceButton.getScene();

            scene.setCursor(Cursor.CROSSHAIR);

            javafx.application.Platform.runLater(() -> {
                scene.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        Point2D localPoint = MainPane.getCenter().sceneToLocal(mouseEvent.getSceneX(), mouseEvent.getSceneY());
                        Pane place = (Pane) MainPane.getCenter();
                        if (place.getBoundsInLocal().contains(localPoint))
                        {
                        Vector2 position = new Vector2(localPoint.getX(), localPoint.getY());
                        engine.handleBuyRequest(type, position);
                        }
                        scene.setCursor(Cursor.DEFAULT);
                        scene.removeEventFilter(MouseEvent.MOUSE_CLICKED, this);
                        mouseEvent.consume();
                    }
                });
            });
        }
}
