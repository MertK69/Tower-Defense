package ui.UILayers;

import game.engine.GameEngine;
import game.tower.Tower;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 * Small overlay shown next to a selected tower with "Upgrade (cost)" / "Sell (value)"
 * actions. Lives inside the same {@code Pane} as the persistent tower-selection click
 * handler (see {@link GameAreaCreator}), so every interactive element here consumes its
 * own mouse-click events -- otherwise a button click would bubble up and immediately
 * clear/switch the selection it is trying to act on (Plan Amendment T-H1/C-M1).
 */
public class TowerInteractionPanel {

    private final VBox root = new VBox(6);
    private final Button upgradeButton = new Button();
    private final Button sellButton = new Button();

    public TowerInteractionPanel(GameEngine engine) {
        root.getStyleClass().add("tower-interaction-panel");
        root.setPadding(new Insets(8));
        root.setVisible(false);
        root.setManaged(false);

        upgradeButton.setOnAction(e -> engine.handleUpgradeRequest());
        sellButton.setOnAction(e -> engine.handleSellRequest());

        root.addEventHandler(MouseEvent.MOUSE_CLICKED, MouseEvent::consume);
        upgradeButton.addEventHandler(MouseEvent.MOUSE_CLICKED, MouseEvent::consume);
        sellButton.addEventHandler(MouseEvent.MOUSE_CLICKED, MouseEvent::consume);

        root.getChildren().addAll(upgradeButton, sellButton);

        engine.get_selectedTowerProperty().addListener((obs, oldTower, newTower) -> refresh(engine, newTower));
        engine.get_MoneyProperty().addListener((obs, oldMoney, newMoney) ->
                refresh(engine, engine.get_selectedTowerProperty().getValue()));
    }

    private void refresh(GameEngine engine, Tower tower) {
        if (tower == null) {
            root.setVisible(false);
            root.setManaged(false);
            return;
        }

        upgradeButton.setText("Upgrade (" + tower.getUpgradeCost() + ")");
        sellButton.setText("Sell (" + tower.getSellValue() + ")");
        upgradeButton.setDisable(tower.isMaxLevel() || engine.get_MoneyProperty().get() < tower.getUpgradeCost());

        root.setLayoutX(tower.getPosition().getX() + 24);
        root.setLayoutY(tower.getPosition().getY() - 24);
        root.setVisible(true);
        root.setManaged(true);
    }

    public VBox getRoot() {
        return root;
    }
}
