package game.economy;

import game.path.Path;
import game.path.Pathtype;
import java.util.List;
import game.enemy.*;
import game.tower.*;

public class Economy {
		public EconomySystems economySystems;
		private int curr_money;
		

		public Economy(EconomySystems economySystems, Pathtype difficulty)
		{
				this.economySystems = economySystems;
				if (difficulty == Pathtype.EASY) this.curr_money = 500;
				if (difficulty == Pathtype.MEDIUM) this.curr_money = 750;
				if (difficulty == Pathtype.MEDIUM) this.curr_money = 1000;
				if (difficulty == Pathtype.IMPOSSIBLE) this.curr_money = 1500;
		}

		public void update(List<Enemy>removedEnemies, List<Tower>soldTowers)
		{
				curr_money += economySystems.addMoneyFromDefeatedEnemy(removedEnemies);
				curr_money += economySystems.addMoneyFromSoldTowers(soldTowers);
		}

		public int getCurr_Money()
		{
				return curr_money;
		}

		public void towersBought(TowerType type)
		{
				curr_money -= economySystems.withdrawMoneyFromBoughtTowers(type);
		}
}
