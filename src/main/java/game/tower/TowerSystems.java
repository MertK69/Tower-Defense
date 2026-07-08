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

}
