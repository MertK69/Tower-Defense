package game.enemy;

public enum EnemyType {
		
		Matrose(10.0d, 20.0d, false, false, 
						"/images/s-lvl1-walk-right/s-lvl1-walk-right",
						"/images/s-lvl1-walk-left/s-lvl1-walk-left", 
						"/images/s-lvl1-walk-down/s-lvl1-walk-down",
						"/images/s-lvl1-walk-up/s-lvl1-walk-up", 6, 0.2d),

		Gefreiter(70.0d, 20.0d, false, false, 
						"/images/s-lvl2-walk-right/s-lvl2-walk-right",
						"/images/s-lvl2-walk-left/s-lvl2-walk-left",
						"/images/s-lvl2-walk-down/s-lvl2-walk-down",
						"/images/s-lvl2-walk-up/s-lvl2-walk-up", 6, 0.2d),

		Leutnant(105.0d, 25.0d, false, false,
						"/images/s-lvl1-walk-right/s-lvl1-walk-right",
						"/images/s-lvl1-walk-left/s-lvl1-walk-left",
						"/images/s-lvl1-walk-down/s-lvl1-walk-down",
						"/images/s-lvl1-walk-up/s-lvl1-walk-up", 6, 0.2d),	

		Kapitan(120.0d, 30.0d, true, false, 
						"/images/s-lvl1-walk-right/s-lvl1-walk-right",
						"/images/s-lvl1-walk-left/s-lvl1-walk-left",
						"/images/s-lvl1-walk-down/s-lvl1-walk-down",
						"/images/s-lvl1-walk-up/s-lvl1-walk-up", 6, 0.2d),

		Kommodore(220.0d, 35.0d, true, false, 
						"/images/s-lvl1-walk-right/s-lvl1-walk-right",
						"/images/s-lvl1-walk-left/s-lvl1-walk-left",
						"/images/s-lvl1-walk-down/s-lvl1-walk-down",
						"/images/s-lvl1-walk-up/s-lvl1-walk-up", 6, 0.2d),

		Vizeadmiral(300.0d, 30.0d, false, true, 
						"/images/s-lvl1-walk-right/s-lvl1-walk-right",
						"/images/s-lvl1-walk-left/s-lvl1-walk-left",
						"/images/s-lvl1-walk-down/s-lvl1-walk-down",
						"/images/s-lvl1-walk-up/s-lvl1-walk-up", 6, 0.2d),

		Admiral(450.0d, 50.0d, false, true, 
						"/images/s-lvl1-walk-right/s-lvl1-walk-right",
						"/images/s-lvl1-walk-left/s-lvl1-walk-left",
						"/images/s-lvl1-walk-down/s-lvl1-walk-down",
						"/images/s-lvl1-walk-up/s-lvl1-walk-up", 6, 0.2d),

		Großadmiral(1000.0d, 60.0d, false, true,
						"/images/s-lvl1-walk-right/s-lvl1-walk-right",
						"/images/s-lvl1-walk-left/s-lvl1-walk-left",
						"/images/s-lvl1-walk-down/s-lvl1-walk-down",
						"/images/s-lvl1-walk-up/s-lvl1-walk-up", 6, 0.2d);	

		private double hp;
		private double speed;
		private boolean can_explode;
		private boolean can_summon;
        private final String movementRightPath;
		private final String movementLeftPath;
        private final String movementUpwardsPath;
        private final String movementDownwardsPath;
        private final int movementFrameCount;
		private double movementInterval;
        EnemyType(double hp, double speed, boolean can_explode, boolean can_summon, String movementRightPath, String movementLeftPath, String movementUpwardsPath, String movementDownwardsPath, int movementFrameCount, double movementInterval) {
				this.hp = hp; 
				this.speed = speed; 
				this.can_explode = can_explode; 
				this.can_summon = can_summon;
				this.movementRightPath = movementRightPath;
				this.movementLeftPath = movementLeftPath;
				this.movementUpwardsPath = movementUpwardsPath;
				this.movementDownwardsPath = movementDownwardsPath;
                this.movementFrameCount = movementFrameCount;
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

		public double movementInterval()
		{
				return this.movementInterval;
		}

        public String get_leftPath()
        {
                return this.movementLeftPath;
        }

        public String get_rightPath()
        {
                return this.movementRightPath;
        }

        public String get_upwardsPath()
        {
                return this.movementUpwardsPath;
        }

        public String get_downwardsPath()
        {
                return this.movementDownwardsPath;
        }

        public int get_frameCount()
        {
                return this.movementFrameCount;
        }
}
