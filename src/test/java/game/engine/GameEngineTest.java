package game.engine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.CountDownLatch;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import game.path.Pathtype;
import game.tower.TowerType;
import util.Vector2;

class GameEngineTest {

    private GameEngine engine;

    @BeforeAll
    static void initToolkit() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);
        try {
            javafx.application.Platform.startup(latch::countDown);
        } catch (IllegalStateException alreadyStarted) {
            latch.countDown();
        }
        latch.await();
    }

    @BeforeEach
    void setUp() {
        // EASY -> 80000 starting money, plenty for handleBuyRequest assertions.
        engine = new GameEngine(Pathtype.EASY, 1, 100);
    }

    /**
     * Fast-forwards a fresh wave to completion (all enemies reach the end of the path)
     * by driving GameEngine.update() with a large stepTime so each call advances a full
     * path segment for every currently-spawned enemy. Boosts livesLeft first so the life
     * loss from enemies reaching the end doesn't also trip gameLost, which would freeze
     * update() before the break-start logic has a chance to run.
     */
    private void runWaveToCompletion() {
        engine.get_livesLeftProperty().setValue(1_000_000);
        int safetyCap = 200;
        int i = 0;
        while (!engine.get_onBreakProperty().getValue() && i < safetyCap) {
            engine.update(1000.0);
            i++;
        }
        assertTrue(engine.get_onBreakProperty().getValue(),
                "Wave did not finish / break did not start within " + safetyCap + " update() calls");
    }

    @Test
    void breakStartsWhenWaveFinishes() {
        runWaveToCompletion();

        assertTrue(engine.get_onBreakProperty().getValue());
        assertEquals(15, engine.get_breakSecondsLeftProperty().getValue());
    }

    @Test
    void breakCountsDownAndAutoEndsAtZero_thenWaveResumes() {
        runWaveToCompletion();

        int previous = engine.get_breakSecondsLeftProperty().getValue();
        for (int i = 0; i < 20 && engine.get_onBreakProperty().getValue(); i++) {
            engine.update(1.0);
            int current = engine.get_breakSecondsLeftProperty().getValue();
            assertTrue(current <= previous, "breakSecondsLeft should never increase");
            previous = current;
        }
        assertFalse(engine.get_onBreakProperty().getValue(), "onBreak should be false after 15s+");

        // Wave/enemy spawning resumes: the next update() call creates a fresh ActiveWave,
        // which immediately publishes its enemy count via waveEnemys.
        engine.update(0.01);
        assertTrue(engine.get_waveEnemyProperty().getValue() > 0, "next wave should have started spawning");
    }

    @Test
    void skipBreakEndsBreakImmediately() {
        runWaveToCompletion();
        assertTrue(engine.get_onBreakProperty().getValue());

        engine.skipBreak();

        assertFalse(engine.get_onBreakProperty().getValue());
        assertEquals(0, engine.get_breakSecondsLeftProperty().getValue());
    }

    @Test
    void skipBreakWhileNotOnBreakIsNoop() {
        assertFalse(engine.get_onBreakProperty().getValue());
        int before = engine.get_breakSecondsLeftProperty().getValue();

        engine.skipBreak();

        assertFalse(engine.get_onBreakProperty().getValue());
        assertEquals(before, engine.get_breakSecondsLeftProperty().getValue());
        assertTrue(engine.get_breakSecondsLeftProperty().getValue() >= 0);
    }

    @Test
    void handleBuyRequestSucceedsDuringBreak() {
        runWaveToCompletion();
        int moneyBefore = engine.get_MoneyProperty().getValue();

        engine.handleBuyRequest(TowerType.BASIC, new Vector2(200, 700));

        int moneyAfter = engine.get_MoneyProperty().getValue();
        assertEquals(moneyBefore - TowerType.BASIC.price(), moneyAfter,
                "Tower purchase should succeed (money deducted) while onBreak is true");
    }

    @Test
    void livesReachingZeroSetsGameLostOnceAndFreezesUpdate() {
        engine.get_livesLeftProperty().setValue(0);

        engine.update(0.016);
        assertTrue(engine.get_gameLostProperty().getValue());

        int waveBefore = engine.get_waveProperty().getValue();
        int enemiesBefore = engine.get_enemyProperty().getValue();

        // Further update() calls must not mutate any state (freeze is idempotent).
        engine.update(0.016);
        engine.update(1000.0);

        assertTrue(engine.get_gameLostProperty().getValue(), "gameLost must stay true (no toggling)");
        assertEquals(waveBefore, engine.get_waveProperty().getValue());
        assertEquals(enemiesBefore, engine.get_enemyProperty().getValue());
    }

    @Test
    void resetGameRestoresInitialState() {
        runWaveToCompletion();
        engine.handleBuyRequest(TowerType.BASIC, new Vector2(50, 50));

        engine.resetGame();

        assertEquals(10, engine.get_livesLeftProperty().getValue());
        assertEquals(1, engine.get_waveProperty().getValue());
        assertEquals(80000, engine.get_MoneyProperty().getValue(), "EASY starting money after reset");
        assertEquals(0, engine.get_enemyProperty().getValue());
        assertFalse(engine.get_gameLostProperty().getValue());
        assertFalse(engine.get_onBreakProperty().getValue());
    }

    @Test
    void resetGameMidBreakClearsBreakTimerState() {
        runWaveToCompletion();
        assertTrue(engine.get_onBreakProperty().getValue());
        assertTrue(engine.get_breakSecondsLeftProperty().getValue() > 0);

        engine.resetGame();

        assertFalse(engine.get_onBreakProperty().getValue());
        assertEquals(0, engine.get_breakSecondsLeftProperty().getValue());
    }

    // ---------------------------------------------------------------------
    // TT-5: pause (AC-4/AC-5) and in-game audio control surface (AC-2/AC-3/AC-8)
    // ---------------------------------------------------------------------

    @Test
    void pauseDefaultsToFalse() {
        assertFalse(engine.isPaused(), "a fresh engine must not start paused");
    }

    @Test
    void pausedUpdateFreezesGameplay() {
        engine.setPaused(true);
        assertTrue(engine.isPaused());

        int waveBefore = engine.get_waveProperty().getValue();
        int enemiesBefore = engine.get_enemyProperty().getValue();
        int waveEnemiesBefore = engine.get_waveEnemyProperty().getValue();

        // Repeated large-step updates must not advance any gameplay state while paused.
        engine.update(1000.0);
        engine.update(1000.0);
        engine.update(1000.0);

        assertEquals(waveBefore, engine.get_waveProperty().getValue(), "wave must not advance while paused");
        assertEquals(enemiesBefore, engine.get_enemyProperty().getValue(), "enemies must not spawn/move while paused");
        assertEquals(waveEnemiesBefore, engine.get_waveEnemyProperty().getValue(), "no wave should start while paused");
    }

    @Test
    void unpauseResumesGameplay() {
        engine.setPaused(true);
        engine.update(1000.0);

        engine.setPaused(false);
        assertFalse(engine.isPaused());

        // First update after resuming starts spawning the wave.
        engine.update(0.01);
        assertTrue(engine.get_waveEnemyProperty().getValue() > 0, "gameplay should resume after unpausing");
    }

    @Test
    void sfxVolumeConversion100MapsToOne() {
        GameEngine e = new GameEngine(Pathtype.EASY, 1, 100);
        assertEquals(1.0, e.getSfxVolume(), 1e-9);
    }

    @Test
    void sfxVolumeConversion50MapsToHalf() {
        GameEngine e = new GameEngine(Pathtype.EASY, 1, 50);
        assertEquals(0.5, e.getSfxVolume(), 1e-9);
    }

    @Test
    void setSfxVolumeIsLiveAndReadable() {
        engine.setSfxVolume(0.2);
        assertEquals(0.2, engine.getSfxVolume(), 1e-9);
    }

    @Test
    void setMusicVolumeIsReadable() {
        engine.setMusicVolume(0.3);
        assertEquals(0.3, engine.getMusicVolume(), 1e-9);
    }

    @Test
    void musicAndSfxVolumesAreIndependent() {
        engine.setSfxVolume(0.2);
        engine.setMusicVolume(0.7);
        assertEquals(0.2, engine.getSfxVolume(), 1e-9, "SFX volume must not be affected by music volume");
        assertEquals(0.7, engine.getMusicVolume(), 1e-9, "music volume must not be affected by SFX volume");
    }

    @Test
    void stopMusicDoesNotThrow() {
        engine.stopMusic();
    }
}
