package ui.Menu.MainLayerInsets.MainLayerStrategies;

import game.path.Pathtype;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import ui.Menu.MainLayerInsets.MainLayerStrategy;
import ui.Menu.MainLayerInsets.MainLayerStrategyChanger;

public class FirstStrategy implements MainLayerStrategy {
    private BooleanProperty gameStarter;

	@Override
	public VBox changeMainLayerButton(BooleanProperty gameStarter, MainLayerStrategyChanger strategyChanger, BooleanProperty changedStrategy, ObjectProperty<Pathtype> type, IntegerProperty waveNumber) {
        this.gameStarter = gameStarter;

        VBox BigButtons = new VBox(15);
        BigButtons.getStyleClass().add("main-layer-big-buttons");

        Button startGameButton = new Button("START GAME");

        startGameButton.setOnAction(e -> start_game());
        startGameButton.getStyleClass().add("start-game-button");
        startGameButton.setPrefSize(180,60);

        Button setGameOption = new Button("SETTINGS");

        setGameOption.setOnAction(e -> {
            setOptions(strategyChanger);
            changedStrategy.setValue(!changedStrategy.getValue());
        });
        setGameOption.getStyleClass().add("start-game-button");
        setGameOption.setPrefSize(180, 60);

        Button aboutUsButton = new Button("ABOUT US");
        aboutUsButton.setOnAction(e -> {
            showAboutUs(strategyChanger);
            changedStrategy.setValue(!changedStrategy.getValue());
        });
        aboutUsButton.getStyleClass().add("start-game-button");
        aboutUsButton.setPrefSize(180, 60);
    
        BigButtons.getChildren().addAll(startGameButton, setGameOption, aboutUsButton);
	
        return BigButtons;
    }

    public void showAboutUs(MainLayerStrategyChanger strategyChanger)
    {
       strategyChanger.changeStrategy(new ThirdStrategy()); 
    }

    public void start_game()
    {
       gameStarter.setValue(true); 
    }

    public void setOptions(MainLayerStrategyChanger strategyChanger)
    {
       strategyChanger.changeStrategy(new SecondStrategy()); 
    }



}
