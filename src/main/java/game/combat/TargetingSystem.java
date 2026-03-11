package game.combat;


import util.Vector2;
import game.enemy.*;
import game.tower.*;

import java.util.ArrayList;
import java.util.List;
public class TargetingSystem {

		
		public Enemy getClosestEnemy(List<Enemy>pos_Enemys, Tower tower){
				Enemy closest = null;
				double closest_distance = Double.MAX_VALUE;
				for (Enemy enemy : pos_Enemys){

	 				Vector2 Distance = enemy.getPosition().subtraction(tower.getPosition());
					double Distance_length = Distance.Vector_length();
						if ( closest_distance > Distance_length && Distance_length <= tower.getReichweite()) {
								closest_distance = Distance_length;
								closest = enemy;
						}
				}
				return closest;
		}

        public List<Enemy>getEnemiesInRange(List<Enemy>pos_Enemys, Vector2 Position, double Reichweite)
        {
            List<Enemy>Enemies = new ArrayList<>();

            for(Enemy enemy : pos_Enemys)
            {
                Vector2 Distance = enemy.getPosition().subtraction(Position);
                double Distance_length = Distance.Vector_length();
                if ( Distance_length <= Reichweite)
                {
                    Enemies.add(enemy);
                }
            }
            return Enemies;
        }

}
