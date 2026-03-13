package game.engine;

import game.enemy.*;
import game.wave.*;
import game.path.*;
import game.render.RenderSystems;
import game.sattack.SpecialAttack;
import game.sattack.SpecialAttackFactory;
import game.sattack.SpecialAttackType;
import game.tower.*;
import game.animation.towerAnimationen.Fire;
import game.combat.*;
import game.economy.*;
import util.Vector2;
import java.util.*;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class GameEngine {
		private final List<Enemy>enemies = new ArrayList<>();
		private final List<Tower>towers = new ArrayList<>();
		private final List<Enemy>EnemiesToRemove = new ArrayList<>();
		private final List<Fire>Bullets = new ArrayList<>();
        private final List<SpecialAttack> sattackList = new ArrayList<>();
		private TowerFactory towerFactory = new TowerFactory();
		private EnemyFactory enemyFactory = new EnemyFactory();
        private SpecialAttackFactory sattackFactory = new SpecialAttackFactory();
		private WaveFactory waveFactory = new WaveFactory();
		private PathFactory pathFactory = new PathFactory();
		private ActiveWave activeWave = null;
        private IntegerProperty waveProperty = new SimpleIntegerProperty(1);
        private IntegerProperty enemyProperty = new SimpleIntegerProperty();
		private int waveNumber = 50;
		private Path path = null;
	    private	Canvas canvas = new Canvas(1200, 800);
        private GraphicsContext gc = canvas.getGraphicsContext2D();
		private TowerSystems towerSystems = new TowerSystems();
        private CombatSystem combatSystem = new CombatSystem();
		private RenderSystems renderSystems = new RenderSystems();
		private EconomySystems economySystems = new EconomySystems();
		private Economy economy = new Economy(economySystems, Pathtype.EASY);
		public void update(double stepTime) 
		{

				if ( path == null) path = pathFactory.createPath(Pathtype.EASY);
				if ( activeWave == null ) activeWave = new ActiveWave(createWave());
		
				EnemyType spawnType = activeWave.update(stepTime);

				if (spawnType != null)
				{
						createEnemy(spawnType, path);
				}
                this.enemyProperty.set(enemies.size());

				if (activeWave.isFinished() && enemies.isEmpty())
				{
						activeWave = null;
						waveNumber++;
                        this.waveProperty.set(this.waveNumber);
				}
				EnemiesToRemove.clear();
				for (Enemy enemy : enemies)
				{
						enemy.update(stepTime);
						if (!enemy.isAlive() || enemy.isFinished())
						{
								EnemiesToRemove.add(enemy);
								continue;
						}
				}
				enemies.removeAll(EnemiesToRemove);

				List<Tower>TowersToRemove = new ArrayList<>();
				for (Tower tower : towers)
				{
						tower.update(stepTime);
				}
				List<Fire>bulletsToRemove = new ArrayList<>();
				for (Fire bullet : Bullets)
				{
						bullet.updatePosition(stepTime);
						if (bullet.reachedTarget())
						{
								bulletsToRemove.add(bullet);
						}
				}
				Bullets.removeAll(bulletsToRemove);
                List<SpecialAttack>SAttacksToRemove = new ArrayList<>();
                for(SpecialAttack specialAttack : sattackList)
                {   
                    if(specialAttack.reachedLastFrame())
                    {
                        SAttacksToRemove.add(specialAttack);
                    }
                }
                sattackList.removeAll(SAttacksToRemove);

				combatSystem.update(stepTime, towers, enemies, Bullets);
				economy.update(EnemiesToRemove, TowersToRemove);
		}

		public void render(double STEP)
		{
				renderSystems.renderBackground(gc, canvas);

				if ( path != null)
				{
						renderSystems.renderPath(gc, path);
				}
				renderSystems.renderTower(gc, towers, STEP);	

				renderSystems.renderEnemies(gc, enemies, STEP, path);

				renderSystems.renderRemovedEnemies(gc, EnemiesToRemove);

				renderSystems.renderBullets(gc, Bullets);

                renderSystems.renderSpecialAttacks(gc, sattackList, STEP);
		}

		public void buyTower(TowerType type, Vector2 position)
		{
				towers.add(towerFactory.create_tower(type, position));	
		}

        public void createSAttack(SpecialAttackType type, Vector2 position)
        {
                sattackList.add(sattackFactory.create_sattack(type, position));
        }

		public void createEnemy(EnemyType type, Path path)
		{
				enemies.add(enemyFactory.create_enemy(type, path));
		}

		public Wave createWave()
		{
				return waveFactory.create_wave(waveNumber);		
		}

		public Canvas getCanvas()
		{
				return canvas;
		}

		public void handleBuyRequest(TowerType type, Vector2 position)
		{
				towerSystems.handleBuyRequest(economy, towers, type, position);	
		}

        public void handleSpecialAttack(Vector2 Position ,SpecialAttackType attackType)
        {
            createSAttack(attackType, Position);
            combatSystem.handleSpecialAttack(this.enemies, Position, attackType); 
        }

        public IntegerProperty get_MoneyProperty()
        {
            return this.economy.Money_Property();
        }

        public IntegerProperty get_waveProperty()
        {
            return this.waveProperty;
        }

        public IntegerProperty get_enemyProperty()
        {
            return this.enemyProperty;
        }
}
