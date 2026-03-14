package ui.Menu.MainLayerInsets;

import game.path.Pathtype;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.scene.layout.VBox;

public class MainLayerStrategyChanger {
    private MainLayerStrategy strategy;
    private BooleanProperty gameStarter;
    private BooleanProperty changedStrategy;

    public MainLayerStrategyChanger(BooleanProperty gameStarter, BooleanProperty changedStrategy)
    {
        this.gameStarter = gameStarter;
        this.changedStrategy = changedStrategy;
    }

    public void changeStrategy(MainLayerStrategy strategy)
    {
        this.strategy = strategy;
    }

    public VBox setNewMainLayer(ObjectProperty<Pathtype> type, IntegerProperty waveNumber)
    {
        return this.strategy.changeMainLayerButton(this.gameStarter, this, this.changedStrategy, type, waveNumber);
    }



}
