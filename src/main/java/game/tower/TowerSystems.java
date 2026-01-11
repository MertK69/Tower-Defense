package game.tower;

import java.util.List;

import game.economy.Economy;
import game.placement.PlacementSystems;
import util.Vector2;

public class TowerSystems {
		private final PlacementSystems placementSystems = new PlacementSystems();

		public void handleBuyRequest(Economy economy,List<Tower>towers, TowerType type)
		{
			if (economy.getCurr_Money() >= type.price())
		    {
				Vector2 position = new Vector2(200, 200); 
				placementSystems.placeTower(towers, type, position);	
				economy.towersBought(type);
		    }
		}



}
