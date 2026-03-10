package game.render;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class BackgroundRenderer {
		Image Background;

		public void renderBackground(GraphicsContext gc, Canvas canvas)
		{
				if ( Background == null )
				{
					this.Background = new Image (getClass().getResource("/images/clean_background.png").toExternalForm());
				}
				gc.drawImage(Background, 0, 0, canvas.getWidth(), canvas.getHeight());
		}
}
