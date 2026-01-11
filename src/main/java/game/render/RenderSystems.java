package game.render;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import game.tower.*;
import game.animation.towerAnimationen.Fire;
import game.economy.Economy;
import game.enemy.*;
import game.path.*;
import java.util.List;

public class RenderSystems {
		private TowerRenderer towerRenderer = new TowerRenderer();
		private EnemyRenderer enemyRenderer = new EnemyRenderer();
		private PathRenderer pathRenderer = new PathRenderer();
		private BackgroundRenderer backgroundRenderer = new BackgroundRenderer();
		private UIRenderer uiRenderer = new UIRenderer();
		private BulletRenderer bulletRenderer = new BulletRenderer();
		public RenderSystems ()
		{
		}

		public void renderTower(GraphicsContext gc, List<Tower>towers, double dt)
		{
				towerRenderer.renderTower(towers, gc, dt);				
		}

		public void renderEnemies(GraphicsContext gc, List<Enemy>enemies, double STEP, Path path)
		{
				enemyRenderer.renderEnemies(gc, enemies, STEP, path);
		}

		public void renderRemovedEnemies(GraphicsContext gc, List<Enemy>EnemiesToRemove)
		{
				enemyRenderer.renderRemovedEnemies(gc, EnemiesToRemove);
		}

		public void renderPath(GraphicsContext gc, Path path)
		{
				pathRenderer.renderPath(gc, path);
		}

		public void renderBackground(GraphicsContext gc, Canvas canvas)
		{
				backgroundRenderer.renderBackground(gc, canvas);
		}

		public void renderUI(GraphicsContext gc, Economy economy)
		{
				uiRenderer.renderUI(gc, economy);
		}

		public void renderBullets(GraphicsContext gc, List<Fire>Bullets)
		{
				bulletRenderer.renderBullets(gc, Bullets);
		}

}
