package game.enemy;

import util.Vector2;

public class EnemySpawner {

		public Enemy create_enemy(EnemyType type, Vector2 position){
				return new Enemy(type, position);
		}


}
