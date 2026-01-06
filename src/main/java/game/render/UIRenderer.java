package game.render;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import game.economy.Economy;

public class UIRenderer {

		public void renderUI(GraphicsContext gc, Economy economy)
		{
				String Wealth = String.valueOf(economy.getCurr_Money()); 
				gc.setFont(Font.font("Arial", 40));
				gc.setFill(Color.DARKRED);
				gc.fillText(Wealth, 85, 585);
		}




}
