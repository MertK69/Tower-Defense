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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import util.Vector2;

public class UIBuilder {
    private BorderPane MainPane;

        public UIBuilder(BorderPane pane)
        {
            this.MainPane = pane;
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
            pane.setStyle("-fx-background-color: Transparent; -fx-border-color: green; -fx-border-width: 3;");
            return pane;
        }

        public HBox TopLayer()
        {
            HBox TopLayer = new HBox();
            TopLayer.setPadding(new Insets(1));
            TopLayer.setAlignment(Pos.CENTER_LEFT);
            TopLayer.setStyle("-fx-background-color: grey; -fx-border-color: red; -fx-border-width: 1;");
            Button GameButton = new Button();
            GameButton.setPrefSize(120,0.5);
            Button OptionsButton = new Button();
            GameButton.setPrefSize(120,0.5);
            Button ExitButton = new Button();
            GameButton.setPrefSize(120,0.5);

            TopLayer.getChildren().addAll(GameButton, OptionsButton, ExitButton);
            return TopLayer;
        }

        public VBox DualBottomLayer(GameEngine engine)
        {
            VBox DualBottomLayer = new VBox(TopDualLayer(engine), LowerDualLayer());
            return DualBottomLayer;
        }

        public HBox LowerDualLayer()
        {
            HBox towerMenu = new HBox(1000);
            towerMenu.setPadding(new Insets(10));
            towerMenu.setAlignment(Pos.CENTER_LEFT);
            towerMenu.setStyle("-fx-background-color: #6B4E31; -fx-border-color: blue; -fx-border-width: 1;");
            Button button = new Button();
            button.setPrefSize(120,80);
            towerMenu.getChildren().add(button);
            return towerMenu;
        }

        public HBox TopDualLayer(GameEngine engine)
        {
            HBox bottomMenu = new HBox(); 
            bottomMenu.setPadding(new Insets(10, 20, 10, 20)); // Abstand nach oben, rechts, unten, links
            bottomMenu.setAlignment(Pos.CENTER_LEFT);
            bottomMenu.setStyle("-fx-background-color: #6B4E31;"); // Brauner Hintergrund

            Label coinText = new Label("500");
            coinText.setStyle("-fx-font-size: 36px; -fx-text-fill: darkred; -fx-font-weight: bold; -fx-background-color: white; -fx-padding: 10 30 10 30; -fx-background-radius: 5;");
            
            // 3. Ein Container für die Turm-Buttons (rechts daneben)
            HBox towerMenu = new HBox(15); // 15 Pixel Abstand zwischen den Buttons
            towerMenu.setAlignment(Pos.CENTER_LEFT);
            towerMenu.setPadding(new Insets(0, 0, 0, 50)); // Etwas Abstand nach links zu den Münzen
            
            Button turm1 = new Button("Turm 1");
            turm1.setOnAction(e -> buyTower(e, TowerType.BASIC, engine));
            turm1.setPrefSize(80, 50);
            Button turm2 = new Button("Turm 2");
            turm2.setOnAction(e -> buyTower(e, TowerType.ADVANCED, engine));
            turm2.setPrefSize(80, 50);
            Button turm3 = new Button("Turm 3");
            turm3.setOnAction(e -> buyTower(e, TowerType.EXPERT, engine));
            turm3.setPrefSize(80, 50);

            towerMenu.getChildren().addAll(turm1, turm2, turm3);

            // 4. Alles der Haupt-Leiste hinzufügen
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

}
