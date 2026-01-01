package game.wave;

import game.enemy.Enemy;
import game.enemy.EnemySpawner;


public class WaveSpawner {
		private final Wave wave;
		private int spawned;
		private double timer;


		public WaveSpawner(Wave wave){		
				this.wave = wave;
		}

		public boolean isFinished() {
				return spawned >= wave.getEnemyCount();
		}

		public boolean update(double dt) {
				timer += dt;

				if (timer >= wave.getSpawnInterval() && !isFinished()) {
						timer -= wave.getSpawnInterval();
						spawned++;
						return true;
				}else{
						return false;
				}

		}
		
}
