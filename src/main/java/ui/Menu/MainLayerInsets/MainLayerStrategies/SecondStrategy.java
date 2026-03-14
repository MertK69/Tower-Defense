package ui.Menu.MainLayerInsets.MainLayerStrategies;


import game.path.Pathtype;
import javafx.animation.PauseTransition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import ui.Menu.MainLayerInsets.MainLayerStrategy;
import ui.Menu.MainLayerInsets.MainLayerStrategyChanger;

public class SecondStrategy implements MainLayerStrategy {

	@Override
	public VBox changeMainLayerButton(BooleanProperty gameStarter, MainLayerStrategyChanger strategyChanger, BooleanProperty strategyChanged, ObjectProperty<Pathtype> type, IntegerProperty waveNumber) {

        VBox BigButtons = new VBox(15);
        BigButtons.getStyleClass().add("main-layer-big-buttons");

        HBox waveNumberBox = new HBox(10);
        waveNumberBox.setAlignment(Pos.CENTER);

        Button startWaveButton = new Button("Start Wave");
        
        startWaveButton.setOnAction(e -> {
            waveNumber.setValue(0);
            highlightedButton(startWaveButton); 
        });
        startWaveButton.getStyleClass().add("start-game-button");
        startWaveButton.setPrefSize(195,60);

        Slider slider = new Slider(0, 100, 0);

        slider.setPrefSize(385, 60);
        slider.getStyleClass().add("start-game-button");

        slider.valueProperty().addListener((obs, oldVal, newVal) -> {
            waveNumber.set(newVal.intValue());
        });
    
        waveNumber.addListener((obs, oldVal, newVal) -> {
           slider.setValue(newVal.doubleValue());
        });

        Label currWave = new Label();
        currWave.textProperty().bind(waveNumber.asString());
        currWave.getStyleClass().add("curr-wave-label");
        currWave.setPrefSize(180, 60);

        waveNumberBox.getChildren().addAll(startWaveButton, slider, currWave);

        HBox difficultyBox = new HBox(10);
        difficultyBox.setAlignment(Pos.CENTER);

        Button changeToEasy = new Button("EASY");

        changeToEasy.setOnAction(e -> {
                type.setValue(Pathtype.EASY);
                highlightedButton(changeToEasy);
        });
        changeToEasy.getStyleClass().add("start-game-button");
        changeToEasy.setPrefSize(140, 60);

        Button changeToMedium = new Button("MEDIUM");

        changeToMedium.setOnAction(e -> {
            type.setValue(Pathtype.MEDIUM);
            highlightedButton(changeToMedium);
        });
        changeToMedium.getStyleClass().add("start-game-button");
        changeToMedium.setPrefSize(140,60);

        Button changeToHard = new Button("HARD");

        changeToHard.setOnAction(e -> {
            type.setValue(Pathtype.HARD);
            highlightedButton(changeToHard);
        });
        changeToHard.getStyleClass().add("start-game-button");
        changeToHard.setPrefSize(140,60);
    
        Button changeToImpossible = new Button("IMPOSSIBLE");

        changeToImpossible.setOnAction(e -> {
            type.setValue(Pathtype.IMPOSSIBLE);
            highlightedButton(changeToImpossible);
        });
        changeToImpossible.getStyleClass().add("start-game-button");
        changeToImpossible.setPrefSize(140,60);

        Label currentDifficulty = new Label();
        currentDifficulty.textProperty().bind(type.asString());
        currentDifficulty.getStyleClass().add("curr-wave-label");
        currentDifficulty.setPrefSize(180, 60);

        difficultyBox.getChildren().addAll(changeToEasy, changeToMedium, changeToHard, changeToImpossible, currentDifficulty);


        Button setAndReturn = new Button("Set and return");
        setAndReturn.setOnAction(e -> {
            changeStrat(strategyChanger);
            returnToMenu(strategyChanged);
        });
        setAndReturn.getStyleClass().add("start-game-button");
        setAndReturn.setPrefSize(180, 60);

        BigButtons.getChildren().addAll(waveNumberBox,  difficultyBox, setAndReturn);
	
        return BigButtons;
    }

    public void returnToMenu(BooleanProperty strategyChanged)
    {
        strategyChanged.setValue(!strategyChanged.getValue()); 
    }

    public void changeStrat(MainLayerStrategyChanger strategyChanger)
    {
       strategyChanger.changeStrategy(new FirstStrategy()); 
    }

    public void highlightedButton(Button button)
    {
        button.getStyleClass().add("highlighted-button");
        PauseTransition pause = new PauseTransition(Duration.seconds(0.2));
        pause.setOnFinished(Event -> button.getStyleClass().remove("highlighted-button"));
        pause.play();
    }
}
