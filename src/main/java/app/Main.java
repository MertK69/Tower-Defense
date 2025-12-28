package app;

import javafx.application.Application;
import javafx.stage.Stage;
import ui.HelloFX;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        HelloFX ui = new HelloFX();

        stage.setTitle("Tower Defense");
        stage.setScene(ui.createScene());
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

