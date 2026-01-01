package game.wave;

import java.util.HashMap;
import java.util.Map;

import game.enemy.*;

public class Wave {
		private final int enemyCount;
		private final double spawnInterval;
		private final Map<EnemyType, Integer>enemyTypes;


		public Wave(int enemyCount, double spawnInterval, Map<EnemyType, Integer>enemyTypes) {
				this.enemyCount = enemyCount;
				this.spawnInterval = spawnInterval;
				this.enemyTypes = Map.copyOf(enemyTypes);
		}

		public int getEnemyCount() {
				return this.enemyCount;
		}

		public double getSpawnInterval() {
				return this.spawnInterval;
		}

		public Map<EnemyType, Integer> getEnemyTypes() {
				return enemyTypes;
		}





}
