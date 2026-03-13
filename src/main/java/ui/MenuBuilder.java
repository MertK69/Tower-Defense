package ui;

import app.Main;
import game.engine.GameLoop;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.layout.BorderPane;
import ui.Menu.BottomLayerCreator;
import ui.Menu.MainLayerCreator;
import ui.Menu.TopLayerCreator;

public class MenuBuilder {
    private BorderPane MainPane;
    private BottomLayerCreator bottomLayerCreator = new BottomLayerCreator();
    private TopLayerCreator topLayerCreator = new TopLayerCreator();
    private MainLayerCreator mainLayerCreator;

    public MenuBuilder(BooleanProperty changeScene)
    {
        this.mainLayerCreator = new MainLayerCreator(changeScene);
    }

    public void set_MainPane(BorderPane pane)
    {
        this.MainPane = pane;
    }

    public void create_Menu()
    {
        this.MainPane.setBottom(bottomLayerCreator.create_bottomLayer());
        this.MainPane.setCenter(mainLayerCreator.create_MainLayer());
        this.MainPane.setTop(topLayerCreator.create_TopLayer());
    }

    public MainLayerCreator test()
    {
        return this.mainLayerCreator;
    }

}
