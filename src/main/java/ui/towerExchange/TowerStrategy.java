package ui.towerExchange;

import game.engine.GameEngine;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public interface TowerStrategy{

    HBox changeTower(GameEngine engine, BorderPane MainPane); 

}
