package ui.Menu;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TopLayerCreator {

    public VBox create_TopLayer()
    {
        VBox TopBox = new VBox();
        TopBox.setPadding(new Insets(30, 30, 30, 30));
        TopBox.getStyleClass().add("top-menu-bar");
        Label GameLabel = new Label("Tower-Defense by MertK69");
        GameLabel.getStyleClass().add("top-menu-game-name");

        TopBox.getChildren().add(GameLabel);

        return TopBox;
    }




}
