package game.engine;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class RoundLifecycle
{
    private static final double BREAK_DURATION = 15.0;
    private static final int STARTING_LIVES = 10;

    private final BooleanProperty onBreak = new SimpleBooleanProperty(false);
    private final IntegerProperty breakSecondsLeft = new SimpleIntegerProperty();
    private final BooleanProperty gameLost = new SimpleBooleanProperty();
    private final IntegerProperty livesLeft = new SimpleIntegerProperty(STARTING_LIVES);
    private double breakTimer = 0;

    // Sets gameLost once when lives run out; idempotent while already lost.
    public boolean checkAndHandleGameLost()
    {
        if (livesLeft.getValue() <= 0 && !gameLost.getValue())
        {
            gameLost.setValue(true);
        }
        return gameLost.getValue();
    }

    public void loseLife()
    {
        livesLeft.setValue(livesLeft.getValue() - 1);
    }

    public void startBreak()
    {
        onBreak.setValue(true);
        breakTimer = BREAK_DURATION;
        breakSecondsLeft.setValue((int) BREAK_DURATION);
    }

    // Counts down the between-wave break and ends it once the timer reaches zero.
    public void advanceBreak(double stepTime)
    {
        breakTimer -= stepTime;
        breakSecondsLeft.setValue((int) Math.ceil(Math.max(0, breakTimer)));
        if (breakTimer <= 0)
        {
            onBreak.setValue(false);
            breakTimer = 0;
        }
    }

    public void skipBreak()
    {
        if (!onBreak.getValue()) return;
        onBreak.setValue(false);
        breakTimer = 0;
        breakSecondsLeft.setValue(0);
    }

    public void reset()
    {
        livesLeft.setValue(STARTING_LIVES);
        onBreak.setValue(false);
        breakTimer = 0;
        breakSecondsLeft.setValue(0);
        gameLost.setValue(false);
    }

    public boolean isOnBreak()
    {
        return onBreak.getValue();
    }

    public BooleanProperty onBreakProperty()
    {
        return onBreak;
    }

    public IntegerProperty breakSecondsLeftProperty()
    {
        return breakSecondsLeft;
    }

    public BooleanProperty gameLostProperty()
    {
        return gameLost;
    }

    public IntegerProperty livesLeftProperty()
    {
        return livesLeft;
    }
}
