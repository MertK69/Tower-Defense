package game.render;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import game.tower.*;
import game.enemy.*;
import game.path.*;
import java.util.List;

public class RenderSystems {
		private TowerRenderer towerRenderer;
		private EnemyRenderer enemyRenderer;
		private PathRenderer pathRenderer;
 	
		public RenderSystems (TowerRenderer towerRenderer, EnemyRenderer enemyRenderer, PathRenderer pathRenderer)
		{
				this.towerRenderer = towerRenderer;
				this.enemyRenderer = enemyRenderer;
				this.pathRenderer = pathRenderer;
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




}
