package game.animation.hilfsfunktionen;

import java.net.URL;

import app.Main;
import game.enemy.EnemyType;
import game.tower.TowerType;
import javafx.scene.image.Image;
import util.Vector2;

public class Loader {


		public static Image loadImage(String path) 
		{
			URL url = Main.class.getResource(path);
			if (url == null) {
				throw new IllegalStateException("Asset nicht gefunden: " + path);
			}
			return new Image(url.toExternalForm());

		}

        public static Image loadGif(String path)
        {
            URL url = Main.class.getResource(path);
			if (url == null) {
				throw new IllegalStateException("Asset nicht gefunden: " + path);
			}
			return new Image(url.toExternalForm());

        }

        public static Vector2 loadRandomVec2()
        {
            Vector2 vec2 = new Vector2( (int) (Math.random() * 1200), (int) (Math.random() * 610));
            return vec2;
        }

        public static Vector2 loadRandomVec2withSpecs(Vector2 position)
        {
            double randomOffsetX = (Math.random() * 85) - 15;
            double randomOffsetY = (Math.random() * 85) - 15;
            int x_range = (int) (position.getX() + randomOffsetX); 
            int y_range = (int) (position.getY() + randomOffsetY);
            Vector2 vec2 = new Vector2(x_range, y_range);
            return vec2;
        }

}
