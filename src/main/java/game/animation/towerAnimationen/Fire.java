package game.animation.towerAnimationen;

import javafx.scene.image.Image;
import util.Vector2;
import game.animation.enemyAnimationen.LoadSystems;
import game.enemy.Enemy;
import game.tower.TowerType;

public class Fire{
		private String firePic = "/images/static-images/bullet";
        private String rayPic = "/images/static-images/ray2";
        private String rocketPic = "/images/static-images/Missle";
        private Image loadedFirePic = null;
		private Vector2 endpoint;
		private Vector2 position;
        private Vector2 startPosition;
		private double speed = 400d;
        private double totalDistance;
        private double distanceTraveled = 0;
        private double maxHeight = 100d;
		private boolean reachedTarget = false;
        private boolean fliesArch = false;
        private double currentAngle = 0;
        private double imageOffsetAngle = -135;
        private Enemy enemy;
        private TowerType type;

		public Fire(TowerType type ,Vector2 position, Vector2 endpoint, Enemy enemy) 
		{
            if (type == TowerType.BASIC || type == TowerType.ADVANCED || type == TowerType.EXPERT) 
            {
                this.loadedFirePic = LoadSystems.loadImage(this.firePic);
            } else if(type == TowerType.RAYBASIC || type == TowerType.RAYADVANCED || type == TowerType.RAYEXPERT ){
                this.loadedFirePic = LoadSystems.loadImage(this.rayPic);
            } else{
                this.loadedFirePic = LoadSystems.loadImage(this.rocketPic);
                this.fliesArch = true;
            }
            this.position = position; 
            this.endpoint = new Vector2(endpoint.getX(), endpoint.getY());
            this.startPosition = position; 
            this.totalDistance = this.startPosition.direction_to(endpoint).Vector_length();
            Vector2 dir = this.startPosition.direction_to(endpoint);
            this.currentAngle = Math.toDegrees(Math.atan2(dir.getY(), dir.getX())) - imageOffsetAngle;
            this.type = type;
            this.enemy = enemy;
		}

        public void updatePosition(double dt) {
        if (reachedTarget) return;

        // 1. Alte Position merken
        double oldX = this.position.getX();
        double oldY = this.position.getY();

        double step = this.speed * dt;
        distanceTraveled += step;

        if (distanceTraveled >= totalDistance) {
            this.position = endpoint;
            this.reachedTarget = true;
        } else {
            Vector2 direction = startPosition.direction_to(endpoint);
            Vector2 normed_dir = direction.Normierung();
            Vector2 linearPosition = startPosition.Addition(normed_dir.Scale(distanceTraveled));

            if (fliesArch) {
                double t = distanceTraveled / totalDistance;
                double arcHeight = maxHeight * Math.sin(t * Math.PI);
                Vector2 heightOffset = new Vector2(0, -arcHeight); 
                this.position = linearPosition.Addition(heightOffset);
            } else {
                this.position = linearPosition;
            }
        }

        // 2. Winkel anhand der tatsächlichen Bewegung im aktuellen Frame berechnen
        double dx = this.position.getX() - oldX;
        double dy = this.position.getY() - oldY;
        
        // Verhindern, dass der Winkel bei Stillstand auf 0 springt
        if (dx != 0 || dy != 0) {
            // Math.atan2 gibt Bogenmaß zurück -> in Grad umwandeln -> Bild-Offset abziehen
            this.currentAngle = Math.toDegrees(Math.atan2(dy, dx)) - imageOffsetAngle;
        }
    }

		public boolean reachedTarget()
		{
            return reachedTarget;
		}

		public Vector2 getPosition()
		{
            return position;
		}

        public boolean get_fliesArch()
        {
            return this.fliesArch;
        }

		public Image getFirePic()
		{
            if (this.loadedFirePic == null)
            {
                this.loadedFirePic = LoadSystems.loadImage(this.firePic);
            }
			return this.loadedFirePic;
		}

        public double getAngle() 
        {
            return this.currentAngle;
        }

        public int getDamage()
        {
            return this.type.damage();
        }

        public Enemy getEnemy()
        {
            return this.enemy;
        }

        public Vector2 getEndpoint()
        {
            return this.endpoint;
        }

}
