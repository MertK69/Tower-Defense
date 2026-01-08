package game.render;
import game.tower.*;
import util.Vector2;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import java.util.*;
public class TowerRenderer {

		public void renderTower(List<Tower>towers, GraphicsContext gc, double dt)
		{
				for (Tower tower : towers) 
				{
					Vector2 tp = tower.getPosition();
				    Image towerPic = image(tower, dt);
					gc.save();
					gc.translate(tp.getX(), tp.getY());
					gc.rotate(tower.getAngle());
					gc.drawImage(
						    towerPic,
							- 18,
							- 18,
							36, 36
					);
					gc.restore();
					gc.setStroke(Color.color(0, 0, 1, 0.25));
					gc.strokeOval(
							tp.getX() - tower.getReichweite(),
							tp.getY() - tower.getReichweite(),
							tower.getReichweite() * 2,
							tower.getReichweite() * 2
					);
				 }
		}

		public Image image(Tower tower, double dt)
		{
				Image image = null;

				if(!tower.getAnimationLock())
				{
						image = tower.getType().image();
				} else {
					 image = tower.currShootAnimation(dt);	
				}
				return image;
		}
}
