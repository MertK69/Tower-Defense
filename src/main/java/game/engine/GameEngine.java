package game.engine;

import game.enemy.*;
import game.wave.*;
import game.path.*;
import game.tower.Tower;
import game.tower.TowerFactory;
import game.tower.TowerType;
import game.wave.*;
import util.Vector2;
import java.util.ArrayList;
import java.util.List;
import game.path.*;


public class GameEngine {
		private final List<Enemy>enemies = new ArrayList<>();
		private final List<Tower>towers = new ArrayList<>();
		private TowerFactory towerfactory = new TowerFactory();
		private EnemySpawner enemyspawner = new EnemySpawner();
		private WaveFactory waveFactory = new WaveFactory();
		private ActiveWave activeWave = null;
		private int waveNumber = 1;
		private Path path;
		public void update(double stepTime) 
		{
				if ( activeWave == null ) activeWave = new ActiveWave(waveFactory.create_wave(waveNumber));
		
				EnemyType spawnType = activeWave.update(stepTime);
				
				if (spawnType != null)
				{
						createEnemy(spawnType, path);
				}

				if (activeWave.isFinished())
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
		}

		public void render()
		{

		}

		public void buyTower(TowerType type, Vector2 position){
				towers.add(towerfactory.create_tower(type, position));	
		}

		public void createEnemy(EnemyType type, Path path){
				enemies.add(enemyspawner.create_enemy(type, path));
		}

}
