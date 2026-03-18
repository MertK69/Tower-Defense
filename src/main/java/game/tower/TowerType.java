package game.tower;

public enum TowerType {

		BASIC(80.0d, 60, 40, 150, 
						"/images/static-images/lvl1-turret",
						"/images/lvl1-turret-fight/lvl1-turret-fight", 8
						),
		ADVANCED(60.0d, 70, 60, 300,
						"/images/static-images/lvl2-turret",
						"/images/lvl2-turret-fight/lvl2-turret-fight", 8
						),
		EXPERT(40.0d, 90, 75, 650,
						"/images/static-images/lvl3-turret",
						"/images/lvl3-turret-fight/lvl3-turret-fight", 8	
						),
        RAYBASIC(40.0d, 90, 75, 150,
						"/images/static-images/lvl4-turret",
						"/images/lvl4-turret-fight/lvl4-turret-fight", 11	
						),
        RAYADVANCED(40.0d, 90, 75, 150,
						"/images/static-images/lvl5-turret",
						"/images/lvl5-turret-fight/lvl5-turret-fight", 11	
						),
        RAYEXPERT(40.0d, 90, 75, 150,
						"/images/static-images/lvl6-turret",
						"/images/lvl6-turret-fight/lvl6-turret-fight", 11	
						);


		private final double firerate;
		private final int reichweite;
		private final int damage;
		private final int price;
		private final String imagePath;
        private final String framePath;
        private final int frameCount;

		TowerType(double firerate, int reichweite, int damage, int price,String imagePath, String framePath, int frameCount) {
				this.firerate = firerate;
				this.reichweite = reichweite;
				this.damage = damage;
				this.price = price;
				this.imagePath = imagePath;
                this.framePath = framePath;
                this.frameCount = frameCount;
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

		public String get_imagePath()
		{
				return this.imagePath; 
		}

		public String get_shootingAnimationPath()
		{
				return this.framePath; 
		}

        public int get_frameCount()
        {
                return this.frameCount;
        }


}
