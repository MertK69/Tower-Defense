package ui;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class UIFXSettings {
		public static void initializeSettings(Stage stage, Scene scene)
		{
				stage.setScene(scene);
				stage.setTitle("Tower Defense - Made by MertK69");
				stage.setWidth(1200);
				stage.setHeight(840);
				stage.setResizable(false);
		}
}
