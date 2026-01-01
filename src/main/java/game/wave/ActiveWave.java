package game.wave;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import game.enemy.*;


public class ActiveWave {
		private final Wave wave;
		private final Map<EnemyType, Integer> remainingEnemies;
		private double Timer = 0;

		public ActiveWave (Wave wave)
		{
				this.wave = wave;
				this.remainingEnemies = new HashMap<>(wave.getEnemyTypes());
		}

		public EnemyType update(double dt)
		{
				Timer += dt;
		
				if (remainingEnemies.isEmpty())
				{
						return null;
				}

				if (Timer >= wave.getSpawnInterval())
				{
						Timer -= wave.getSpawnInterval();
						EnemyType enemytype = getNextEnemyType();
						decrement(enemytype);
						return enemytype;
				}
				return null;
		}

		private EnemyType getNextEnemyType()
		{
				return remainingEnemies.keySet().iterator().next();
		}
		
		private void decrement(EnemyType type)
		{
				int amount_left = remainingEnemies.get(type) - 1;
				if (amount_left <= 0)
				{
						remainingEnemies.remove(type);
						return;
				}
				remainingEnemies.put(type, amount_left);

		}
		
		public boolean isFinished() {
				return remainingEnemies.isEmpty();
		}






}
