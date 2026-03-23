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

public class FirstStrategy implements TowerStrategy{

        @Override
        public HBox changeTower(GameEngine engine, BorderPane MainPane) {
                HBox towerMenu = new HBox(15); // 15 Pixel Abstand zwischen den Buttons
                towerMenu.setAlignment(Pos.CENTER_LEFT);
                towerMenu.setPadding(new Insets(0, 0, 0, 25)); 
                Image image = LoadSystems.loadImage("/images/static-images/lvl1-turret");
                ImageView iV = new ImageView(image);
                Image image2 = LoadSystems.loadImage("/images/static-images/lvl2-turret");
                ImageView iV2 = new ImageView(image2);
                Image image3 = LoadSystems.loadImage("/images/static-images/lvl3-turret");
                ImageView iV3 = new ImageView(image3);
                Image image4 = LoadSystems.loadImage("/images/static-images/lvl4-turret");
                ImageView iV4 = new ImageView(image4);
                Image image5 = LoadSystems.loadImage("/images/static-images/lvl5-turret");
                ImageView iV5 = new ImageView(image5);
                Image image6 = LoadSystems.loadImage("/images/static-images/lvl6-turret");
                ImageView iV6 = new ImageView(image6);

                Label tower1PriceTag = new Label();
                tower1PriceTag.setText(String.valueOf(TowerType.BASIC.price()));
                tower1PriceTag.getStyleClass().add("tower-price-label");
                Label tower2PriceTag = new Label();
                tower2PriceTag.setText(String.valueOf(TowerType.ADVANCED.price()));
                tower2PriceTag.getStyleClass().add("tower-price-label");
                Label tower3PriceTag = new Label();
                tower3PriceTag.setText(String.valueOf(TowerType.EXPERT.price()));
                tower3PriceTag.getStyleClass().add("tower-price-label");
                Label tower4PriceTag = new Label();
                tower4PriceTag.setText(String.valueOf(TowerType.RAYBASIC.price()));
                tower4PriceTag.getStyleClass().add("tower-price-label");
                Label tower5PriceTag = new Label();
                tower5PriceTag.setText(String.valueOf(TowerType.RAYADVANCED.price()));
                tower5PriceTag.getStyleClass().add("tower-price-label");
                Label tower6PriceTag = new Label();
                tower6PriceTag.setText(String.valueOf(TowerType.RAYEXPERT.price()));
                tower6PriceTag.getStyleClass().add("tower-price-label");


                VBox tower1box = new VBox();
                tower1box.getStyleClass().add("tower-box");
                VBox tower2box = new VBox();
                tower2box.getStyleClass().add("tower-box");
                VBox tower3box = new VBox();
                tower3box.getStyleClass().add("tower-box");
                VBox tower4box = new VBox();
                tower4box.getStyleClass().add("tower-box");
                VBox tower5box = new VBox();
                tower5box.getStyleClass().add("tower-box");
                VBox tower6box = new VBox();
                tower6box.getStyleClass().add("tower-box");

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
                Button tower4 = new Button("", iV4);
                tower4.setOnAction(e -> buyTower(e, TowerType.RAYBASIC, engine, MainPane));
                tower4.getStyleClass().add("tower-border");
                tower4.setPrefSize(80, 50);
                Button tower5 = new Button("", iV5);
                tower5.setOnAction(e -> buyTower(e, TowerType.RAYADVANCED, engine, MainPane));
                tower5.getStyleClass().add("tower-border");
                tower5.setPrefSize(80, 50);
                Button tower6 = new Button("", iV6);
                tower6.setOnAction(e -> buyTower(e, TowerType.RAYEXPERT, engine, MainPane));
                tower6.getStyleClass().add("tower-border");
                tower6.setPrefSize(80, 50);

                tower1box.getChildren().addAll(tower1, tower1PriceTag);
                tower1box.getStyleClass().add("tower-border");
                tower2box.getChildren().addAll(tower2, tower2PriceTag);
                tower2box.getStyleClass().add("tower-border");
                tower3box.getChildren().addAll(tower3, tower3PriceTag);
                tower3box.getStyleClass().add("tower-border");
                tower4box.getChildren().addAll(tower4, tower4PriceTag);
                tower4box.getStyleClass().add("tower-border");
                tower5box.getChildren().addAll(tower5, tower5PriceTag);
                tower5box.getStyleClass().add("tower-border");
                tower6box.getChildren().addAll(tower6, tower6PriceTag);
                tower6box.getStyleClass().add("tower-border");


                towerMenu.getChildren().addAll(tower1box, tower2box, tower3box, tower4box, tower5box, tower6box);
            
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
                        Pane place = (Pane) MainPane.getCenter();
                        Point2D localPoint = MainPane.getCenter().sceneToLocal(mouseEvent.getSceneX(), mouseEvent.getSceneY());
                        if ( place.getBoundsInLocal().contains(localPoint))
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
