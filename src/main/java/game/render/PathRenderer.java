package game.render;

import game.path.Path;
import javafx.scene.canvas.GraphicsContext;
import util.Vector2;
import javafx.scene.paint.Color;


public class PathRenderer {

		public void renderPath(GraphicsContext gc, Path path)
		{
                gc.save();
				gc.setStroke(Color.CHOCOLATE);
                gc.setLineWidth(32);
                _renderPath(gc, path);

                gc.setStroke(Color.BEIGE);
                gc.setLineWidth(28);
                _renderPath(gc, path);
                gc.restore();
        }

        private void _renderPath(GraphicsContext gc, Path path)
        {
            for (int i=0;i<path.get_waypoint_amount() - 1;i++)
				{
						Vector2 waypoint = path.get_waypoint(i);
						Vector2 nextWaypoint = path.get_waypoint(i + 1);
						gc.strokeLine(waypoint.getX(), waypoint.getY(), nextWaypoint.getX(), nextWaypoint.getY());	
                }
        }
}
