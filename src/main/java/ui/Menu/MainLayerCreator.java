package ui.Menu;

import app.Main;
import game.engine.GameLoop;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class MainLayerCreator {
    public BooleanProperty gameStarter;

    public MainLayerCreator(BooleanProperty changeScene)
    {
        this.gameStarter = changeScene; 
    }

    public BorderPane create_MainLayer()
    {
        BorderPane MainBox = new BorderPane();
        MainBox.setStyle("-fx-background-color: white;");

        Button button = new Button("START GAME");

        button.setOnAction(e -> start_game());
        button.getStyleClass().add("start-game-button");
        button.setPrefSize(120,65);

        MainBox.setCenter(button);

        return MainBox;
    }

    public void start_game()
    {
       gameStarter.setValue(true); 
    }


}
