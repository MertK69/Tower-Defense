package game.tower;

import java.util.List;

import game.animation.enemyAnimationen.SLVL1;
import javafx.scene.image.Image;

public enum TowerType {

		BASIC(80.0d, 40, 40, 150, 
						SLVL1.loadImage("/images/static-images/lvl1-turret"),
						SLVL1.loadWalking("/images/lvl1-turret-fight/lvl1-turret-fight", 8)
						),
		ADVANCED(60.0d, 50, 60, 300,
						SLVL1.loadImage("/images/static-images/lvl2-turret"),
						SLVL1.loadWalking("/images/lvl2-turret-fight/lvl2-turret-fight", 8)
						),
		EXPERT(40.0d, 80, 75, 650,
						SLVL1.loadImage("/images/static-images/lvl3-turret"),
						SLVL1.loadWalking("/images/lvl3-turret-fight/lvl3-turret-fight", 8)	
						);
		private final double firerate;
		private final int reichweite;
		private final int damage;
		private final int price;
		private final Image image;
		private final List<Image>shootingAnimation;

		TowerType(double firerate, int reichweite, int damage, int price,Image image, List<Image>shootingAnimation) {
				this.firerate = firerate;
				this.reichweite = reichweite;
				this.damage = damage;
				this.price = price;
				this.image = image;
				this.shootingAnimation = shootingAnimation;
		}

		public double firerate() {
				return this.firerate;
		}

		public int reichweite() {
				return this.reichweite;
		}

		public int damage() {
				return this.damage;
		}

		public int price()
		{
				return this.price;
		}

		public Image image()
		{
				return this.image;
		}

		public List<Image>shootingAnimation()
		{
				return this.shootingAnimation;
		}


}
