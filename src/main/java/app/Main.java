package app;

import game.engine.GameEngine;
import game.engine.GameLoop;
import game.path.Pathtype;
import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import ui.*;
public class Main extends Application {
    private GameEngine engine;
    private GameLoop GL;
    private UIBuilder uiB;
    private Stage mainStage;
    public BooleanProperty changeScene = new SimpleBooleanProperty();
    private ObjectProperty<Pathtype> pathtype = new SimpleObjectProperty<>(Pathtype.EASY);
    private IntegerProperty waveNumber = new SimpleIntegerProperty(0);
    private BooleanProperty lostGame = new SimpleBooleanProperty();
    private MenuBuilder menuBuilder = new MenuBuilder(changeScene, pathtype, waveNumber);
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
        this.lostGame.addListener((obs, oldValue, newValue) -> {
            if (newValue != null && newValue == true) {
                lostGameWindow();
            }
        });
        stage.show();
    }

    public void changeCurrentSceneByProperty()
    {
        javafx.application.Platform.runLater(() -> {
            if (this.mainStage != null) {
                {
                    if (this.changeScene.getValue() == true)
                    {
                    Scene gameScene = setGameScene();
                    this.mainStage.setScene(gameScene);
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
        this.menuBuilder.create_Menu(this.pathtype.getValue(), this.waveNumber.getValue());
        root.getChildren().add(MainPane);
        Scene menuScene = new Scene(root);
        menuScene.getStylesheets().add(getClass().getResource("/css/UI.css").toExternalForm());
       
        return menuScene;
    }

    public Scene setGameScene()
    {
        this.engine = new GameEngine(this.pathtype.getValue(), this.waveNumber.getValue());
        this.GL = new GameLoop(engine);
        this.GL.start();
        this.lostGame.unbind();
        this.lostGame.bind(this.engine.get_gameLostProperty());
        StackPane root = new StackPane(); 
        BorderPane Layout = new BorderPane();
        this.uiB = new UIBuilder(Layout, engine, changeScene);
        this.uiB.initializeMainPane(engine);
        root.getChildren().add(Layout);
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/css/UI.css").toExternalForm());
        return scene;
    }

    public void lostGameWindow()
    {
        this.lostGame.unbind();
        this.engine = null;
        this.GL.stop();
        this.GL = null;
        this.changeScene.setValue(false);
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
