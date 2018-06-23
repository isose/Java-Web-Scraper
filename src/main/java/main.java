import Controllers.ScraperController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Objects;

import javafx.application.Application;

public class main extends Application{

    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("main.fxml")));
        primaryStage.setTitle("Hello World");
        Scene testScene = new Scene(root, 600, 400);
        testScene.getStylesheets().add("application.css");

        primaryStage.setScene(testScene);
        primaryStage.show();

        ScraperController Controller = new ScraperController();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

