package game.enemy;

import game.path.Path;

public class EnemyFactory {

		public Enemy create_enemy(EnemyType type, Path path){
				return new Enemy(type, path);
		}


}
