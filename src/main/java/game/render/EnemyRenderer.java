package game.render;
import game.enemy.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.util.*;
import util.Vector2;
import javafx.scene.paint.Color;
import game.path.*;
import game.animation.enemyAnimationen.*;
import game.render.animationStorage.*;
public class EnemyRenderer {
    public EnemyAnimation Matrose;
    public EnemyAnimation Gefreiter;
    public EnemyAnimation Leutnant;
    public EnemyAnimation Kapitan;
    public EnemyAnimation Kommodore;
    public EnemyAnimation Vizeadmiral;
    public EnemyAnimation Admiral;
    public EnemyAnimation Großadmiral;

    public EnemyRenderer()
    {
        this.Matrose = new EnemyAnimation(EnemyType.Matrose);
        this.Gefreiter = new EnemyAnimation(EnemyType.Gefreiter);
        this.Leutnant = new EnemyAnimation(EnemyType.Leutnant);
        this.Kapitan = new EnemyAnimation(EnemyType.Kapitan);
        this.Kommodore = new EnemyAnimation(EnemyType.Kommodore);
        this.Vizeadmiral = new EnemyAnimation(EnemyType.Vizeadmiral);
        this.Admiral = new EnemyAnimation(EnemyType.Admiral);
        this.Großadmiral = new EnemyAnimation(EnemyType.Großadmiral);
    }

		public void renderEnemies(GraphicsContext gc, List<Enemy>enemies, double dt, Path path)
		{
				for (Enemy enemy : enemies) {
                    if (!enemy.isAlive()) continue;
				    Image currEnemyMovement = getNextImage(enemy, getDirection(enemy, path),enemy.getCurrMovement(dt));
                    Vector2 ep = enemy.getPosition();
                    gc.setFill(Color.RED);
                    gc.drawImage(
						    currEnemyMovement,
                            ep.getX() - 18,
                            ep.getY() - 18,
                            36, 36
                    );
                    gc.setFill(Color.RED);
                    gc.fillRect(ep.getX() - 10, ep.getY() + 12, 20, 5);
                    gc.setFill(Color.GREEN);
                    double hpPercentage = enemy.getCurrent_hp() / (double) enemy.getMaxHealth(); 
                    gc.fillRect(ep.getX() - 10, ep.getY() + 12, 20 * hpPercentage, 5);
                }				

		}

		public void renderRemovedEnemies(GraphicsContext gc, List<Enemy>EnemiesToRemove)
		{
				for (Enemy enemy : EnemiesToRemove)
				{
						return;								
				}
		}

		public int getDirection(Enemy enemy, Path path)
		{
				if ( path.get_waypoint(enemy.getCurrWaypoint()).getY() == enemy.getPosition().getY() )
				{
						if ( path.get_waypoint(enemy.getCurrWaypoint()).getX() - enemy.getPosition().getX() < 0 )
						{
							return 1;  		
						} else {
								return 2;
								}
				} else{
						if (  path.get_waypoint(enemy.getCurrWaypoint()).getY() - enemy.getPosition().getY() > 0 )
						{
						     return 3;
						} else {
								return 4;
						}
				}
		}

        public Image getNextImage(Enemy enemy, int direction, int currMovement)
        {
            if (enemy.getType() == EnemyType.Matrose)
            {
                    return Matrose.getNextMovement(direction, currMovement);
            }
            if (enemy.getType() == EnemyType.Gefreiter)
            {
                    return Gefreiter.getNextMovement(direction, currMovement);
            }
            if (enemy.getType() == EnemyType.Leutnant)
            {
                    return Leutnant.getNextMovement(direction, currMovement);
            }
            if (enemy.getType() == EnemyType.Kapitan)
            {
                    return Kapitan.getNextMovement(direction, currMovement);
            }
            if (enemy.getType() == EnemyType.Kommodore)
            {
                    return Kommodore.getNextMovement(direction, currMovement);
            }
            if (enemy.getType() == EnemyType.Vizeadmiral)
            {
                    return Vizeadmiral.getNextMovement(direction, currMovement);
            }
            if (enemy.getType() == EnemyType.Admiral)
            {
                    return Admiral.getNextMovement(direction, currMovement);
            }
            if (enemy.getType() == EnemyType.Großadmiral)
            {
                    return Großadmiral.getNextMovement(direction, currMovement);
            }
            return null;
        }

}
