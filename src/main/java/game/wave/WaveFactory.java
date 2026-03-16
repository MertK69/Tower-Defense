package game.wave;

import game.enemy.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class WaveFactory {

		public Wave create_wave(int waveNumber) {
				int enemyCount = waveNumber * 2 + 8;
				int enemyCountCopy = enemyCount;
				double spawnInterval = Math.max(0.2d, 1.5d - waveNumber * 0.1);
				Map<EnemyType, Integer>enemyTypes = new HashMap<>();
				if (waveNumber <= 3 ) {
					enemyTypes.put(EnemyType.Matrose, enemyCountCopy);	
				}
				else if ( waveNumber <= 10 && waveNumber > 3) 
				{
					enemyTypes.put(EnemyType.Matrose, enemyCountCopy / 2);
				    enemyCountCopy -= enemyCountCopy / 2;
					enemyTypes.put(EnemyType.Gefreiter, enemyCountCopy);
				}
				else if ( waveNumber <= 15 && waveNumber > 10)
				{
				    enemyTypes.put(EnemyType.Matrose, enemyCountCopy / 3);
					enemyCountCopy -= enemyCountCopy / 3;
					enemyTypes.put(EnemyType.Gefreiter, enemyCountCopy / 2);
					enemyCountCopy -= enemyCountCopy / 2;
				    enemyTypes.put(EnemyType.Leutnant, enemyCountCopy);
				}
				else if ( waveNumber <= 20 && waveNumber > 15)
				{
				    enemyTypes.put(EnemyType.Matrose, enemyCountCopy / 4);
					enemyCountCopy -= enemyCountCopy / 4;
					enemyTypes.put(EnemyType.Gefreiter, enemyCountCopy / 3);
					enemyCountCopy -= enemyCountCopy / 3;
				    enemyTypes.put(EnemyType.Leutnant, enemyCountCopy / 2);
					enemyCountCopy -= enemyCountCopy / 2;
					enemyTypes.put(EnemyType.Kapitan, enemyCountCopy);
				}
				else if ( waveNumber <= 25 && waveNumber > 20)
				{
				    enemyTypes.put(EnemyType.Matrose, enemyCountCopy / 5);
					enemyCountCopy -= enemyCountCopy / 5;
					enemyTypes.put(EnemyType.Gefreiter, enemyCountCopy / 4);
					enemyCountCopy -= enemyCountCopy / 4;
				    enemyTypes.put(EnemyType.Leutnant, enemyCountCopy / 3);
					enemyCountCopy -= enemyCountCopy / 3;
					enemyTypes.put(EnemyType.Kapitan, enemyCountCopy / 2);
					enemyCountCopy -= enemyCountCopy / 2;
					enemyTypes.put(EnemyType.Kommodore, enemyCountCopy / 2);
					enemyCountCopy -= enemyCountCopy / 2;
					enemyTypes.put(EnemyType.Vizeadmiral, enemyCountCopy);
				}
				else
				{	
				    if (waveNumber >= 50 ) 
						{
						enemyTypes.put(EnemyType.Großadmiral, 1);
						enemyCountCopy -= 1;
						}
				    enemyTypes.put(EnemyType.Matrose, enemyCountCopy / 5);
					enemyCountCopy -= enemyCountCopy / 5;
					enemyTypes.put(EnemyType.Gefreiter, enemyCountCopy / 4);
					enemyCountCopy -= enemyCountCopy / 4;
				    enemyTypes.put(EnemyType.Leutnant, enemyCountCopy / 3);
					enemyCountCopy -= enemyCountCopy / 3;
					enemyTypes.put(EnemyType.Kapitan, enemyCountCopy / 3);
					enemyCountCopy -= enemyCountCopy / 3;
					enemyTypes.put(EnemyType.Kommodore, enemyCountCopy / 2);
					enemyCountCopy -= enemyCountCopy / 2;
					enemyTypes.put(EnemyType.Vizeadmiral, enemyCountCopy);
					enemyCountCopy -= enemyCountCopy / 2;
					enemyTypes.put(EnemyType.Admiral, enemyCountCopy);
				}
				return new Wave(enemyCount, spawnInterval, enemyTypes);
		}

}
