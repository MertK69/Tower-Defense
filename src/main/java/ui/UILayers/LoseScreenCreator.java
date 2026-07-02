package ui.UILayers;

import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class LoseScreenCreator {

    public StackPane create_lose_screen(IntegerProperty waveProperty, Runnable onPlayAgain, Runnable onGoToMenu)
    {
        StackPane overlay = new StackPane();
        overlay.getStyleClass().add("lose-screen-overlay");

        Label title = new Label();
        title.textProperty().bind(Bindings.format("You lasted %d Rounds", waveProperty));
        title.getStyleClass().add("lose-screen-title");

        Button playAgainButton = new Button("Play again");
        playAgainButton.getStyleClass().add("lose-screen-button");
        playAgainButton.setOnAction(e -> onPlayAgain.run());

        Button goToMenuButton = new Button("Go To Menu");
        goToMenuButton.getStyleClass().add("lose-screen-button");
        goToMenuButton.setOnAction(e -> onGoToMenu.run());

        VBox box = new VBox(15);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(30));
        box.getStyleClass().add("lose-screen-box");
        box.getChildren().addAll(title, playAgainButton, goToMenuButton);

        overlay.getChildren().add(box);
        StackPane.setAlignment(box, Pos.CENTER);

        return overlay;
    }
}
