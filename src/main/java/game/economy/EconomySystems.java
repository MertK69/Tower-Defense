package game.economy;

import java.util.List;
import game.enemy.*;
import game.tower.*;
public class EconomySystems {
		private EarningsSystems earningsSystems;
		private SpendingsSystems spendingsSystems;

		public EconomySystems(EarningsSystems earningsSystems, SpendingsSystems spendingsSystems)
		{
				this.earningsSystems = earningsSystems;
				this.spendingsSystems = spendingsSystems;
		}

		public int addMoneyFromDefeatedEnemy(List<Enemy>removedEnemies)
		{
				return earningsSystems.addMoneyFromDefeatedEnemy(removedEnemies);
		}

		public int addMoneyFromSoldTowers(List<Tower>soldTowers)
		{
				return earningsSystems.addMoneyFromSoldTowers(soldTowers);
		}



}
