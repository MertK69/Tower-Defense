package game.engine;

import javafx.animation.AnimationTimer;
import game.engine.GameEngine;

public final class GameLoop extends AnimationTimer {

    private static final double STEP = 1.0 / 60.0;
    private long lastTime = 0;
    private double accumulator = 0;
    private final GameEngine GE;

	public GameLoop(GameEngine engine){
		this.GE = engine;
		}


    @Override
    public void handle(long now) {
        if (lastTime == 0) {
            lastTime = now;
            return;
        }

        double delta = (now - lastTime) / 1_000_000_000.0;
        lastTime = now;
        accumulator += delta;

        while (accumulator >= STEP) {
            GE.update(STEP);
            accumulator -= STEP;
        }

        GE.render(STEP);
    }
}

