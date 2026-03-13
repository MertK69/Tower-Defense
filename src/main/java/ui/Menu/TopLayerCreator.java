package ui.Menu;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class TopLayerCreator {

    public HBox create_TopLayer()
    {
        HBox TopBox = new HBox();
        Label GameLabel = new Label("Tower-Defense by MertK69");
        GameLabel.getStyleClass().add("GameLabel");

        TopBox.getChildren().add(GameLabel);

        return TopBox;
    }




}
