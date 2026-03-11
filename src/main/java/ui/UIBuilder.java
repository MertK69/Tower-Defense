package ui;
import game.engine.GameEngine;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import ui.UILayers.UIFactory;

public class UIBuilder {
    private BorderPane MainPane;
    private UIFactory uiFactory;
        public UIBuilder(BorderPane pane, GameEngine engine)
        {
            this.MainPane = pane;
            this.uiFactory = new UIFactory(engine.getCanvas(), engine, pane);
        }

		public void initializeMainPane(GameEngine engine)
		{
            this.MainPane.setTop(TopLayer());
            this.MainPane.setCenter(GameArea());
            this.MainPane.setBottom(DualBottomLayer());
		}

        public Pane GameArea()
        {
            return this.uiFactory.create_GameArea();
        }

        public HBox TopLayer()
        {
            return this.uiFactory.create_TopLayer();
        }

        public VBox DualBottomLayer()
        {
            return this.uiFactory.create_BottomLayer();
        }

}
