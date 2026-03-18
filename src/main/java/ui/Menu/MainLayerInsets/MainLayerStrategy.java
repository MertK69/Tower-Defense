package ui.Menu.MainLayerInsets;

import game.path.Pathtype;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.scene.layout.VBox;

public interface MainLayerStrategy {
    
    VBox changeMainLayerButton(BooleanProperty gameStarter, MainLayerStrategyChanger strategyChanger, BooleanProperty changedStrategy, ObjectProperty<Pathtype> type, IntegerProperty waveNumber, IntegerProperty volumeProperty);
}
