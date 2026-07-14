package ui.UILayers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import game.engine.GameEngine;
import game.path.Pathtype;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.StackPane;

/**
 * Headless unit tests for {@link SettingsOverlayCreator} (TT-5 AC-6, AC-7 and Decision 6).
 * Verifies the overlay can be added to / removed from a parent's children, that the
 * "Back" button invokes the supplied callback, and that the sliders propagate volume
 * changes to the engine in real time. TDD RED — the creator does not exist yet.
 */
class SettingsOverlayCreatorTest {

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

    private GameEngine newEngine() {
        return new GameEngine(Pathtype.EASY, 1, 100);
    }

    private void collectSliders(Node node, List<Slider> out) {
        if (node instanceof Slider s) out.add(s);
        if (node instanceof Parent p) {
            for (Node child : p.getChildrenUnmodifiable()) collectSliders(child, out);
        }
    }

    private Button findButton(Node node, String text) {
        if (node instanceof Button b && text.equals(b.getText())) return b;
        if (node instanceof Parent p) {
            for (Node child : p.getChildrenUnmodifiable()) {
                Button found = findButton(child, text);
                if (found != null) return found;
            }
        }
        return null;
    }

    @Test
    void createsStackPaneOverlay() {
        StackPane overlay = new SettingsOverlayCreator().create_settings_overlay(newEngine(), () -> {});
        assertNotNull(overlay);
    }

    @Test
    void overlayCanBeAddedToAndRemovedFromParent() {
        StackPane parent = new StackPane();
        StackPane overlay = new SettingsOverlayCreator().create_settings_overlay(newEngine(), () -> {});

        int before = parent.getChildren().size();
        parent.getChildren().add(overlay);
        assertEquals(before + 1, parent.getChildren().size(), "overlay should be added on top");

        parent.getChildren().remove(overlay);
        assertEquals(before, parent.getChildren().size(), "overlay should be removed");
    }

    @Test
    void backButtonRunsSuppliedCallback() {
        boolean[] backCalled = { false };
        StackPane overlay = new SettingsOverlayCreator()
                .create_settings_overlay(newEngine(), () -> backCalled[0] = true);

        Button back = findButton(overlay, "Back");
        assertNotNull(back, "overlay must contain a 'Back' button");
        back.fire();
        assertTrue(backCalled[0], "clicking Back must run the supplied callback");
    }

    @Test
    void overlayHasTwoVolumeSliders() {
        StackPane overlay = new SettingsOverlayCreator().create_settings_overlay(newEngine(), () -> {});
        List<Slider> sliders = new ArrayList<>();
        collectSliders(overlay, sliders);
        assertEquals(2, sliders.size(), "overlay must have a Music slider and an SFX slider");
    }

    @Test
    void musicSliderPropagatesToEngineInRealTime() {
        GameEngine engine = newEngine();
        StackPane overlay = new SettingsOverlayCreator().create_settings_overlay(engine, () -> {});
        List<Slider> sliders = new ArrayList<>();
        collectSliders(overlay, sliders);
        // Slider order: Music first, SFX second.
        sliders.get(0).setValue(40);
        assertEquals(0.4, engine.getMusicVolume(), 1e-9);
    }

    @Test
    void sfxSliderPropagatesToEngineInRealTime() {
        GameEngine engine = newEngine();
        StackPane overlay = new SettingsOverlayCreator().create_settings_overlay(engine, () -> {});
        List<Slider> sliders = new ArrayList<>();
        collectSliders(overlay, sliders);
        sliders.get(1).setValue(20);
        assertEquals(0.2, engine.getSfxVolume(), 1e-9);
    }
}
