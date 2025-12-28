package game.enemy;

import util.Vector2;

public class Enemy {
		private Vector2 position;
		private EnemyType type;
		private double current_hp;


		public Enemy(EnemyType type, Vector2 position ) {
				this.type = type;
				this.position = position;
				this.current_hp = type.hp();
		}

		public void update(double dt) {
				

		}

		public double speed() {
				return type.speed();
		}
				
		public EnemyType getType() {
				return this.type;
		}

		public Vector2 getPosition() {
				return this.position;
		}

		public double getCurrent_hp() {
				return this.current_hp;
		}
		


}
