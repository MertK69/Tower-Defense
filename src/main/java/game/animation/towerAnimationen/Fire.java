package game.animation.towerAnimationen;

import javafx.scene.image.Image;
import util.Vector2;
import game.animation.enemyAnimationen.SLVL1;

public class Fire{
		private Image firePic = SLVL1.loadImage("/images/static-images/bullet");
		private Vector2 endpoint;
		private Vector2 position;
		private double speed = 400d;
		private boolean reachedTarget = false;
		public Fire(Vector2 position, Vector2 endpoint) 
		{
				this.position = position; this.endpoint = endpoint;
		}

		public Image getFirePic()
		{
				return this.firePic;
		}

		public void updatePosition(double dt)
		{
				Vector2 direction = this.position.direction_to(endpoint);
				double distance = direction.Vector_length();
				Vector2 normed_dir = direction.Normierung();
				double factor = this.speed * dt;
				normed_dir = normed_dir.Scale(factor);
				if (factor >= distance) {
						this.position = endpoint;
				} else{
				this.position = this.position.Addition(normed_dir);
				}
				if ( position == endpoint) reachedTarget = true;
		}

		public boolean reachedTarget()
		{
				return reachedTarget;
		}

		public Vector2 getPosition()
		{
				return position;
		}


}
