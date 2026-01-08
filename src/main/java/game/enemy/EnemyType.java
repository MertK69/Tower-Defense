package game.enemy;

import java.util.*;
import javafx.scene.image.Image;
import game.animation.enemyAnimationen.*;
public enum EnemyType {
		// Enum Const
		
		Matrose(10.0d, 20.0d, false, false, 
						SLVL1.loadWalking("/images/s-lvl1-walk-right/s-lvl1-walk-right", 6),
						SLVL1.loadWalking("/images/s-lvl1-walk-left/s-lvl1-walk-left", 6),
						SLVL1.loadWalking("/images/s-lvl1-walk-down/s-lvl1-walk-down", 6),
						SLVL1.loadWalking("/images/s-lvl1-walk-up/s-lvl1-walk-up", 6), 0.2d),

		Gefreiter(70.0d, 20.0d, false, false, 
						SLVL1.loadWalking("/images/s-lvl2-walk-right/s-lvl2-walk-right", 6),
						SLVL1.loadWalking("/images/s-lvl2-walk-left/s-lvl2-walk-left", 6),
						SLVL1.loadWalking("/images/s-lvl2-walk-down/s-lvl2-walk-down", 6),
						SLVL1.loadWalking("/images/s-lvl2-walk-up/s-lvl2-walk-up", 6), 0.2d),

		Leutnant(105.0d, 25.0d, false, false,
						SLVL1.loadWalking("/images/s-lvl1-walk-right/s-lvl1-walk-right", 6),
						SLVL1.loadWalking("/images/s-lvl1-walk-left/s-lvl1-walk-left", 6),
						SLVL1.loadWalking("/images/s-lvl1-walk-down/s-lvl1-walk-down", 6),
						SLVL1.loadWalking("/images/s-lvl1-walk-up/s-lvl1-walk-oben", 6), 0.2d),	

		Kapitan(120.0d, 30.0d, true, false, 
						SLVL1.loadWalking("/images/s-lvl1-walk-right/s-lvl1-walk-right", 6),
						SLVL1.loadWalking("/images/s-lvl1-walk-left/s-lvl1-walk-left", 6),
						SLVL1.loadWalking("/images/s-lvl1-walk-down/s-lvl1-walk-down", 6),
						SLVL1.loadWalking("/images/s-lvl1-walk-up/s-lvl1-walk-oben", 6), 0.2d),

		Kommodore(220.0d, 35.0d, true, false, 
						SLVL1.loadWalking("/images/s-lvl1-walk-right/s-lvl1-walk-right", 6),
						SLVL1.loadWalking("/images/s-lvl1-walk-left/s-lvl1-walk-left", 6),
						SLVL1.loadWalking("/images/s-lvl1-walk-down/s-lvl1-walk-down", 6),
						SLVL1.loadWalking("/images/s-lvl1-walk-up/s-lvl1-walk-oben", 6), 0.2d),

		Vizeadmiral(300.0d, 30.0d, false, true, 
						SLVL1.loadWalking("/images/s-lvl1-walk-right/s-lvl1-walk-right", 6),
						SLVL1.loadWalking("/images/s-lvl1-walk-left/s-lvl1-walk-left", 6),
						SLVL1.loadWalking("/images/s-lvl1-walk-down/s-lvl1-walk-down", 6),
						SLVL1.loadWalking("/images/s-lvl1-walk-up/s-lvl1-walk-oben", 6), 0.2d),

		Admiral(450.0d, 50.0d, false, true, 
						SLVL1.loadWalking("/images/s-lvl1-walk-right/s-lvl1-walk-right", 6),
						SLVL1.loadWalking("/images/s-lvl1-walk-left/s-lvl1-walk-left", 6),
						SLVL1.loadWalking("/images/s-lvl1-walk-down/s-lvl1-walk-down", 6),
						SLVL1.loadWalking("/images/s-lvl1-walk-up/s-lvl1-walk-oben", 6), 0.2d),

		Großadmiral(1000.0d, 60.0d, false, true,
						SLVL1.loadWalking("/images/s-lvl1-walk-right/s-lvl1-walk-right", 6),
						SLVL1.loadWalking("/images/s-lvl1-walk-left/s-lvl1-walk-left", 6),
						SLVL1.loadWalking("/images/s-lvl1-walk-down/s-lvl1-walk-down", 6),
						SLVL1.loadWalking("/images/s-lvl1-walk-up/s-lvl1-walk-oben", 6), 0.2d);	

		private double hp;
		private double speed;
		private boolean can_explode;
		private boolean can_summon;
		private List<Image>movementRight;
		private List<Image>movementLeft;
		private List<Image>movementUpwards;
		private List<Image>movementDownwards;
		private double movementInterval;EnemyType(double hp, double speed, boolean can_explode, boolean can_summon, List<Image>movementRight,List<Image>movementLeft, 								  List<Image>movementUpwards, List<Image>movementDownwards, double movementInterval) {
				this.hp = hp; 
				this.speed = speed; 
				this.can_explode = can_explode; 
				this.can_summon = can_summon;
				this.movementRight = movementRight;
				this.movementLeft = movementLeft;
				this.movementUpwards = movementUpwards;
				this.movementDownwards = movementDownwards;
				this.movementInterval = movementInterval;

		}

		public double hp(){
				return this.hp;
		}

		public double speed() {
				return this.speed;
		}

		public boolean can_summon() {
				return this.can_summon;
		}

		public boolean can_explode() {
				return this.can_explode;
		}

		public List<Image>movementRight() 
		{
				return this.movementRight;
		}

		public List<Image>movementLeft() 
		{
				return this.movementLeft;
		}

		public List<Image>movementUpwards() 
		{
				return this.movementUpwards;
		}

		public List<Image>movementDownwards() 
		{
				return this.movementDownwards;
		}

		public double movementInterval()
		{
				return this.movementInterval;
		}


}
