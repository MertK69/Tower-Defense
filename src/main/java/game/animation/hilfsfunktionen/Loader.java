package game.animation.hilfsfunktionen;

import java.net.URL;

import app.Main;
import game.enemy.EnemyType;
import game.tower.TowerType;
import javafx.scene.image.Image;

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


}
