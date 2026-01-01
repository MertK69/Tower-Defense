package game.render;
import game.enemy.*;
import javafx.scene.canvas.GraphicsContext;
import java.util.*;
import util.Vector2;
import javafx.scene.paint.Color;


public class EnemyRenderer {

		public void renderEnemies(GraphicsContext gc, List<Enemy>enemies)
		{
				for (Enemy enemy : enemies) {
                    if (!enemy.isAlive()) continue;

                    Vector2 ep = enemy.getPosition();
                    gc.setFill(Color.RED);
                    gc.fillOval(
                            ep.getX() - 8,
                            ep.getY() - 8,
                            16, 16
                    );
				}				

		}
				 

}
