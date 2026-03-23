package game.economy;

import java.util.List;
import game.enemy.*;
import game.sattack.SpecialAttackType;
import game.tower.*;
public class EconomySystems {
		private EarningsSystems earningsSystems = new EarningsSystems();
		private SpendingsSystems spendingsSystems = new SpendingsSystems();

		public EconomySystems()
		{
		}

		public int addMoneyFromDefeatedEnemy(List<Enemy>removedEnemies)
		{
				return earningsSystems.addMoneyFromDefeatedEnemy(removedEnemies);
		}

		public int addMoneyFromSoldTowers(List<Tower>soldTowers)
		{
				return earningsSystems.addMoneyFromSoldTowers(soldTowers);
		}

		public int withdrawMoneyFromBoughtTowers(TowerType type)
		{
				return spendingsSystems.buyTower(type);
		}

        public int withdrawMoneyFromSpecialEffects(SpecialAttackType type)
        {
                return spendingsSystems.buySpecialEffect(type);
        }
}
