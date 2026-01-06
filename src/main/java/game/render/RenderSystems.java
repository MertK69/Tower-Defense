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
		private TowerRenderer towerRenderer;
		private EnemyRenderer enemyRenderer;
		private PathRenderer pathRenderer;
		private BackgroundRenderer backgroundRenderer;
		private UIRenderer uiRenderer;
		private BulletRenderer bulletRenderer;
		public RenderSystems (TowerRenderer towerRenderer, EnemyRenderer enemyRenderer, PathRenderer pathRenderer, BackgroundRenderer backgroundRenderer, UIRenderer uiRenderer, BulletRenderer bulletRenderer)
		{
				this.towerRenderer = towerRenderer;
				this.enemyRenderer = enemyRenderer;
				this.pathRenderer = pathRenderer;
				this.backgroundRenderer = backgroundRenderer;
				this.uiRenderer = uiRenderer;
				this.bulletRenderer = bulletRenderer;
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
