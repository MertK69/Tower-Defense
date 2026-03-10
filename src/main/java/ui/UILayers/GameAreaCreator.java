package ui.UILayers;

import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;

public class GameAreaCreator{

    public GameAreaCreator()
    {

    }

    public Pane create_game_area(Canvas canvas)
    {
        Pane pane = new Pane();
        pane.getChildren().add(canvas);
        canvas.widthProperty().bind(pane.widthProperty());
        canvas.heightProperty().bind(pane.heightProperty());
        pane.setPadding(new Insets(10));
        return pane;
    }



}
