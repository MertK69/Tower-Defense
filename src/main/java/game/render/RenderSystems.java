package game.render;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import game.tower.*;
import game.economy.Economy;
import game.enemy.*;
import game.path.*;
import java.util.List;

public class RenderSystems {
		private TowerRenderer towerRenderer;
		private EnemyRenderer enemyRenderer;
		private PathRenderer pathRenderer;
		private BackgroundRenderer backgroundRenderer;
		private UIRenderer uiRenderer;
		public RenderSystems (TowerRenderer towerRenderer, EnemyRenderer enemyRenderer, PathRenderer pathRenderer, BackgroundRenderer backgroundRenderer, UIRenderer uiRenderer)
		{
				this.towerRenderer = towerRenderer;
				this.enemyRenderer = enemyRenderer;
				this.pathRenderer = pathRenderer;
				this.backgroundRenderer = backgroundRenderer;
				this.uiRenderer = uiRenderer;
		}

		public void renderTower(GraphicsContext gc, List<Tower>towers)
		{
				towerRenderer.renderTower(towers, gc);				
		}

		public void renderEnemies(GraphicsContext gc, List<Enemy>enemies)
		{
				enemyRenderer.renderEnemies(gc, enemies);
		}

		public void renderPath(GraphicsContext gc, Path path)
		{
				pathRenderer.renderPath(gc, path);
		}

		public void renderBackground(GraphicsContext gc, Canvas canvas)
		{
				backgroundRenderer.renderBackground(gc, canvas);
		}

		public void uiRenderer(GraphicsContext gc, Economy economy)
		{
				uiRenderer.renderUI(gc, economy);
		}

}
