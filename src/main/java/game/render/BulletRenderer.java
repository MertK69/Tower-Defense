package game.render;

import java.util.List;

import game.animation.towerAnimationen.Fire;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import util.Vector2;

public class BulletRenderer{

		public void renderBullets(GraphicsContext gc, List<Fire> Bullets)
		{
            for (Fire bullet : Bullets) {
            if (bullet.reachedTarget()) continue;
            
            Image currBullet = bullet.getFirePic();
            Vector2 ep = bullet.getPosition();
            
            gc.save();
            gc.translate(ep.getX(), ep.getY());
            
            if (bullet.get_fliesArch() == true)
            {
                gc.rotate(bullet.getAngle());
            }
            gc.drawImage(currBullet, -9, -9, 18, 18);
            
            gc.restore();
            }
		}

}
