package game.engine;

import game.enemy.*;
import game.wave.*;
import game.path.*;
import game.render.RenderSystems;
import game.render.*;
import game.tower.Tower;
import game.tower.TowerFactory;
import game.tower.TowerType;
import game.animation.towerAnimationen.Fire;
import game.combat.*;
import game.economy.EarningsSystems;
import game.economy.Economy;
import game.economy.EconomySystems;
import game.economy.SpendingsSystems;
import util.Vector2;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;



public class GameEngine {
		private final List<Enemy>enemies = new ArrayList<>();
		private final List<Tower>towers = new ArrayList<>();
		private final List<Enemy>EnemiesToRemove = new ArrayList<>();
		private final List<Fire>Bullets = new ArrayList<>();
		private TowerFactory towerfactory = new TowerFactory();
		private EnemySpawner enemyspawner = new EnemySpawner();
		private WaveFactory waveFactory = new WaveFactory();
		private PathFabric pathFabric = new PathFabric();
		private ActiveWave activeWave = null;
		private int waveNumber = 1;
		private Path path = null;
	    private	Canvas canvas = new Canvas(1200, 800);
        private GraphicsContext gc = canvas.getGraphicsContext2D();
		private TargetingSystem targetingSystem = new TargetingSystem();
        private DamageSystem damageSystem = new DamageSystem();
        private CombatSystem combatSystem = new CombatSystem(targetingSystem, damageSystem);
		private TowerRenderer towerRenderer = new TowerRenderer();
		private EnemyRenderer enemyRenderer = new EnemyRenderer();
		private PathRenderer pathRenderer = new PathRenderer();
		private BackgroundRenderer backgroundRenderer = new BackgroundRenderer();
		private UIRenderer uiRenderer = new UIRenderer();
		private BulletRenderer bulletRenderer = new BulletRenderer();
		private RenderSystems renderSystems = new RenderSystems(towerRenderer, enemyRenderer, pathRenderer, backgroundRenderer, uiRenderer, bulletRenderer);
		private EarningsSystems earningsSystems = new EarningsSystems();
		private SpendingsSystems spendingsSystems = new SpendingsSystems();
		private EconomySystems economySystems = new EconomySystems(earningsSystems, spendingsSystems);
		private Economy economy = new Economy(economySystems, Pathtype.EASY);
		public void update(double stepTime) 
		{
				if ( path == null ) 
				{
						Tower tower1 = new Tower(TowerType.BASIC,    new Vector2(500, 270));
						Tower tower2 = new Tower(TowerType.EXPERT, new Vector2(350, 270));
						Tower tower3 = new Tower(TowerType.BASIC,    new Vector2(100, 270));
						Tower tower4 = new Tower(TowerType.ADVANCED, new Vector2(170, 270));
						towers.add(tower1);	towers.add(tower2); towers.add(tower3); towers.add(tower4);
				}

				if ( path == null) path = pathFabric.createPath(Pathtype.EASY);
				if ( activeWave == null ) activeWave = new ActiveWave(createWave());
		
				EnemyType spawnType = activeWave.update(stepTime);
				
				if (spawnType != null)
				{
						createEnemy(spawnType, path);
				}


				if (activeWave.isFinished() && enemies.isEmpty())
				{
						activeWave = null;
						waveNumber++;
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


				combatSystem.update(stepTime, towers, enemies, Bullets);
				economy.update(EnemiesToRemove, TowersToRemove);				
		}

		public void render(double STEP)
		{
				renderSystems.renderBackground(gc, canvas);

				renderSystems.renderUI(gc, economy);

				if ( path != null)
				{
						renderSystems.renderPath(gc, path);
				}
				renderSystems.renderTower(gc, towers, STEP);	

				renderSystems.renderEnemies(gc, enemies, STEP, path);

				renderSystems.renderRemovedEnemies(gc, EnemiesToRemove);

				renderSystems.renderBullets(gc, Bullets);

		}

		public void buyTower(TowerType type, Vector2 position)
		{
				towers.add(towerfactory.create_tower(type, position));	
		}

		public void createEnemy(EnemyType type, Path path)
		{
				enemies.add(enemyspawner.create_enemy(type, path));
		}
		public Wave createWave()
		{
				return waveFactory.create_wave(waveNumber);		
		}

		public Canvas getCanvas()
		{
				return canvas;
		}

}
