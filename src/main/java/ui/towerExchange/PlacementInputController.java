package ui.towerExchange;

import game.engine.GameEngine;
import game.tower.TowerType;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import util.Vector2;

/**
 * Shared, persistent input handling for the tower-placement preview flow
 * (mouse-move follow, left-click confirm, right-click/ESC cancel).
 * Replaces the one-shot MOUSE_CLICKED filter previously duplicated in
 * FirstStrategy/SecondStrategy.
 */
public final class PlacementInputController {

		private static EventHandler<MouseEvent> activeMouseMovedFilter = null;
		private static EventHandler<MouseEvent> activeMouseClickedFilter = null;
		private static EventHandler<KeyEvent> activeKeyPressedFilter = null;
		private static Scene activeScene = null;

		private PlacementInputController()
		{}

		public static void begin(Scene scene, Pane place, GameEngine engine, TowerType type)
		{
				cleanup();

				scene.setCursor(Cursor.CROSSHAIR);
				engine.startPlacementPreview(type);

				activeScene = scene;

				activeMouseMovedFilter = mouseEvent -> {
						Point2D localPoint = place.sceneToLocal(mouseEvent.getSceneX(), mouseEvent.getSceneY());
						if (place.getBoundsInLocal().contains(localPoint))
						{
								engine.updatePlacementPreviewPosition(new Vector2(localPoint.getX(), localPoint.getY()));
						}
				};

				activeMouseClickedFilter = mouseEvent -> {
						if (mouseEvent.getButton() == MouseButton.SECONDARY)
						{
								engine.cancelPlacementPreview();
								cleanup();
								mouseEvent.consume();
								return;
						}

						Point2D localPoint = place.sceneToLocal(mouseEvent.getSceneX(), mouseEvent.getSceneY());
						boolean clickedInsidePane = mouseEvent.getButton() == MouseButton.PRIMARY
										&& place.getBoundsInLocal().contains(localPoint);
						if (clickedInsidePane)
						{
								// Only tear down the input filters once placement actually succeeds --
								// an invalid click keeps the preview active so the player can retry
								// (AC 5 only requires "does not place", see plan Edge Cases #4).
								boolean placed = engine.confirmPlacementPreview();
								if (placed)
								{
										cleanup();
								}
						}
						mouseEvent.consume();
				};

				activeKeyPressedFilter = keyEvent -> {
						if (keyEvent.getCode() == KeyCode.ESCAPE)
						{
								engine.cancelPlacementPreview();
								cleanup();
						}
				};

				scene.addEventFilter(MouseEvent.MOUSE_MOVED, activeMouseMovedFilter);
				scene.addEventFilter(MouseEvent.MOUSE_CLICKED, activeMouseClickedFilter);
				scene.addEventFilter(KeyEvent.KEY_PRESSED, activeKeyPressedFilter);
		}

		public static void cleanup()
		{
				if (activeScene == null) return;

				if (activeMouseMovedFilter != null) activeScene.removeEventFilter(MouseEvent.MOUSE_MOVED, activeMouseMovedFilter);
				if (activeMouseClickedFilter != null) activeScene.removeEventFilter(MouseEvent.MOUSE_CLICKED, activeMouseClickedFilter);
				if (activeKeyPressedFilter != null) activeScene.removeEventFilter(KeyEvent.KEY_PRESSED, activeKeyPressedFilter);

				activeScene.setCursor(Cursor.DEFAULT);

				activeMouseMovedFilter = null;
				activeMouseClickedFilter = null;
				activeKeyPressedFilter = null;
				activeScene = null;
		}
}
