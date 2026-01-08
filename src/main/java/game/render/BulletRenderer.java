package game.render;

import java.util.List;

import game.animation.towerAnimationen.Fire;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import util.Vector2;

public class BulletRenderer{

		public void renderBullets(GraphicsContext gc, List<Fire> Bullets)
		{
				for (Fire bullet : Bullets) {
                    if (bullet.reachedTarget()) continue;
				    Image currBullet = bullet.getFirePic();
                    Vector2 ep = bullet.getPosition();
                    gc.setFill(Color.RED);
                    gc.drawImage(
						    currBullet,
                            ep.getX() - 9,
                            ep.getY() - 9,
                            18, 18
                    );
				}
		}




}
