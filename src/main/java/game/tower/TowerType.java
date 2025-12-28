package game.tower;

public enum TowerType {

		BASIC(50.0d, 40, 40),
		ADVANCED(60.0d, 50, 60),
		EXPERT(80.0d, 30, 75);

		private final double firerate;
		private final int reichweite;
		private final int damage; 

		TowerType(double firerate, int reichweite, int damage) {
				this.firerate = firerate;
				this.reichweite = reichweite;
				this.damage = damage;
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


}
