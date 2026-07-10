package game.engine;

import game.economy.Economy;
import game.tower.Tower;
import game.tower.TowerSystems;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import util.Vector2;

import java.util.List;

/**
 * Owns the currently-selected tower and the click/sell/upgrade interactions that act on
 * it (AC #1-#7). Extracted from {@link GameEngine} to keep tower-selection concerns
 * separate from wave/combat orchestration and keep {@code GameEngine} within the
 * project's class-size guideline.
 */
class TowerSelectionController {

		private static final double TOWER_HIT_RADIUS = 18d;

		private final ObjectProperty<Tower> selectedTower = new SimpleObjectProperty<>(null);
		private final TowerSystems towerSystems;

		TowerSelectionController(TowerSystems towerSystems) {
				this.towerSystems = towerSystems;
		}

		/**
		 * Selects the tower hit by a click at {@code position}, or clears the selection
		 * if the click hit empty ground (AC #1 / AC #7).
		 */
		void handleTowerClick(List<Tower> towers, Vector2 position) {
				Tower hit = TowerSystems.findTowerAt(towers, position.getX(), position.getY(), TOWER_HIT_RADIUS);
				selectedTower.setValue(hit);
		}

		/**
		 * Sells the currently selected tower (AC #4/#5) and closes the interaction panel.
		 */
		void handleSellRequest(Economy economy, List<Tower> towers, List<Tower> pendingSoldTowers) {
				towerSystems.handleSellRequest(economy, towers, selectedTower.getValue(), pendingSoldTowers);
				clearSelection();
		}

		/**
		 * Upgrades the currently selected tower (AC #2/#3). Selection is kept so the
		 * panel can refresh with the new level/cost.
		 */
		void handleUpgradeRequest(Economy economy) {
				towerSystems.handleUpgradeRequest(economy, selectedTower.getValue());
		}

		void clearSelection() {
				selectedTower.setValue(null);
		}

		ObjectProperty<Tower> selectedTowerProperty() {
				return selectedTower;
		}
}
