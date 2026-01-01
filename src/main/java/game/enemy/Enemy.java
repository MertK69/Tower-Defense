package game.enemy;

import util.Vector2;
import game.path.Path;

public class Enemy {
		private Vector2 position;
		private EnemyType type;
		private double current_hp;
		private int curr_waypoint;
		private Path path;


		public Enemy(EnemyType type, Path path ) {
				this.type = type;
				this.position = path.get_waypoint(0);
				this.current_hp = type.hp();
				this.curr_waypoint = 0;
				this.path = path;
		}

		public void update(double dt) {
				Vector2 TargetWaypoint = this.path.get_waypoint(curr_waypoint);
				Vector2 direction = this.position.direction_to(TargetWaypoint);
				double distance = direction.Vector_length();
				Vector2 normed_dir = direction.Normierung();
				double factor = this.speed() * dt;
				normed_dir = normed_dir.Scale(factor);
				if (factor >= distance) {
						this.position = TargetWaypoint;
						this.curr_waypoint++;
				} else{
				this.position = this.position.Addition(normed_dir);
				}
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

		public boolean isFinished() {
				if (this.curr_waypoint == path.get_waypoint_amount()){
						return true;
						} else {
								return false;
				}
		}

		public boolean isAlive() {
				if (this.current_hp > 0) return true; else return false;
		}

		public void get_damage(double damage) {
				if (this.current_hp - damage <= 0d) {
						this.current_hp = 0d;
						return;
				}
				this.current_hp = this.current_hp - damage;
		}
		


}
