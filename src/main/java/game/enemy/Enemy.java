package game.enemy;

import util.Vector2;
import game.path.Path;
import javafx.scene.image.Image;

public class Enemy {
		private Vector2 position;
		private EnemyType type;
		private double current_hp;
		private int curr_waypoint;
		private Path path;
		private int curr_Movement = 0;
		private double movementInterval;
		private double timer = 0;

		public Enemy(EnemyType type, Path path ) {
				this.type = type;
				this.position = path.get_waypoint(0);
				this.current_hp = type.hp();
				this.curr_waypoint = 0;
				this.path = path;
				this.movementInterval = type.movementInterval();
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

        public void __set_Position__(double X, double Y)
        {
                this.position.set_Position(X, Y);
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

		private void nextMovement() 
		{
				if (curr_Movement == type.movementLeft().size() - 1)
				{
						curr_Movement = 0;
				} else {
						curr_Movement++;
				}
				timer = 0;
		}
		public Image getCurrMovement(double dt, int direction) // directions 1 == Right, 2 == left, 3 == Up, 4 == Down
		{
				Image image = null;
				if (direction == 1) image = type.movementLeft().get(curr_Movement);
				if (direction == 2) image = type.movementRight().get(curr_Movement);
				if (direction == 3) image = type.movementUpwards().get(curr_Movement);
				if (direction == 4) image = type.movementDownwards().get(curr_Movement);
				timer += dt;
				if (timer >= movementInterval) nextMovement();
				return image;
		}

		public int getCurrWaypoint()
		{
				return curr_waypoint;
		}
		


}
