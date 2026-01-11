package game.render;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import util.Vector2;

import java.util.HashMap;
import java.util.Map;

import game.economy.Economy;

public class UIRenderer {
		Map<Image, Vector2> Images = new HashMap<>();

		public void renderUI(GraphicsContext gc, Economy economy)
		{
				if (Images.isEmpty()) initializeTowerUI(gc);
				drawTowerUI(gc);
				String Wealth = String.valueOf(economy.getCurr_Money()); 
				gc.setFont(Font.font("Arial", 40));
				gc.setFill(Color.DARKRED);
				gc.fillText(Wealth, 85, 585);
		}

		private void initializeTowerUI(GraphicsContext gc)
		{		
				Images.put(new Image("/images/static-images/lvl1-turret.png"), new Vector2(120, 710));
				Images.put(new Image("/images/static-images/lvl2-turret.png"), new Vector2(310, 715));
				Images.put(new Image("/images/static-images/lvl3-turret.png"), new Vector2(500, 710));
				Images.put(new Image("/images/static-images/lvl1-turret.png"), new Vector2(700, 710));
		}

		private void drawTowerUI(GraphicsContext gc)
		{
				if (Images.isEmpty()) return;
				for (Map.Entry<Image, Vector2> X : Images.entrySet())
				{
						gc.drawImage(
								X.getKey(),
								X.getValue().getX() - 60,
								X.getValue().getY()- 60,
								120, 120);
				}

		}
}
