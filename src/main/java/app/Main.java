package app;

import game.engine.GameEngine;
import game.engine.GameLoop;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import ui.*;
public class Main extends Application {
    private GameLoop GL;

    @Override
    public void start(Stage stage) {

        GameEngine engine = new GameEngine();
        this.GL = new GameLoop(engine);
        GL.start();
		StackPane root = new StackPane();
			
		root.getChildren().add(engine.getCanvas());
		root.getChildren().add(UIFXSettings.createBuyMenu(engine));			

		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/css/UI.css").toExternalForm());
		UIFXSettings.initializeSettings(stage, scene);		
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
    
}

