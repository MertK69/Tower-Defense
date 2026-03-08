package game.render;
import game.render.animationStorage.TowerAnimation;
import game.tower.*;
import util.Vector2;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import java.util.*;
public class TowerRenderer {
    private TowerAnimation BASIC = new TowerAnimation(TowerType.BASIC);
    private TowerAnimation ADVANCED = new TowerAnimation(TowerType.ADVANCED);
    private TowerAnimation EXPERT = new TowerAnimation(TowerType.EXPERT);

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
                if(tower.getType() == TowerType.BASIC)
                {
                    if(!tower.getAnimationLock())
                    {
                            image = BASIC.getImage();
                    } else {
                         int frame = tower.currShootAnimation(dt);
                         image = BASIC.getNextMovement(frame);	
                    }
                }
                if(tower.getType() == TowerType.ADVANCED)
                {
                    if(!tower.getAnimationLock())
                    {
                            image = ADVANCED.getImage();
                    } else {
                         int frame = tower.currShootAnimation(dt);
                         image = ADVANCED.getNextMovement(frame);	
                    }
                }
                if(tower.getType() == TowerType.EXPERT)
                {
                    if(!tower.getAnimationLock())
                    {
                            image = EXPERT.getImage();
                    } else {
                         int frame = tower.currShootAnimation(dt);
                         image = EXPERT.getNextMovement(frame);	
                    }
                }
				return image;
		}
}
