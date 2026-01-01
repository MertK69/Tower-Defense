package game.engine;

import game.enemy.*;
import game.wave.*;
import game.path.*;
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
		private int waveNumber = 1;
		private Path path = null;
	    private	Canvas canvas = new Canvas(900, 600);
        private GraphicsContext gc = canvas.getGraphicsContext2D();
		private TargetingSystem targetingSystem = new TargetingSystem();
        private DamageSystem damageSystem = new DamageSystem();
        private CombatSystem combatSystem = new CombatSystem(targetingSystem, damageSystem);
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

				if ( path == null) path = pathFabric.createPath(Pathtype.EASY);
				if ( activeWave == null ) activeWave = new ActiveWave(waveFactory.create_wave(waveNumber));
		
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
				gc.setFill(Color.BLACK);
                gc.fillRect(0, 0, 900, 600);

                // PATH
                gc.setStroke(Color.DARKGRAY);
                gc.setLineWidth(3);
                gc.strokeLine(50, 300, 850, 300);

                // TOWERS + RANGE
                for (Tower tower : towers) {
                    Vector2 tp = tower.getPosition();

                    gc.setFill(Color.BLUE);
                    gc.fillOval(
                            tp.getX() - 12,
                            tp.getY() - 12,
                            24, 24
                    );

                    gc.setStroke(Color.color(0, 0, 1, 0.25));
                    gc.strokeOval(
                            tp.getX() - tower.getReichweite(),
                            tp.getY() - tower.getReichweite(),
                            tower.getReichweite() * 2,
                            tower.getReichweite() * 2
                    );
                }

                // ENEMIES
                for (Enemy enemy : enemies) {
                    if (!enemy.isAlive()) continue;

                    Vector2 ep = enemy.getPosition();
                    gc.setFill(Color.RED);
                    gc.fillOval(
                            ep.getX() - 8,
                            ep.getY() - 8,
                            16, 16
                    );
				}

		}

		public void buyTower(TowerType type, Vector2 position){
				towers.add(towerfactory.create_tower(type, position));	
		}

		public void createEnemy(EnemyType type, Path path){
				enemies.add(enemyspawner.create_enemy(type, path));
		}

		public Canvas getCanvas() {
				return canvas;
		}

}
