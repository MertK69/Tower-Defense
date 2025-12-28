package game.tower;

import util.Vector2;

public class TowerFactory {

		public Tower create_tower(TowerType type, Vector2 position) {
				return new Tower(type, position);
		}

}
