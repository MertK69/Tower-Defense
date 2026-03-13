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
        TopBox.setPadding(new Insets(40, 50, 40, 50));
        TopBox.setAlignment(Pos.CENTER);
        TopBox.setStyle("-fx-background-color: lightblue");
        Label GameLabel = new Label("Tower-Defense by MertK69");
        GameLabel.getStyleClass().add("GameLabel");

        TopBox.getChildren().add(GameLabel);

        return TopBox;
    }




}
