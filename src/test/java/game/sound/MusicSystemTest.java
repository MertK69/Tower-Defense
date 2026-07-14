package game.sound;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.CountDownLatch;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Headless unit tests for {@link MusicSystem} looping BGM (TT-5 AC-1, AC-3).
 * Covers both the happy path (valid resource) and the defensive no-op fallback
 * (missing/bad resource must not throw — plan-review finding TST-M1). TDD RED.
 */
class MusicSystemTest {

    private static final String VALID = "/sounds/tower-sound.mp3";
    private static final String MISSING = "/sounds/this-resource-does-not-exist-xyz.mp3";

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

    @Test
    void constructsFromValidResourceWithoutThrowing() {
        assertDoesNotThrow(() -> new MusicSystem(VALID));
    }

    @Test
    void constructsFromMissingResourceWithoutThrowing_fallbackNoOp() {
        MusicSystem m = assertDoesNotThrow(() -> new MusicSystem(MISSING));
        // Fallback: no underlying player, but the object is still safely usable.
        assertFalse(m.isLooping(), "missing resource must not produce a looping player");
        assertDoesNotThrow(m::play);
        assertDoesNotThrow(m::stop);
        assertDoesNotThrow(() -> m.setVolume(0.5));
        assertEquals(0.5, m.getVolume(), 1e-9, "fallback still tracks the requested volume");
    }

    @Test
    void validResourceLoopsIndefinitely() {
        MusicSystem m = new MusicSystem(VALID);
        assertTrue(m.isLooping(), "BGM should loop (cycleCount == INDEFINITE)");
    }

    @Test
    void setVolumeUpdatesPlayerVolume() {
        MusicSystem m = new MusicSystem(VALID, 1.0);
        m.setVolume(0.4);
        assertEquals(0.4, m.getVolume(), 1e-9);
    }

    @Test
    void initialVolumeIsHonored() {
        MusicSystem m = new MusicSystem(VALID, 0.25);
        assertEquals(0.25, m.getVolume(), 1e-9);
    }
}
