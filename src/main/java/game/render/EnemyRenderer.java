package game.render;
import game.enemy.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import java.util.*;
import util.Vector2;
import javafx.scene.paint.Color;
import game.path.*;


public class EnemyRenderer {

		public void renderEnemies(GraphicsContext gc, List<Enemy>enemies, double dt, Path path)
		{
				for (Enemy enemy : enemies) {
                    if (!enemy.isAlive()) continue;
				    Image currEnemyMovement = enemy.getCurrMovement(dt, getDirection(enemy, path));
                    Vector2 ep = enemy.getPosition();
                    gc.setFill(Color.RED);
                    gc.drawImage(
						    currEnemyMovement,
                            ep.getX() - 18,
                            ep.getY() - 18,
                            36, 36
                    );
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

}
