package game.combat;

import game.tower.*;
import util.Vector2;
import game.animation.towerAnimationen.Fire;
import game.enemy.*;
import game.sattack.SpecialAttackType;

import java.util.List;

public class CombatSystem {
		TargetingSystem targetingsystem = new TargetingSystem();
		DamageSystem damagesystem = new DamageSystem();


		public CombatSystem()
		{
		}

		public void update(double dt,List<Tower> towerList, List<Enemy>targetList, List<Fire>Bullets) 
        {
				for (Tower tower : towerList) {
						tower.update(dt * 20);
						if (tower.canFire()){
								Enemy enemyToShoot = targetingsystem.getClosestEnemy(targetList, tower);
								if (enemyToShoot == null) continue;
								if (enemyToShoot.isAlive()){
								damagesystem.DamageEnemy(tower.getDamage(), enemyToShoot);
								Bullets.add(new Fire(tower.getPosition(), enemyToShoot.getPosition()));
								tower.reset_cooldown();
								tower.lockAnimationLock();
								tower.calculateAngle(enemyToShoot.getPosition());
								}
						}		
				}
		 }

        public void handleSpecialAttack(List<Enemy>Enemies, Vector2 Position, SpecialAttackType attackType)
        {
            List<Enemy> EnemiesInRange = targetingsystem.getEnemiesInRange(Enemies, Position, attackType.get_Reichweite()); 
            for(Enemy enemy : EnemiesInRange)
            {
                enemy.get_damage(attackType.get_Damage());
            }
        }
}
