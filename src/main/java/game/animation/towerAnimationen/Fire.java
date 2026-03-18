package game.animation.towerAnimationen;

import javafx.scene.image.Image;
import util.Vector2;
import game.animation.enemyAnimationen.LoadSystems;
import game.tower.TowerType;

public class Fire{
		private String firePic = "/images/static-images/bullet";
        private String rayPic = "/images/static-images/ray2";
        private Image loadedFirePic = null;
		private Vector2 endpoint;
		private Vector2 position;
		private double speed = 400d;
		private boolean reachedTarget = false;

		public Fire(TowerType type ,Vector2 position, Vector2 endpoint) 
		{
                if (type == TowerType.BASIC || type == TowerType.ADVANCED || type == TowerType.EXPERT) 
                {
                    this.loadedFirePic = LoadSystems.loadImage(this.firePic);
                } else{
                    this.loadedFirePic = LoadSystems.loadImage(this.rayPic);
                }
				this.position = position; this.endpoint = endpoint;
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

		public Image getFirePic()
		{
            if (this.loadedFirePic == null)
            {
                this.loadedFirePic = LoadSystems.loadImage(this.firePic);
            }
			return this.loadedFirePic;
		}

}
