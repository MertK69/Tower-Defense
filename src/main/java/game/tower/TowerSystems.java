package game.tower;

import java.util.List;

import game.economy.Economy;
import game.path.Path;
import game.placement.PlacementSystems;
import util.Vector2;

public class TowerSystems {
		private final PlacementSystems placementSystems = new PlacementSystems();

		public boolean handleBuyRequest(Economy economy,List<Tower>towers, Path path, TowerType type, Vector2 position)
		{
			if (economy.getMoney() >= type.price() && placementSystems.isValidPlacement(towers, path, position))
		    {
				placementSystems.placeTower(towers, type, position);
				economy.towersBought(type);
				return true;
		    }
			return false;
		}

		public boolean isValidPlacement(List<Tower> towers, Path path, Vector2 position)
		{
				return placementSystems.isValidPlacement(towers, path, position);
		}

		/**
		 * Queues {@code target} for removal on the next {@code GameEngine.update()} drain.
		 * Guards against selling a tower that is no longer active, or double-queueing the
		 * same tower within the same frame (which would otherwise credit the refund twice).
		 */
		public void handleSellRequest(Economy economy, List<Tower> towers, Tower target, List<Tower> pendingSoldTowers)
		{
			if (target == null) return;
			if (!towers.contains(target)) return;
			if (pendingSoldTowers.contains(target)) return;
			pendingSoldTowers.add(target);
		}

		/**
		 * Upgrades {@code target} if it is not already at max level and the player can
		 * afford the current upgrade cost. Silent no-op otherwise (matches the existing
		 * convention of this class -- no exceptions).
		 */
		public void handleUpgradeRequest(Economy economy, Tower target)
		{
			if (target == null) return;
			if (target.isMaxLevel()) return;
			if (economy.getMoney() < target.getUpgradeCost()) return;
			economy.towerUpgraded(target);
			target.upgrade();
		}

		/**
		 * Pure hit-testing seam for tower selection. Kept static and free of GameEngine
		 * so it can be unit-tested without constructing JavaFX-backed collaborators
		 * (SoundSystems/Canvas), which require a display/toolkit to initialize.
		 */
		public static Tower findTowerAt(List<Tower> towers, double x, double y, double radius)
		{
			for (Tower tower : towers)
			{
				Vector2 position = tower.getPosition();
				double dx = position.getX() - x;
				double dy = position.getY() - y;
				double distance = Math.sqrt(dx * dx + dy * dy);
				if (distance <= radius)
				{
					return tower;
				}
			}
			return null;
		}

}
