package game.economy;

import game.path.Path;
import game.path.Pathtype;
import game.sattack.SpecialAttack;
import game.sattack.SpecialAttackType;

import java.util.List;
import game.enemy.*;
import game.tower.*;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Economy {
		public EconomySystems economySystems;
        private IntegerProperty curr_money = new SimpleIntegerProperty();

		public Economy(EconomySystems economySystems, Pathtype difficulty)
		{
				this.economySystems = economySystems;
				this.curr_money.set(startingMoneyFor(difficulty));
		}

		// Reproduces the exact net effect of the original (pre-existing) branch logic:
		// MEDIUM was checked twice in the constructor (750 then 1000 - the second write
		// wins, net 1000) and HARD was never handled (falls through, net 0). Both quirks
		// are intentionally preserved here, not fixed - see plan.md Edge Cases.
		private int startingMoneyFor(Pathtype difficulty)
		{
				int money = 0;
				if (difficulty == Pathtype.EASY) money = 80000;
				if (difficulty == Pathtype.MEDIUM) money = 750;
				if (difficulty == Pathtype.MEDIUM) money = 1000;
				if (difficulty == Pathtype.IMPOSSIBLE) money = 1500;
				return money;
		}

		public void reset(Pathtype difficulty)
		{
				this.curr_money.set(startingMoneyFor(difficulty));
		}

		public void update(List<Enemy>removedEnemies, List<Tower>soldTowers)
		{
				curr_money.set(curr_money.get() + economySystems.addMoneyFromDefeatedEnemy(removedEnemies));
				curr_money.set(curr_money.get() + economySystems.addMoneyFromSoldTowers(soldTowers));
		}

		public IntegerProperty Money_Property()
		{
				return this.curr_money;
		}

        public int getMoney()
        {
                return this.curr_money.get();
        }

		public void towersBought(TowerType type)
		{
				curr_money.set(curr_money.get() - economySystems.withdrawMoneyFromBoughtTowers(type));
		}

		public void towerUpgraded(Tower tower)
		{
				curr_money.set(curr_money.get() - economySystems.withdrawMoneyFromUpgrade(tower));
		}

        public void specialEffectsBought(SpecialAttackType type)
        {
                curr_money.set(curr_money.get() - economySystems.withdrawMoneyFromSpecialEffects(type));
        }

}
