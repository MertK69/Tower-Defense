package ui.towerExchange;

import game.engine.GameEngine;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class TowerUIChanger{
    private TowerStrategy strategy;

    public void setStrategy(TowerStrategy strategy)
    {
       this.strategy = strategy; 
    }

    public HBox createTowerUI(GameEngine engine, BorderPane MainPane)
    {
       return this.strategy.changeTower(engine, MainPane); 
    }

}
