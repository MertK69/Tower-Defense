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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import ui.towerExchange.TowerStrategy;
import util.Vector2;

public class FirstStrategy implements TowerStrategy{
    LoadSystems Loader = new LoadSystems();

        @Override
        public HBox changeTower(GameEngine engine, BorderPane MainPane) {
                HBox towerMenu = new HBox(15); // 15 Pixel Abstand zwischen den Buttons
                towerMenu.setAlignment(Pos.CENTER_LEFT);
                towerMenu.setPadding(new Insets(0, 0, 0, 50)); // Etwas Abstand nach links zu den Münzen
                Image image = Loader.loadImage("/images/static-images/lvl1-turret");
                ImageView iV = new ImageView(image);
                Image image2 = Loader.loadImage("/images/static-images/lvl2-turret");
                ImageView iV2 = new ImageView(image2);
                Image image3 = Loader.loadImage("/images/static-images/lvl3-turret");
                ImageView iV3 = new ImageView(image3);
                Button tower1 = new Button("", iV);
                tower1.setOnAction(e -> buyTower(e, TowerType.BASIC, engine, MainPane));
                tower1.getStyleClass().add("tower-border");
                tower1.setPrefSize(80, 50);
                Button tower2 = new Button("", iV2);
                tower2.setOnAction(e -> buyTower(e, TowerType.ADVANCED, engine, MainPane));
                tower2.getStyleClass().add("tower-border");
                tower2.setPrefSize(80, 50);
                Button tower3 = new Button("", iV3);
                tower3.setOnAction(e -> buyTower(e, TowerType.EXPERT, engine, MainPane));
                tower3.getStyleClass().add("tower-border");
                tower3.setPrefSize(80, 50);

                towerMenu.getChildren().addAll(tower1, tower2, tower3);
            
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
                        Vector2 position = new Vector2(localPoint.getX(), localPoint.getY());
                        engine.handleBuyRequest(type, position);

                        scene.setCursor(Cursor.DEFAULT);
                        scene.removeEventFilter(MouseEvent.MOUSE_CLICKED, this);
                        mouseEvent.consume();
                    }
                });
            });
        }
}
