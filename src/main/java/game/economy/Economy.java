package game.economy;

import game.path.Path;
import game.path.Pathtype;
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
				if (difficulty == Pathtype.EASY) this.curr_money.set(500);
				if (difficulty == Pathtype.MEDIUM) this.curr_money.set(750);
				if (difficulty == Pathtype.MEDIUM) this.curr_money.set(1000);
				if (difficulty == Pathtype.IMPOSSIBLE) this.curr_money.set(1500);
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

}
