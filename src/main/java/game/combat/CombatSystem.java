package game.combat;

import game.tower.*;
import game.animation.towerAnimationen.Fire;
import game.enemy.*;
import java.util.List;

public class CombatSystem {
		TargetingSystem targetingsystem;
		DamageSystem damagesystem;


		public CombatSystem(TargetingSystem targetingsystem, DamageSystem damagesystem){
				this.targetingsystem = targetingsystem;
				this.damagesystem = damagesystem;
		}

		public void update(double dt,List<Tower> towerList, List<Enemy>targetList, List<Fire>Bullets) {
				for (Tower tower : towerList) {
						tower.update(dt * 20);
						if (tower.canFire()){
								Enemy enemyToShoot = targetingsystem.getClosestEnemy(targetList, tower);
								if (enemyToShoot == null) continue;
								if (enemyToShoot.isAlive()){
								damagesystem.DamageEnemy(tower, enemyToShoot);
								Bullets.add(new Fire(tower.getPosition(), enemyToShoot.getPosition()));
								tower.reset_cooldown();
								tower.lockAnimationLock();
								}
						}		
				}
		 }
}
