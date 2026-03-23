package game.tower;

public enum TowerType {

		BASIC(20.0d, 60, 0, 3, 150, 
						"/images/static-images/lvl1-turret",
						"/images/lvl1-turret-fight/lvl1-turret-fight", 8
						),
		ADVANCED(15.0d, 70, 0, 5, 300,
						"/images/static-images/lvl2-turret",
						"/images/lvl2-turret-fight/lvl2-turret-fight", 8
						),
		EXPERT(10.0d, 90, 0, 15, 650,
						"/images/static-images/lvl3-turret",
						"/images/lvl3-turret-fight/lvl3-turret-fight", 8	
						),
        RAYBASIC(25.0d, 70, 0, 110, 850,
						"/images/static-images/lvl4-turret",
						"/images/lvl4-turret-fight/lvl4-turret-fight", 11	
						),
        RAYADVANCED(20.0d, 100, 0, 75, 1100,
						"/images/static-images/lvl5-turret",
						"/images/lvl5-turret-fight/lvl5-turret-fight", 11	
						),
        RAYEXPERT(5.0d, 120, 0, 40, 1350,
						"/images/static-images/lvl6-turret",
						"/images/lvl6-turret-fight/lvl6-turret-fight", 11	
						),
        ROCKETLAUNCHERBASIC(450.0d, 800, 200, 0, 2500, 
                        "/images/static-images/rocket-launcher",
                        "/images/lvl1-rocket-launch/lvl1-rocket-launch", 12
                        ),
        ROCKETLAUNCHERADVANCED(250.0d, 1200, 300, 0, 4000,
                        "/images/static-images/rocket-launcher2",
                        "/images/lvl2-rocket-launch/lvl2-rocket-launch", 12
                        ),
        ROCKETLAUNCHEREXPERT(150.0d, 1200, 400, 0, 6500,
                        "/images/static-images/rocket-launcher3",
                        "/images/lvl3-rocket-launch/lvl3-rocket-launch", 17
                        );

		private final double firerate;
		private final int reichweite;
        private final int negativReichweite;
		private final int damage;
		private final int price;
		private final String imagePath;
        private final String framePath;
        private final int frameCount;

		TowerType(double firerate, int reichweite,int negativReichweite, int damage, int price,String imagePath, String framePath, int frameCount) {
				this.firerate = firerate;
				this.reichweite = reichweite;
                this.negativReichweite = negativReichweite;
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

        public int get_negativReichweite()
        {
                return this.negativReichweite;
        }


}
