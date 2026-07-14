package game.sound;

import java.net.URL;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Looping background-music (BGM) player for TT-5.
 *
 * <p>Unlike {@link SoundSystems} (short {@link javafx.scene.media.AudioClip} SFX), the BGM is a
 * streamed, indefinitely-looping {@link MediaPlayer}. Construction is defensive: if the media
 * resource is missing or fails to load, the system degrades to a silent no-op instead of crashing
 * game construction (TT-5 edge case / plan-review TST-M1). Music volume is tracked independently of
 * the SFX volume so the two overlay sliders control separate audio channels (AC-3).</p>
 */
public class MusicSystem {

    private MediaPlayer player;
    private double volume;

    public MusicSystem(String resourcePath) {
        this(resourcePath, 1.0);
    }

    public MusicSystem(String resourcePath, double volume) {
        this.volume = volume;
        try {
            URL resource = getClass().getResource(resourcePath);
            if (resource == null) {
                throw new IllegalArgumentException("BGM resource not found: " + resourcePath);
            }
            Media media = new Media(resource.toExternalForm());
            this.player = new MediaPlayer(media);
            this.player.setCycleCount(MediaPlayer.INDEFINITE);
            this.player.setVolume(volume);
        } catch (Exception bgmLoadFailure) {
            // Defensive no-op fallback: the game must still start without BGM.
            System.err.println("MusicSystem: BGM unavailable, running silent — " + bgmLoadFailure.getMessage());
            this.player = null;
        }
    }

    /** Starts (or resumes) looping playback. Safe no-op when BGM failed to load. */
    public void play() {
        if (player != null) {
            try {
                player.play();
            } catch (Exception ignored) {
                // playback is best-effort; never propagate audio failures into gameplay
            }
        }
    }

    /** Sets the BGM volume live (0.0–1.0). Tracked even in the silent fallback. */
    public void setVolume(double newVolume) {
        this.volume = newVolume;
        if (player != null) {
            player.setVolume(newVolume);
        }
    }

    /** @return the current BGM volume; reflects the live player volume when available. */
    public double getVolume() {
        return player != null ? player.getVolume() : volume;
    }

    /** @return true when a real looping player is active (cycleCount == INDEFINITE). */
    public boolean isLooping() {
        return player != null && player.getCycleCount() == MediaPlayer.INDEFINITE;
    }

    /** Stops and releases the player (call on scene teardown). Safe no-op when silent. */
    public void stop() {
        if (player != null) {
            try {
                player.stop();
                player.dispose();
            } catch (Exception ignored) {
                // teardown is best-effort
            } finally {
                player = null;
            }
        }
    }
}
