package game.render;

import game.path.Path;
import javafx.scene.canvas.GraphicsContext;
import util.Vector2;
import javafx.scene.paint.Color;


public class PathRenderer {

		public void renderPath(GraphicsContext gc, Path path)
		{
				gc.setStroke(Color.TRANSPARENT);
                gc.setLineWidth(6);
				for (int i=0;i<path.get_waypoint_amount() - 1;i++)
				{
						Vector2 waypoint = path.get_waypoint(i);
						Vector2 nextWaypoint = path.get_waypoint(i + 1);
						gc.strokeLine(waypoint.getX(), waypoint.getY(), nextWaypoint.getX(), nextWaypoint.getY());	
				}	
		}
}
