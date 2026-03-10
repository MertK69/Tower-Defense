package app;

import game.engine.GameEngine;
import game.engine.GameLoop;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import ui.*;
public class Main extends Application {
    private GameLoop GL;
    private UIBuilder uiB;
    @Override
    public void init() // init, start, stop, werden in dieser Reihenfolge durch launch() aufgerufen!
    {

    }

    @Override
    public void start(Stage stage) 
    {
        GameEngine engine = new GameEngine();
        this.GL = new GameLoop(engine);
        GL.start();
		StackPane root = new StackPane(); // root = Grundgerüst, immer nur 1 root
        BorderPane Layout = new BorderPane();
        this.uiB = new UIBuilder(Layout, engine);
        this.uiB.initializeMainPane(engine);
        root.getChildren().add(Layout);

		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/css/UI.css").toExternalForm());
		UIFXSettings.initializeSettings(stage, scene);		
        stage.show();
    }

    @Override
    public void stop()
    {

    }

    public static void main(String[] args) 
    {
        launch();
    }
}
