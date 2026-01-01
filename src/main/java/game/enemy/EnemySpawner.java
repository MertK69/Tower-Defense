package game.enemy;

import util.Vector2;
import game.path.Path;

public class EnemySpawner {

		public Enemy create_enemy(EnemyType type, Path path){
				return new Enemy(type, path);
		}


}
