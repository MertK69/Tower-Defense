package game.render;
import game.render.animationStorage.TowerAnimation;
import game.tower.*;
import util.Vector2;
import javafx.beans.property.BooleanProperty;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import java.util.*;
public class TowerRenderer {
    private TowerAnimation BASIC = new TowerAnimation(TowerType.BASIC);
    private TowerAnimation ADVANCED = new TowerAnimation(TowerType.ADVANCED);
    private TowerAnimation EXPERT = new TowerAnimation(TowerType.EXPERT);
    private TowerAnimation RAYBASIC = new TowerAnimation(TowerType.RAYBASIC);
    private TowerAnimation RAYADVANCED = new TowerAnimation(TowerType.RAYADVANCED);
    private TowerAnimation RAYEXPERT = new TowerAnimation(TowerType.RAYEXPERT);
    private TowerAnimation ROCKETLAUNCHERBASIC = new TowerAnimation(TowerType.ROCKETLAUNCHERBASIC);
    private TowerAnimation ROCKETLAUNCHERADVANCED = new TowerAnimation(TowerType.ROCKETLAUNCHERADVANCED);
    private TowerAnimation ROCKETLAUNCHEREXPERT = new TowerAnimation(TowerType.ROCKETLAUNCHEREXPERT);

    private static final int PREVIEW_SPRITE_SIZE = 36;
    private static final int PREVIEW_SPRITE_HALF_SIZE = PREVIEW_SPRITE_SIZE / 2;
    private static final double PREVIEW_SPRITE_ALPHA = 0.5;
    private static final double PREVIEW_RANGE_FILL_ALPHA = 0.2;

    private BooleanProperty showTowerRanges;

    public TowerRenderer(BooleanProperty showTowerRanges)
    {
        this.showTowerRanges = showTowerRanges; 
    }

		public void renderTower(List<Tower>towers, GraphicsContext gc, double dt)
		{
            gc.save();
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
                    if (this.showTowerRanges.getValue() == true)
                    {
                        gc.save();
                        gc.setStroke(Color.BLACK);
                        gc.strokeOval(
                                tp.getX() - tower.getReichweite(),
                                tp.getY() - tower.getReichweite(),
                                tower.getReichweite() * 2,
                                tower.getReichweite() * 2
                        );
                        gc.restore();
                        if (tower.get_NegativReichweite() > 0)
                        {
                            gc.save();
                            gc.setStroke(Color.RED);
                            gc.strokeOval(
                                    tp.getX() - tower.get_NegativReichweite(),
                                    tp.getY() - tower.get_NegativReichweite(),
                                    tower.get_NegativReichweite() * 2,
                                    tower.get_NegativReichweite() * 2
                        );
                            gc.setFill(Color.RED);
                            gc.setGlobalAlpha(0.2);
                            gc.fillOval(
                                    tp.getX() - tower.get_NegativReichweite(),
                                    tp.getY() - tower.get_NegativReichweite(),
                                    tower.get_NegativReichweite() * 2,
                                    tower.get_NegativReichweite() * 2
                                    );
                            gc.restore();
                        }
                    }
				 }
            gc.restore();
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
                if(tower.getType() == TowerType.RAYBASIC)
                {
                    if(!tower.getAnimationLock())
                    {
                            image = RAYBASIC.getImage();
                    } else {
                         int frame = tower.currShootAnimation(dt);
                         image = RAYBASIC.getNextMovement(frame);	
                    }
                }
                if(tower.getType() == TowerType.RAYADVANCED)
                {
                    if(!tower.getAnimationLock())
                    {
                            image = RAYADVANCED.getImage();
                    } else {
                         int frame = tower.currShootAnimation(dt);
                         image = RAYADVANCED.getNextMovement(frame);	
                    }
                }
                if(tower.getType() == TowerType.RAYEXPERT)
                {
                    if(!tower.getAnimationLock())
                    {
                            image = RAYEXPERT.getImage();
                    } else {
                         int frame = tower.currShootAnimation(dt);
                         image = RAYEXPERT.getNextMovement(frame);	
                    }
                }
                if(tower.getType() == TowerType.ROCKETLAUNCHERBASIC)
                {
                    if(!tower.getAnimationLock())
                    {
                            image = ROCKETLAUNCHERBASIC.getImage();
                    } else {
                         int frame = tower.currShootAnimation(dt);
                         image = ROCKETLAUNCHERBASIC.getNextMovement(frame);	
                    }
                }
                if(tower.getType() == TowerType.ROCKETLAUNCHERADVANCED)
                {
                    if(!tower.getAnimationLock())
                    {
                            image = ROCKETLAUNCHERADVANCED.getImage();
                    } else {
                         int frame = tower.currShootAnimation(dt);
                         image = ROCKETLAUNCHERADVANCED.getNextMovement(frame);	
                    }
                }
                if(tower.getType() == TowerType.ROCKETLAUNCHEREXPERT)
                {
                    if(!tower.getAnimationLock())
                    {
                            image = ROCKETLAUNCHEREXPERT.getImage();
                    } else {
                         int frame = tower.currShootAnimation(dt);
                         image = ROCKETLAUNCHEREXPERT.getNextMovement(frame);	
                    }
                }

				return image;
		}

		public Image idleImage(TowerType type)
		{
				if (type == TowerType.BASIC) return BASIC.getImage();
				if (type == TowerType.ADVANCED) return ADVANCED.getImage();
				if (type == TowerType.EXPERT) return EXPERT.getImage();
				if (type == TowerType.RAYBASIC) return RAYBASIC.getImage();
				if (type == TowerType.RAYADVANCED) return RAYADVANCED.getImage();
				if (type == TowerType.RAYEXPERT) return RAYEXPERT.getImage();
				if (type == TowerType.ROCKETLAUNCHERBASIC) return ROCKETLAUNCHERBASIC.getImage();
				if (type == TowerType.ROCKETLAUNCHERADVANCED) return ROCKETLAUNCHERADVANCED.getImage();
				if (type == TowerType.ROCKETLAUNCHEREXPERT) return ROCKETLAUNCHEREXPERT.getImage();
				return null;
		}

		public void renderPlacementPreview(GraphicsContext gc, TowerType type, Vector2 position, boolean valid)
		{
				if (type == null || position == null) return;

				Image previewImage = idleImage(type);
				int reichweite = type.reichweite();
				Color rangeColor = valid ? Color.GREEN : Color.RED;

				gc.save();
				gc.setGlobalAlpha(PREVIEW_SPRITE_ALPHA);
				if (previewImage != null)
				{
						gc.drawImage(
								previewImage,
								position.getX() - PREVIEW_SPRITE_HALF_SIZE,
								position.getY() - PREVIEW_SPRITE_HALF_SIZE,
								PREVIEW_SPRITE_SIZE, PREVIEW_SPRITE_SIZE
						);
				}
				gc.setStroke(rangeColor);
				gc.strokeOval(
						position.getX() - reichweite,
						position.getY() - reichweite,
						reichweite * 2,
						reichweite * 2
				);
				gc.setFill(rangeColor);
				gc.setGlobalAlpha(PREVIEW_RANGE_FILL_ALPHA);
				gc.fillOval(
						position.getX() - reichweite,
						position.getY() - reichweite,
						reichweite * 2,
						reichweite * 2
				);
				gc.restore();
		}
}
