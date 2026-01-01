package game.render;
import game.tower.*;
import util.Vector2;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.*;
public class TowerRenderer {

		public void renderTower(List<Tower>towers, GraphicsContext gc)
		{
				for (Tower tower : towers) 
				{
					Vector2 tp = tower.getPosition();

					gc.setFill(Color.BLUE);
					gc.fillOval(
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
}
