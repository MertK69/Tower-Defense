package ui.UILayers;

import game.engine.GameEngine;
import javafx.event.EventTarget;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import util.Vector2;

public class GameAreaCreator{

    public GameAreaCreator()
    {

    }

    public Pane create_game_area(Canvas canvas, GameEngine engine)
    {
        Pane pane = new Pane();
        pane.getChildren().add(canvas);
        canvas.widthProperty().bind(pane.widthProperty());
        canvas.heightProperty().bind(pane.heightProperty());
        pane.setPadding(new Insets(10));

        TowerInteractionPanel interactionPanel = new TowerInteractionPanel(engine);
        pane.getChildren().add(interactionPanel.getRoot());

        // Persistent tower-selection click handler. This is additive to, and does not
        // conflict with, the existing one-shot buy-placement filter registered on the
        // Scene (FirstStrategy): that filter consumes the click before it ever reaches
        // this Pane-level handler while a buy-placement is in progress.
        pane.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            if (isInsidePanel(mouseEvent.getTarget(), interactionPanel.getRoot()))
            {
                return;
            }
            engine.handleTowerClick(new Vector2(mouseEvent.getX(), mouseEvent.getY()));
        });

        return pane;
    }

    private boolean isInsidePanel(EventTarget target, Node panelRoot)
    {
        if (!(target instanceof Node))
        {
            return false;
        }
        Node node = (Node) target;
        while (node != null)
        {
            if (node == panelRoot)
            {
                return true;
            }
            node = node.getParent();
        }
        return false;
    }

}
