package ui;
import app.Main;
import game.engine.GameEngine;
import game.tower.TowerType;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import ui.towerExchange.TowerUIChanger;
import ui.towerExchange.TowerStrategies.FirstStrategy;
import ui.towerExchange.TowerStrategies.SecondStrategy;
import util.Vector2;

public class UIBuilder {
    private BorderPane MainPane;
    private TowerUIChanger TowerChanger = new TowerUIChanger();

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
                
            Label coinText = new Label("500");
            coinText.setStyle("-fx-font-size: 36px; -fx-text-fill: darkred; -fx-font-weight: bold; -fx-background-color: white; -fx-padding: 10 30 10 30; -fx-background-radius: 5;");
            
            bottomMenu.getChildren().addAll(coinText, towerMenu);

            return bottomMenu;
        }


        public void buyTower(ActionEvent e, TowerType type, GameEngine engine ) {
            Button sourceButton = (Button) e.getSource();
            Scene scene = sourceButton.getScene();

            scene.setCursor(Cursor.CROSSHAIR);

            javafx.application.Platform.runLater(() -> {
                scene.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        Point2D localPoint = MainPane.getCenter().sceneToLocal(mouseEvent.getSceneX(), mouseEvent.getSceneY());
                        Vector2 position = new Vector2(localPoint.getX(), localPoint.getY());
                        engine.handleBuyRequest(type, position);

                        scene.setCursor(Cursor.DEFAULT);
                        scene.removeEventFilter(MouseEvent.MOUSE_CLICKED, this);
                        mouseEvent.consume();
                    }
                });
            });
        }
        public void updateBottomUI(GameEngine engine) {
            this.MainPane.setBottom(DualBottomLayer(engine));
        }

}
