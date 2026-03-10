package ui.UILayers;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class TopLayerCreator{

    public TopLayerCreator()
    {

    }

    public HBox create_top_layer()
    {
            HBox TopLayer = new HBox();
            TopLayer.setAlignment(Pos.CENTER_LEFT);
            TopLayer.setPadding(new Insets(1));
            TopLayer.getStyleClass().add("top-bottom-border");
            Button GameButton = new Button("GameSettings");
            GameButton.setPrefSize(120,0.6);
            Button OptionsButton = new Button("Options");
            OptionsButton.setPrefSize(120,0.6);
            Button ExitButton = new Button("Exit Game");
            ExitButton.setPrefSize(120,0.6);

            GameButton.setFocusTraversable(false);
            OptionsButton.setFocusTraversable(false);
            ExitButton.setFocusTraversable(false);
        
            TopLayer.getChildren().addAll(GameButton, OptionsButton, ExitButton);
            return TopLayer;
    }
}
