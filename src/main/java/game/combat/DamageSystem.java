package game.combat;

import game.enemy.*;
import game.tower.*;

public class DamageSystem {

		
		public void DamageEnemy(Tower tower, Enemy enemy) {
				enemy.get_damage(tower.getDamage()); 			
		}
}
