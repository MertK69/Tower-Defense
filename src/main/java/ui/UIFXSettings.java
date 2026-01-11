package ui;

import game.engine.GameEngine;
import game.tower.Tower;
import game.tower.TowerType;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import util.Vector2;

public class UIFXSettings {

		public static void initializeSettings(Stage stage, Scene scene)
		{
				stage.setScene(scene);
				stage.setTitle("Tower Defense - Made by MertK69");
				stage.setWidth(1200);
				stage.setHeight(840);
				stage.setResizable(false);
		}

		public static StackPane createBuyMenu(GameEngine engine)
		{
		    Button button = new Button("Tower Weak");
			button.getStyleClass().add("tower-buy-button");
			button.setOnAction(e -> BuyTower(e, TowerType.BASIC, engine));
		    button.setPrefSize(175, 150);
		    StackPane pane = new StackPane(button);
			pane.setAlignment(button, Pos.TOP_LEFT);
			button.setTranslateX(32);
			button.setTranslateY(633);
		    return pane;
		}

		public static void BuyTower(ActionEvent e, TowerType type, GameEngine engine)
		{
				engine.handleBuyRequest(type);	
		}






}
