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
					gc.drawImage(
						    towerPic,
							tp.getX() - 12,
							tp.getY() - 12,
							24, 24
					);

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
