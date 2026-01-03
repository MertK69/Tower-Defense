package game.render;
import game.enemy.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.*;
import util.Vector2;
import javafx.scene.paint.Color;


public class EnemyRenderer {

		public void renderEnemies(GraphicsContext gc, List<Enemy>enemies)
		{
				Image Matrose = new Image("/images/Solider.png");
				for (Enemy enemy : enemies) {
                    if (!enemy.isAlive()) continue;

                    Vector2 ep = enemy.getPosition();
                    gc.setFill(Color.RED);
					
                    gc.drawImage(
						    Matrose,
                            ep.getX() - 18,
                            ep.getY() - 18,
                            36, 36
                    );
				}				

		}
				 

}
