import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Objects;

import javafx.application.Application;

public class main extends Application{
    private static final String PRIMARY_STAGE_TITLE = "Steam Game Scraper";
    private static final double PRIMARY_STAGE_WIDTH = 800;
    private static final double PRIMARY_STAGE_HEIGHT = 600;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxml/main.fxml")));
        primaryStage.setTitle(PRIMARY_STAGE_TITLE);
        primaryStage.setMinWidth(PRIMARY_STAGE_WIDTH);
        primaryStage.setMinHeight(PRIMARY_STAGE_HEIGHT);
        primaryStage.setMaximized(true);

        Scene scene = new Scene(root);
        scene.getStylesheets().add("css/main.css");

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

