package game.tower;

import javafx.scene.image.Image;
import util.Vector2;

public class Tower {
		private final TowerType type;
		private final Vector2 position;
		private double cooldown;
		private  boolean shootAnimationLock = true;
		private int currShootAnimation = 0;
		private double shootAnimationInterval = 5d;
		private double Timer = 0d;
				
		public Tower(TowerType type, Vector2 position) {
				this.type = type;
				this.position = position;
				this.cooldown = 0;
		}

		public void update(double dt) {
				cooldown -= dt;
		}

		public boolean canFire() {
				return cooldown <= 0;
		}

		public void fire() {
				cooldown = type.firerate();
		}
		public double getDamage(){
				return this.type.damage();
		}

		public double getReichweite() {
				return this.type.reichweite();
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
				this.cooldown = type.firerate();
		}

		public boolean getAnimationLock()
		{
				return this.shootAnimationLock;
		}

		public void lockAnimationLock()
		{
				shootAnimationLock = true;
		}

		public void delockAnimationLock()
		{
				shootAnimationLock = false;
		}

		public Image currShootAnimation(double dt)
		{
				Image image = this.type.shootingAnimation().get(currShootAnimation);
				if (nextFrame(dt) == true){
						if (currShootAnimation ==6)
						{
								this.currShootAnimation = 0;
								delockAnimationLock();
						} else{
						currShootAnimation++;
						}
						return image;
				} else {
						return image;
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
}
