import Controllers.ScraperController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Map;
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

    private static boolean isGameFree(String gameUrl, Map<String, String> cookies) throws IOException {
        String freeGameText = "Install Game";
        String freeToPlay = "Play Game";
        Document doc = Jsoup.connect(gameUrl).cookies(cookies).get();
        Elements priceBox = doc.select("[class~=btnv6_green_white_innerfade]");
        return (priceBox.text().equals(freeGameText) || priceBox.text().equals(freeToPlay));
    }

    private static void printDescription(String gameUrl, Map<String, String> cookies) throws IOException{
        Document doc = Jsoup.connect(gameUrl).cookies(cookies).get();
        Elements steamUrls = doc.select(".game_description_snippet");
        System.out.println(steamUrls.text());


    }


}

