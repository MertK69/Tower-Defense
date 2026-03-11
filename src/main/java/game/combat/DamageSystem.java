package game.combat;

import game.enemy.*;
import game.tower.*;

public class DamageSystem {

		
		public void DamageEnemy(double damage, Enemy enemy) {
				enemy.get_damage(damage); 			
		}

}
