package game.animation.enemyAnimationen;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import javafx.scene.image.Image;
import game.animation.hilfsfunktionen.*;


public class LoadSystems {
    private String css = this.getClass().getResource("/css/UI.css").toExternalForm();		
		public LoadSystems() 
		{
		}
			
		public static List<Image> loadAnimation(String pathToWalkingAnimation,int frameCount)
		{
				List<Image> Liste = new ArrayList<>();
				for(int i=1; i<frameCount;i++)
				{
						Liste.add(Loader.loadImage(pathToWalkingAnimation + i + ".png"));
				}
				return Liste;
		}

		public static Image loadImage(String pathToStaticAnimation)
		{
					return Loader.loadImage(pathToStaticAnimation + ".png");
		}

        public static Image loadGif(String pathToGif)
        {
                return Loader.loadGif(pathToGif + ".gif");
        }

        public String get_css()
        {
            return this.css;
        }

}
