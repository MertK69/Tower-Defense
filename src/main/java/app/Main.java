package app;

import game.engine.GameEngine;
import game.engine.GameLoop;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
    private GameLoop GL;

    @Override
    public void start(Stage stage) {

        GameEngine engine = new GameEngine();
        this.GL = new GameLoop(engine);
        GL.start();

        Scene scene = new Scene(
            new StackPane(engine.getCanvas())
        );

        stage.setScene(scene);
        stage.setTitle("Tower Defense – Test");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
    
}

