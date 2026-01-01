package game.tower;

import util.Vector2;

public class Tower {
		private final TowerType type;
		private final Vector2 position;
		private double cooldown;
				
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

		public TowerType getType() {
				return type;
		}

		public Vector2 getPosition() {
				return position;
		}

		public void reset_cooldown() {
				this.cooldown = type.firerate();
		}

		
}
