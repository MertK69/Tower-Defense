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
						Money += X.getType().get_Value();
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
