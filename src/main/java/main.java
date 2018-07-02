import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Objects;

import javafx.application.Application;

public class main extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxml/main.fxml")));
        primaryStage.setTitle("Steam Game Scraper");
        primaryStage.setMaximized(true);

        Scene scene = new Scene(root);
        scene.getStylesheets().add("css/main.css");

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

