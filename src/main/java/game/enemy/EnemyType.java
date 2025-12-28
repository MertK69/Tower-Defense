package game.enemy;

public enum EnemyType {
		
		// Enum Const
		
		Matrose(10.0d, 20.0d, false, false),
		Gefreiter(15.0d, 20.0d, false, false),
		Leutnant(25.0d, 25.0d, false, false),
		Kapitan(30.0d, 30.0d, true, false),
		Kommodore(35.0d, 35.0d, true, false),
		Vizeadmiral(60.0d, 30.0d, false, true),
		Admiral(75.0d, 50.0d, false, true),
		Großadmiral(90.0d, 60.0d, false, true);
		
		private double hp;
		private double speed;
		private boolean can_explode;
		private boolean can_summon;

		EnemyType(double hp, double speed, boolean can_explode, boolean can_summon) {
				this.hp = hp; this.speed = speed; 
				this.can_explode = can_explode; this.can_summon = can_summon;

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



}
