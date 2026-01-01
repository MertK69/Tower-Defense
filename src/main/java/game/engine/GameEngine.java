package game.engine;

import game.enemy.*;
import game.wave.*;
import game.path.*;
import game.render.RenderSystems;
import game.render.*;
import game.tower.Tower;
import game.tower.TowerFactory;
import game.tower.TowerType;
import game.combat.*;
import game.wave.*;
import util.Vector2;
import java.util.ArrayList;
import java.util.List;
import game.path.*;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;



public class GameEngine {
		private final List<Enemy>enemies = new ArrayList<>();
		private final List<Tower>towers = new ArrayList<>();
		private TowerFactory towerfactory = new TowerFactory();
		private EnemySpawner enemyspawner = new EnemySpawner();
		private WaveFactory waveFactory = new WaveFactory();
		private PathFabric pathFabric = new PathFabric();
		private ActiveWave activeWave = null;
		private int waveNumber = 25;
		private Path path = null;
	    private	Canvas canvas = new Canvas(1200, 800);
        private GraphicsContext gc = canvas.getGraphicsContext2D();
		private TargetingSystem targetingSystem = new TargetingSystem();
        private DamageSystem damageSystem = new DamageSystem();
        private CombatSystem combatSystem = new CombatSystem(targetingSystem, damageSystem);
		private TowerRenderer towerRenderer = new TowerRenderer();
		private EnemyRenderer enemyRenderer = new EnemyRenderer();
		private PathRenderer pathRenderer = new PathRenderer();
		private RenderSystems renderSystems = new RenderSystems(towerRenderer, enemyRenderer, pathRenderer);
		public void update(double stepTime) 
		{
				if ( path == null ) 
				{
						Tower tower1 = new Tower(TowerType.BASIC,    new Vector2(500, 270));
						Tower tower2 = new Tower(TowerType.ADVANCED, new Vector2(450, 270));
						Tower tower3 = new Tower(TowerType.BASIC,    new Vector2(400, 270));
						Tower tower4 = new Tower(TowerType.ADVANCED, new Vector2(350, 270));
						towers.add(tower1);	towers.add(tower2); towers.add(tower3); towers.add(tower4);
				}

				if ( path == null) path = pathFabric.createPath(Pathtype.Impossible);
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
				List<Enemy>ToRemove = new ArrayList<>();
				for (Enemy enemy : enemies)
				{
						
						enemy.update(stepTime);
						if (!enemy.isAlive() || enemy.isFinished())
						{
								ToRemove.add(enemy);
								continue;
						}

				}
				enemies.removeAll(ToRemove);

				for (Tower tower : towers)
				{
						tower.update(stepTime);
				}
				combatSystem.update(stepTime, towers, enemies);

		}

		public void render()
		{
				gc.setFill(Color.GREEN);
                gc.fillRect(0, 0, 1200, 800);
				if ( path != null)
				{
						renderSystems.renderPath(gc, path);
				}
				renderSystems.renderTower(gc, towers);	

				renderSystems.renderEnemies(gc, enemies);

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
				return waveFactory.create_wave(waveNumber);		}

		public Canvas getCanvas()
		{
				return canvas;
		}

}
