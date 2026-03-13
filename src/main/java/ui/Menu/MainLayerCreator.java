package ui.Menu;

import app.Main;
import game.engine.GameLoop;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class MainLayerCreator {
    public BooleanProperty gameStarter;

    public MainLayerCreator(BooleanProperty changeScene)
    {
        this.gameStarter = changeScene; 
    }

    public HBox create_MainLayer()
    {
        HBox MainBox = new HBox();
        Button button = new Button("OAOAOAO");
        button.setOnAction(e -> start_game());
        button.setPrefSize(120,65);

        MainBox.getChildren().add(button);



        return MainBox;
    }

    public void start_game()
    {
       gameStarter.setValue(true); 
    }


}
