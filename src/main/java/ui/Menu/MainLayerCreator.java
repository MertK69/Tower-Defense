package ui.Menu;

import game.animation.enemyAnimationen.LoadSystems;
import game.path.Pathtype;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.MapProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import ui.Menu.MainLayerInsets.MainLayerStrategy;
import ui.Menu.MainLayerInsets.MainLayerStrategyChanger;
import ui.Menu.MainLayerInsets.MainLayerStrategies.FirstStrategy;

public class MainLayerCreator {
    public BooleanProperty gameStarter;
    private LoadSystems Loader = new LoadSystems();
    private BooleanProperty changedStrategy = new SimpleBooleanProperty(false);
    private MainLayerStrategyChanger strategyChanger;
    private BorderPane MainBox = new BorderPane();
    private IntegerProperty waveNumber;
    private IntegerProperty volumeProperty;
    private ObjectProperty<Pathtype> type;

    public MainLayerCreator(BooleanProperty changeScene, ObjectProperty<Pathtype> type, IntegerProperty waveNumber, IntegerProperty volumeProperty)
    {
        this.strategyChanger = new MainLayerStrategyChanger(changeScene, changedStrategy);
        this.type = type; this.waveNumber = waveNumber; this.volumeProperty = volumeProperty;
        this.strategyChanger.changeStrategy(new FirstStrategy());
        this.changedStrategy.addListener(e -> refreshUI());
    }

    public Parent create_MainLayer()
    {
        Image gifImage = Loader.loadGif("/images/background-video/background_gif");
    
        ImageView backgroundView = new ImageView(gifImage);
        VBox Buttons = this.strategyChanger.setNewMainLayer(this.type, this.waveNumber, this.volumeProperty);
        MainBox.setCenter(Buttons);

        StackPane root = new StackPane();

        backgroundView.fitWidthProperty().bind(root.widthProperty());
        backgroundView.fitHeightProperty().bind(root.heightProperty());
        backgroundView.setPreserveRatio(false);

        root.getChildren().add(backgroundView);

        root.getChildren().add(MainBox);

        return root;
    }

    public void refreshUI()
    {
        this.MainBox.setCenter(this.strategyChanger.setNewMainLayer(this.type, this.waveNumber, this.volumeProperty));
    }



}
