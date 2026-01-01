package game.combat;

import game.tower.*;
import game.enemy.*;
import java.util.List;

public class CombatSystem {
		TargetingSystem targetingsystem;
		DamageSystem damagesystem;


		public CombatSystem(TargetingSystem targetingsystem, DamageSystem damagesystem){
				this.targetingsystem = targetingsystem;
				this.damagesystem = damagesystem;
		}

		public void update(double dt,List<Tower> towerList, List<Enemy>targetList) {
				for (Tower tower : towerList) {
						tower.update(dt * 20);
						if (tower.canFire()){
								Enemy enemyToShoot = targetingsystem.getClosestEnemy(targetList, tower);
								if (enemyToShoot == null) continue;
								if (enemyToShoot.isAlive()){
								damagesystem.DamageEnemy(tower, enemyToShoot);
								tower.reset_cooldown();
								}
						}		
				}
		 }
}
