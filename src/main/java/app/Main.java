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
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import ui.*;
import ui.UILayers.LoseScreenCreator;
public class Main extends Application {
    private GameEngine engine;
    private GameLoop GL;
    private UIBuilder uiB;
    private Stage mainStage;
    private StackPane gameRoot;
    public BooleanProperty changeScene = new SimpleBooleanProperty();
    private ObjectProperty<Pathtype> pathtype = new SimpleObjectProperty<>(Pathtype.EASY);
    private IntegerProperty waveNumber = new SimpleIntegerProperty(0);
    private IntegerProperty volumeProperty = new SimpleIntegerProperty(100);
    private BooleanProperty lostGame = new SimpleBooleanProperty();
    private MenuBuilder menuBuilder = new MenuBuilder(changeScene, pathtype, waveNumber, volumeProperty);
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
            if (newValue != null && newValue) {
                // Capture the engine reference now: `engine` is still valid while this listener
                // fires (it is nulled later by lostGameWindow()/changeCurrentSceneByProperty()).
                final GameEngine engineAtLoss = this.engine;
                final StackPane[] loseScreenHolder = new StackPane[1];
                Runnable onPlayAgain = () -> {
                    engineAtLoss.resetGame();
                    this.gameRoot.getChildren().remove(loseScreenHolder[0]);
                };
                Runnable onGoToMenu = () -> {
                    this.gameRoot.getChildren().remove(loseScreenHolder[0]);
                    lostGameWindow();
                };
                StackPane loseScreen = new LoseScreenCreator().create_lose_screen(
                        engineAtLoss.get_waveProperty(), onPlayAgain, onGoToMenu);
                loseScreenHolder[0] = loseScreen;
                this.gameRoot.getChildren().add(loseScreen);
            }
        });
        stage.show();
    }

    public void changeCurrentSceneByProperty()
    {
        javafx.application.Platform.runLater(() -> {
            if (this.mainStage != null) {
                if (this.changeScene.getValue())
                {
                    Scene gameScene = setGameScene();
                    this.mainStage.setScene(gameScene);
                    gameScene.getRoot().requestFocus();
                } else {
                    this.engine = null;
                    this.GL.stop();
                    this.GL = null;
                    this.mainStage.setScene(setMenuScene());
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
        this.engine = new GameEngine(this.pathtype.getValue(), this.waveNumber.getValue(), this.volumeProperty.getValue());
        this.GL = new GameLoop(engine);
        this.GL.start();
        this.lostGame.unbind();
        this.lostGame.bind(this.engine.get_gameLostProperty());
        this.gameRoot = new StackPane();
        BorderPane Layout = new BorderPane();
        this.uiB = new UIBuilder(Layout, engine, changeScene);
        this.uiB.initializeMainPane(engine);
        this.gameRoot.getChildren().add(Layout);
		Scene scene = new Scene(this.gameRoot);
		scene.getStylesheets().add(getClass().getResource("/css/UI.css").toExternalForm());
        scene.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ESCAPE)
            {
                this.engine.clearSelection();
            }
        });
        return scene;
    }

    public void lostGameWindow()
    {
        this.lostGame.unbind();
        this.engine = null;
        this.GL.stop();
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
