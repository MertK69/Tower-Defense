package game.combat;

import game.tower.*;
import util.Vector2;
import game.animation.enemyAnimationen.LoadSystems;
import game.animation.hilfsfunktionen.Loader;
import game.animation.towerAnimationen.Fire;
import game.enemy.*;
import game.sattack.SpecialAttack;
import game.sattack.SpecialAttackType;

import java.util.List;

public class CombatSystem {
		TargetingSystem targetingsystem = new TargetingSystem();
		DamageSystem damagesystem = new DamageSystem();


		public CombatSystem()
		{
		}

		public void update(double dt,List<Tower> towerList, List<Enemy>targetList, List<Fire>Bullets, List<SpecialAttack>sattackList) 
        {
				for (Tower tower : towerList) {
						tower.update(dt * 20);
						if (tower.canFire()){
								Enemy enemyToShoot = targetingsystem.getClosestEnemy(targetList, tower);
								if (enemyToShoot == null) continue;
								if (enemyToShoot.isAlive()){
                                if (!(tower.getType() == TowerType.ROCKETLAUNCHERBASIC) && !(tower.getType() == TowerType.ROCKETLAUNCHERADVANCED) && !(tower.getType() == TowerType.ROCKETLAUNCHEREXPERT))
                                {
                                damagesystem.DamageEnemy(tower.getDamage(), enemyToShoot);
								Bullets.add(new Fire(tower.getType() ,tower.getPosition(), enemyToShoot.getPosition(), enemyToShoot));
                                } else {
                                    handleRocketLauncher(tower.getType(), Bullets, tower, enemyToShoot);
                                }
								tower.reset_cooldown();
								tower.lockAnimationLock();
								tower.calculateAngle(enemyToShoot.getPosition());
								}
						}		
				}
                for (Fire bullet : Bullets)
                {
                    if (bullet.reachedTarget())
                    {
                        if(bullet.get_fliesArch() == true)
                        {
                            sattackList.add(new SpecialAttack(SpecialAttackType.BombAttack, bullet.getPosition()));
                            handleSpecialAttack(targetList, bullet.getPosition(), SpecialAttackType.BombAttack);
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

        private void handleRocketLauncher(TowerType type, List<Fire>Bullets, Tower tower, Enemy enemyToShoot)
        {
            if (type == TowerType.ROCKETLAUNCHERBASIC)
            {
                    Bullets.add(new Fire(tower.getType(), tower.getPosition(), enemyToShoot.getPosition() , enemyToShoot ));
            }
            if (type == TowerType.ROCKETLAUNCHERADVANCED)
            {
                for(int i=0; i<2;i++)
                {
                    Bullets.add(new Fire(tower.getType(), tower.getPosition(), Loader.loadRandomVec2withSpecs(enemyToShoot.getPosition()), enemyToShoot ));
                }
            }
            if (type == TowerType.ROCKETLAUNCHEREXPERT)
            {
                    for(int i=0; i<5;i++)
                {
                    Bullets.add(new Fire(tower.getType(), tower.getPosition(), Loader.loadRandomVec2withSpecs(enemyToShoot.getPosition()), enemyToShoot ));
                }

            } 
        }
}
