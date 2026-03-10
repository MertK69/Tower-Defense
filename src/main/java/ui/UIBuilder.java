package ui;
import game.engine.GameEngine;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import ui.towerExchange.TowerUIChanger;
import ui.towerExchange.TowerStrategies.FirstStrategy;
import ui.towerExchange.TowerStrategies.SecondStrategy;

public class UIBuilder {
    private BorderPane MainPane;
    private TowerUIChanger TowerChanger = new TowerUIChanger();
    private IntegerProperty Money = new SimpleIntegerProperty(0);
    private IntegerProperty Enemies = new SimpleIntegerProperty();
    private IntegerProperty LifesLeft = new SimpleIntegerProperty();
    private IntegerProperty WaveNumber = new SimpleIntegerProperty(1);
        public UIBuilder(BorderPane pane)
        {
            this.MainPane = pane;
            this.TowerChanger.setStrategy(new FirstStrategy());
        }

		public void initializeMainPane(GameEngine engine)
		{
            this.MainPane.setTop(TopLayer());
            this.MainPane.setCenter(GameArea(engine.getCanvas()));
            this.MainPane.setBottom(DualBottomLayer(engine));
		}

        public Pane GameArea(Canvas canvas)
        {
            Pane pane = new Pane();
            pane.getChildren().add(canvas);
            canvas.widthProperty().bind(pane.widthProperty());
            canvas.heightProperty().bind(pane.heightProperty());
            pane.setPadding(new Insets(10));
            return pane;
        }

        public HBox TopLayer()
        {
            HBox TopLayer = new HBox();
            TopLayer.setAlignment(Pos.CENTER_LEFT);
            TopLayer.setPadding(new Insets(1));
            TopLayer.getStyleClass().add("top-bottom-border");
            Button GameButton = new Button("GameSettings");
            GameButton.setPrefSize(120,0.6);
            Button OptionsButton = new Button("Options");
            OptionsButton.setPrefSize(120,0.6);
            Button ExitButton = new Button("Exit Game");
            ExitButton.setPrefSize(120,0.6);

            GameButton.setFocusTraversable(false);
            OptionsButton.setFocusTraversable(false);
            ExitButton.setFocusTraversable(false);
        
            TopLayer.getChildren().addAll(GameButton, OptionsButton, ExitButton);
            return TopLayer;
        }

        public VBox DualBottomLayer(GameEngine engine)
        {
            VBox DualBottomLayer = new VBox(TopDualLayer(engine), LowerDualLayer(engine));
            return DualBottomLayer;
        }

        public HBox LowerDualLayer(GameEngine engine)
        {
            HBox towerMenu = new HBox(10);
            towerMenu.setPadding(new Insets(10));
            towerMenu.setAlignment(Pos.CENTER_LEFT);
            towerMenu.setStyle("-fx-background-color: #6B4E31;");
            Button button = new Button();
            button.setPrefSize(120,65);
            button.setOnAction(e -> {
            TowerChanger.setStrategy(new FirstStrategy());
            updateBottomUI(engine);
            });
            Button button2 = new Button();
            button2.setPrefSize(120,65);
            button2.setOnAction(e -> {
            TowerChanger.setStrategy(new SecondStrategy());
            updateBottomUI(engine);
            });
            towerMenu.getChildren().addAll(button, button2);
            return towerMenu;
        }

        public HBox TopDualLayer(GameEngine engine)
        {
            HBox bottomMenu = new HBox(); 
            bottomMenu.setPadding(new Insets(10, 20, 10, 20)); // Abstand nach oben, rechts, unten, links
            bottomMenu.setAlignment(Pos.CENTER_LEFT);
            bottomMenu.getStyleClass().add("bottom-top-border");
            HBox towerMenu = TowerChanger.createTowerUI(engine, MainPane);
            VBox Test = new VBox(10);
            Label life = new Label("Lifes left: ");
            life.textProperty().bind(engine.get_waveProperty().asString());
            Label EnemiesLeft = new Label("Enemies left: ");
            life.getStyleClass().add("half-sized"); 
            EnemiesLeft.getStyleClass().add("half-sized");
            EnemiesLeft.textProperty().bind(engine.get_enemyProperty().asString());
            Test.setPadding(new Insets(10,10,10,10));
            Test.setAlignment(Pos.CENTER_LEFT);
            Test.getChildren().addAll(life, EnemiesLeft);
            Label coinText = new Label();
            coinText.getStyleClass().add("coin-field");
            coinText.textProperty().bind(engine.get_MoneyProperty().asString());
            bottomMenu.getChildren().addAll(coinText, Test,  towerMenu);
            return bottomMenu;
        }

        public void updateBottomUI(GameEngine engine) 
        {
            this.MainPane.setBottom(DualBottomLayer(engine));
        }
}
