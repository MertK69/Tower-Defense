package game.tower;

import java.util.List;

import game.economy.Economy;
import game.placement.PlacementSystems;
import util.Vector2;

public class TowerSystems {
		private final PlacementSystems placementSystems = new PlacementSystems();

		public void handleBuyRequest(Economy economy,List<Tower>towers, TowerType type, Vector2 position)
		{
			if (economy.getCurr_Money() >= type.price())
		    {
				placementSystems.placeTower(towers, type, position);	
				economy.towersBought(type);
		    }
		}



}
