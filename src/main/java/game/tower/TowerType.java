package game.tower;

public enum TowerType {

		BASIC(50.0d, 40, 40, 150),
		ADVANCED(60.0d, 50, 60, 300),
		EXPERT(80.0d, 30, 75, 650);

		private final double firerate;
		private final int reichweite;
		private final int damage;
		private final int price;

		TowerType(double firerate, int reichweite, int damage, int price) {
				this.firerate = firerate;
				this.reichweite = reichweite;
				this.damage = damage;
				this.price = price;
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


}
