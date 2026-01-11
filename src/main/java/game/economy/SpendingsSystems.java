package game.economy;

import java.util.List;

import game.tower.Tower;
import game.tower.TowerType;

public class SpendingsSystems {

		public SpendingsSystems()
		{}

		
		public int buyTower(TowerType type)
		{
				return type.price();
		}


}
