package game.tower;

import javafx.scene.image.Image;
import util.Vector2;

public class Tower {
		public static final int MAX_LEVEL = 3;
		private static final double UPGRADE_COST_FACTOR = 0.5;
		private static final double STAT_MULTIPLIER_PER_LEVEL = 0.25;
		private static final double SELL_REFUND_RATE = 0.7;

		private final TowerType type;
		private final Vector2 position;
		private double cooldown;
		private  boolean shootAnimationLock = true;
		private int currShootAnimation = 0;
		private double shootAnimationInterval = 5d;
		private double Timer = 0d;
	    private double currentAngle = -90d;
        private boolean doFireSound = false;
		private int level = 1;
		private final int baseCost;
		private int totalInvestedGold;
		public Tower(TowerType type, Vector2 position) {
				this.type = type;
				this.position = position;
				this.cooldown = 0;
				this.baseCost = type.price();
				this.totalInvestedGold = this.baseCost;
		}

		public void update(double dt) {
				cooldown -= dt;
		}

		public boolean canFire() {
				return cooldown <= 0;
		}

		public void fire() {
				cooldown = getEffectiveFirerate();
		}
		public double getDamage(){
				return this.type.damage() * getStatMultiplier();
		}

		public double getReichweite() {
				return this.type.reichweite() * getStatMultiplier();
		}

		public int getPrice()
		{
				return this.type.price();
		}


		public TowerType getType() {
				return type;
		}

		public Vector2 getPosition() {
				return position;
		}

		public void reset_cooldown() {
				this.cooldown = getEffectiveFirerate();
		}

		public int getLevel() {
				return level;
		}

		public boolean isMaxLevel() {
				return level >= MAX_LEVEL;
		}

		public int getBaseCost() {
				return baseCost;
		}

		public int getTotalInvestedGold() {
				return totalInvestedGold;
		}

		public int getUpgradeCost() {
				return (int) (baseCost * UPGRADE_COST_FACTOR * level);
		}

		public void upgrade() {
				if (isMaxLevel()) return;
				totalInvestedGold += getUpgradeCost();
				level++;
		}

		public int getSellValue() {
				return (int) (totalInvestedGold * SELL_REFUND_RATE);
		}

		private double getStatMultiplier() {
				return 1.0 + STAT_MULTIPLIER_PER_LEVEL * (level - 1);
		}

		private double getEffectiveFirerate() {
				return this.type.firerate() / getStatMultiplier();
		}

		public boolean getAnimationLock()
		{
				return this.shootAnimationLock;
		}

		public void lockAnimationLock()
		{
				shootAnimationLock = true;
                this.doFireSound = true;
		}

		public void delockAnimationLock()
		{
				shootAnimationLock = false;
		}

		public int currShootAnimation(double dt)
		{
				if (nextFrame(dt) == true){
						if (currShootAnimation == type.get_frameCount() - 2)
						{
								this.currShootAnimation = 0;
								delockAnimationLock();
						} else{
						currShootAnimation++;
						}
						return currShootAnimation;
				} else {
						return currShootAnimation;
				}
		}

		public boolean nextFrame(double dt)
		{
				Timer += dt;
				if (Timer >= shootAnimationInterval )
				{
						Timer -= dt;
						return true;
				} else {
						return false;
						}
		}

		public void calculateAngle(Vector2 enemy)
		{
				Vector2 Direction = position.direction_to(enemy);

				this.currentAngle = Math.toDegrees(Math.atan2(Direction.getY(), Direction.getX()));
		}

		public double getAngle()
		{
				return this.currentAngle + 90d;
		}

        public void set_fireSound()
        {
            this.doFireSound = false;
        }

        public boolean get_fireSound()
        {
            return this.doFireSound;
        }

        public int get_NegativReichweite()
        {
            return this.type.get_negativReichweite();
        }
}
