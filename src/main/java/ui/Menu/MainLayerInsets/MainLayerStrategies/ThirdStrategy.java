package ui.Menu.MainLayerInsets.MainLayerStrategies;

import game.path.Pathtype;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ui.Menu.MainLayerInsets.MainLayerStrategy;
import ui.Menu.MainLayerInsets.MainLayerStrategyChanger;

public class ThirdStrategy implements MainLayerStrategy {

	@Override
	public VBox changeMainLayerButton(BooleanProperty gameStarter, MainLayerStrategyChanger strategyChanger,
			BooleanProperty changedStrategy, ObjectProperty<Pathtype> type, IntegerProperty waveNumber, IntegerProperty volumeProperty) {

        VBox MainBox = new VBox(10);
        MainBox.setAlignment(Pos.CENTER);

        Label aboutUs = new Label("Was hast du hier erwartet amk");
        aboutUs.getStyleClass().add("about-us");
        aboutUs.setPrefSize(350, 300);

        HBox lowerRow = new HBox(20);

        lowerRow.setAlignment(Pos.CENTER);
       
        Button returnButton = new Button("Return to Menu");
        returnButton.setOnAction(e -> {
            changeStrat(strategyChanger);
            returnToMenu(changedStrategy);
        });
        returnButton.getStyleClass().add("return-button");
        returnButton.setPrefSize(165, 40);
       
        Button secondButton = new Button();
        secondButton.getStyleClass().add("start-game-button");
        secondButton.setPrefSize(165, 40);

        lowerRow.getChildren().addAll(secondButton, returnButton);

        MainBox.getChildren().addAll(aboutUs, lowerRow);

        return MainBox;
	}

    public void returnToMenu(BooleanProperty strategyChanged)
    {
        strategyChanged.setValue(!strategyChanged.getValue()); 
    }

    public void changeStrat(MainLayerStrategyChanger strategyChanger)
    {
       strategyChanger.changeStrategy(new FirstStrategy()); 
    }
}
