package app;

import game.combat.*;
import game.enemy.*;
import game.path.Path;
import game.tower.*;
import util.Vector2;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    private long lastTime = 0;

    @Override
    public void start(Stage stage) {

        Canvas canvas = new Canvas(900, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // ===== PATH =====
        Path path = new Path(List.of(
                new Vector2(50, 300),
				new Vector2(150, 450),
                new Vector2(850, 450)
        ));

        // ===== ENEMIES =====
        List<Enemy> enemies = new ArrayList<>();
		for (int i = 0; i < 8; i++) {
			enemies.add(new Enemy(
				EnemyType.Matrose,
				new Path(List.of(
					new Vector2(50 + i * 10, 300),
					new Vector2(850 + i * 10, 300 )
				))
			));
		}
		
        // ===== TOWERS =====
        Tower tower1 = new Tower(TowerType.BASIC,    new Vector2(500, 270));
        Tower tower2 = new Tower(TowerType.ADVANCED, new Vector2(450, 270));
        Tower tower3 = new Tower(TowerType.BASIC,    new Vector2(400, 270));
        Tower tower4 = new Tower(TowerType.ADVANCED, new Vector2(350, 270));

        List<Tower> towers = List.of(tower1, tower2, tower3, tower4);

        // ===== COMBAT =====
        TargetingSystem targetingSystem = new TargetingSystem();
        DamageSystem damageSystem = new DamageSystem();
        CombatSystem combatSystem = new CombatSystem(targetingSystem, damageSystem);

        // ===== GAME LOOP =====
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (lastTime == 0) {
                    lastTime = now;
                    return;
                }

                double dt = (now - lastTime) / 1_000_000_000.0;
                lastTime = now;

                // UPDATE ENEMIES
                for (Enemy enemy : enemies) {
                    if (enemy.isAlive() && !enemy.isFinished()) {
                        enemy.update(dt);
                    }
                }

                // COMBAT
                combatSystem.update(dt, towers, enemies);

                // ===== RENDER =====
                gc.setFill(Color.BLACK);
                gc.fillRect(0, 0, 900, 600);

                // PATH
                gc.setStroke(Color.DARKGRAY);
                gc.setLineWidth(3);
                gc.strokeLine(50, 300, 850, 300);

                // TOWERS + RANGE
                for (Tower tower : towers) {
                    Vector2 tp = tower.getPosition();

                    gc.setFill(Color.BLUE);
                    gc.fillOval(
                            tp.getX() - 12,
                            tp.getY() - 12,
                            24, 24
                    );

                    gc.setStroke(Color.color(0, 0, 1, 0.25));
                    gc.strokeOval(
                            tp.getX() - tower.getReichweite(),
                            tp.getY() - tower.getReichweite(),
                            tower.getReichweite() * 2,
                            tower.getReichweite() * 2
                    );
                }

                // ENEMIES
                for (Enemy enemy : enemies) {
                    if (!enemy.isAlive()) continue;

                    Vector2 ep = enemy.getPosition();
                    gc.setFill(Color.RED);
                    gc.fillOval(
                            ep.getX() - 8,
                            ep.getY() - 8,
                            16, 16
                    );
                }
            }
        };

        timer.start();

        stage.setScene(new Scene(new StackPane(canvas)));
        stage.setTitle("Tower Defense – Test");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

