package game.placement;

import java.util.List;

import game.tower.Tower;
import game.tower.TowerType;
import util.Vector2;

public class PlacementSystems {

		public PlacementSystems()
		{}

		public void placeTower(List<Tower>towers, TowerType type, Vector2 position)
		{
				towers.add(new Tower(type, position));
		}



}
