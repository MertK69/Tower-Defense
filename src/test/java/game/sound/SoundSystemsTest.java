package game.sound;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javafx.scene.media.AudioClip;

/**
 * Headless unit tests for {@link SoundSystems} live volume control (TT-5 AC-2, AC-8).
 * Written before the {@code setVolume}/{@code getVolume} implementation exists (TDD RED).
 */
class SoundSystemsTest {

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

    @SuppressWarnings("unchecked")
    private Map<?, AudioClip> mapField(SoundSystems s, String name) throws Exception {
        Field f = SoundSystems.class.getDeclaredField(name);
        f.setAccessible(true);
        return (Map<?, AudioClip>) f.get(s);
    }

    private void assertAllClipsAtVolume(SoundSystems s, double expected) throws Exception {
        for (String mapName : new String[] { "TowerSounds", "EnemySounds", "SpecialAttackSounds" }) {
            for (AudioClip clip : mapField(s, mapName).values()) {
                assertEquals(expected, clip.getVolume(), 1e-9,
                        "clip in " + mapName + " should report the updated volume");
            }
        }
    }

    @Test
    void getVolumeReturnsConstructorValue() {
        SoundSystems s = new SoundSystems(0.3);
        assertEquals(0.3, s.getVolume(), 1e-9);
    }

    @Test
    void setVolumeUpdatesGetVolume() {
        SoundSystems s = new SoundSystems(0.5);
        s.setVolume(0.8);
        assertEquals(0.8, s.getVolume(), 1e-9);
    }

    @Test
    void setVolumePropagatesToEveryClipInAllThreeMaps() throws Exception {
        SoundSystems s = new SoundSystems(0.5);
        s.setVolume(0.2);
        assertAllClipsAtVolume(s, 0.2);
    }

    @Test
    void constructorVolumeIsAppliedToClips() throws Exception {
        SoundSystems s = new SoundSystems(0.75);
        assertAllClipsAtVolume(s, 0.75);
    }
}
