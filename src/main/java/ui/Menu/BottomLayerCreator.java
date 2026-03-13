package ui.Menu;


import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class BottomLayerCreator {

    public HBox create_bottomLayer()
    {
        HBox bottomBox = new HBox();
        bottomBox.setPadding(new Insets(50,50,50,50));
        bottomBox.setStyle("-fx-background-color: lightblue");  

        return bottomBox;
    }




}
