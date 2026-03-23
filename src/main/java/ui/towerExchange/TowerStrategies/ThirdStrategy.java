package ui.towerExchange.TowerStrategies;

import game.animation.enemyAnimationen.LoadSystems;
import game.engine.GameEngine;
import javafx.event.ActionEvent;
import javafx.event.Event;
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
import game.sattack.SpecialAttack;
import game.sattack.SpecialAttackType;

public class ThirdStrategy implements TowerStrategy {

	@Override
	public HBox changeTower(GameEngine engine, BorderPane MainPane) {
    
        HBox towerMenu = new HBox(15); // 15 Pixel Abstand zwischen den Buttons
        towerMenu.setAlignment(Pos.CENTER_LEFT);
        towerMenu.setPadding(new Insets(0, 0, 0, 25)); // Etwas Abstand nach links zu den Münzen
        Image image = LoadSystems.loadImage("/images/static-images/Fireattack");
        ImageView iV = new ImageView(image);
        Image image2 = LoadSystems.loadImage("/images/static-images/Missle");
        ImageView iV2 = new ImageView(image2);
        Image image3 = LoadSystems.loadImage("/images/static-images/EarthQuake");
        ImageView iV3 = new ImageView(image3);
    
        Label attack1priceTag = new Label(String.valueOf(SpecialAttackType.FireAttack.get_Price()));
        attack1priceTag.getStyleClass().add("tower-price-label");
        Label attack2priceTag = new Label(String.valueOf(SpecialAttackType.BombAttack.get_Price()));
        attack2priceTag.getStyleClass().add("tower-price-label");
        Label attack3priceTag = new Label(String.valueOf(SpecialAttackType.ElectricWave.get_Price()));
        attack3priceTag.getStyleClass().add("tower-price-label");

        VBox attack1box = new VBox();
        attack1box.getStyleClass().add("tower-box");
        VBox attack2box = new VBox();
        attack2box.getStyleClass().add("tower-box");
        VBox attack3box = new VBox();
        attack3box.getStyleClass().add("tower-box");

        Button tower1 = new Button("", iV);
        tower1.getStyleClass().add("tower-border");
        tower1.setOnAction(e -> useSpecialAttack(e, SpecialAttackType.FireAttack, engine, MainPane));
        tower1.setPrefSize(80, 50);
        Button tower2 = new Button("", iV2);
        tower2.getStyleClass().add("tower-border");
        tower2.setOnAction(e -> useSpecialAttack(e, SpecialAttackType.BombAttack, engine, MainPane));
        tower2.setPrefSize(80, 50);
        Button tower3 = new Button("", iV3);
        tower3.getStyleClass().add("tower-border");
        tower3.setOnAction(e -> useSpecialAttack(e, SpecialAttackType.ElectricWave, engine, MainPane));
        tower3.setPrefSize(80, 50);
           
        attack1box.getChildren().addAll(tower1, attack1priceTag);
        attack1box.getStyleClass().add("tower-border");
        attack2box.getChildren().addAll(tower2, attack2priceTag);
        attack2box.getStyleClass().add("tower-border");
        attack3box.getChildren().addAll(tower3, attack3priceTag);
        attack3box.getStyleClass().add("tower-border");

        towerMenu.getChildren().addAll(attack1box, attack2box ,attack3box);

        return towerMenu;
	}

    public void useSpecialAttack(ActionEvent e, SpecialAttackType attackType, GameEngine engine, BorderPane MainPane)
    {
       Button sourceButton = (Button) e.getSource();
       Scene scene = sourceButton.getScene(); 

       scene.setCursor(Cursor.HAND);
        javafx.application.Platform.runLater(() -> {
            scene.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent mouseEvent) {
                    Point2D localPoint = MainPane.getCenter().sceneToLocal(mouseEvent.getSceneX(), mouseEvent.getSceneY()); 
                    Pane pane = (Pane) MainPane.getCenter();
                    if (pane.getBoundsInLocal().contains(localPoint))
                        {
                        Vector2 position = new Vector2(localPoint.getX(), localPoint.getY());
                        engine.handleSpecialAttack(position, attackType);
                        }
                    scene.removeEventFilter(MouseEvent.MOUSE_CLICKED, this);

                    scene.setCursor(Cursor.DEFAULT);
                    mouseEvent.consume();
				}
            });
        });

    }




}
