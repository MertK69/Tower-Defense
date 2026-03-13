package app;

import game.engine.GameEngine;
import game.engine.GameLoop;
import javafx.application.Application;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ui.*;
public class Main extends Application {
    private GameEngine engine = new GameEngine();
    private GameLoop GL = new GameLoop(this.engine);
    private UIBuilder uiB;
    private Stage mainStage;
    public BooleanProperty changeScene = new SimpleBooleanProperty();
    private MenuBuilder menuBuilder = new MenuBuilder(changeScene);

    @Override
    public void init() // init, start, stop, werden in dieser Reihenfolge durch launch() aufgerufen!
    {

    }

    @Override
    public void start(Stage stage) 
    {
        this.mainStage = stage;

        Scene initialScene = setMenuScene();
		UIFXSettings.initializeSettings(this.mainStage, initialScene);
        this.changeScene.addListener(obs -> changeCurrentSceneByProperty());
        stage.show();
    }

    public void changeCurrentSceneByProperty()
    {
        javafx.application.Platform.runLater(() -> {
            Scene gameScene = setGameScene(); 
            if (this.mainStage != null) {
                {
                    if (this.changeScene.getValue() == true)
                    {
                    this.mainStage.setScene(gameScene);
                    // Optional: Fokus auf die neue Szene setzen
                    gameScene.getRoot().requestFocus();
                    } else {
                        this.mainStage.setScene(setMenuScene());
                    }
                }
            }
        });
    }

    public Scene setMenuScene()
    {
        StackPane root = new StackPane();
        BorderPane MainPane = new BorderPane();
        this.menuBuilder.set_MainPane(MainPane);
        this.menuBuilder.create_Menu();
        root.getChildren().add(MainPane);
        Scene menuScene = new Scene(root);
        
        return menuScene;
    }

    public Scene setGameScene()
    {
        GameEngine engine = new GameEngine();
        this.GL = new GameLoop(engine);
        this.GL.start();
        StackPane root = new StackPane(); // root = Grundgerüst, immer nur 1 root
        BorderPane Layout = new BorderPane();
        this.uiB = new UIBuilder(Layout, engine, changeScene);
        this.uiB.initializeMainPane(engine);
        root.getChildren().add(Layout);
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/css/UI.css").toExternalForm());
        return scene;
    }

    public void startGame()
    {

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
