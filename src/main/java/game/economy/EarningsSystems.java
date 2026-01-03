package game.economy;


import java.util.List;
import game.enemy.*;
import game.tower.*;

public class EarningsSystems{

		public EarningsSystems()
		{}


		public int addMoneyFromDefeatedEnemy(List<Enemy>removedEnemies)
		{
				int Money = 0;
				for (Enemy X : removedEnemies)
				{
						if (X.getType() == EnemyType.Matrose) Money += 10;
						if (X.getType() == EnemyType.Gefreiter) Money += 15;
						if (X.getType() == EnemyType.Leutnant) Money += 20;
						if (X.getType() == EnemyType.Kommodore) Money += 25;
						if (X.getType() == EnemyType.Kapitan) Money += 35;
						if (X.getType() == EnemyType.Vizeadmiral) Money += 50;
						if (X.getType() == EnemyType.Admiral) Money += 70;
						if (X.getType() == EnemyType.Großadmiral) Money += 100;
				}
				return Money;
		}

		public int addMoneyFromSoldTowers(List<Tower>soldTowers)
		{
				int Money = 0;
				for (Tower X : soldTowers)
				{
						Money += X.getPrice();	
				}
				return Money;
		}





}
