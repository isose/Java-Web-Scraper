package Controllers;

import Models.RedditScraper;
import Models.SteamGame;
import Models.SteamGames;
import Models.SteamScraper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.ResourceBundle;

public class ScraperController implements Initializable {
    private RedditScraper redditGameScraper;
    private SteamScraper steamGameScraper;
    private SteamGames listOfSteamGames;

    @FXML
    private ListView<String> gameListView;

    @FXML
    private GamePopUpController gameController = new GamePopUpController();

    public void scrape(ActionEvent event) {
        HashSet<String> steamUrls = new HashSet<>();
        try {
            steamUrls = redditGameScraper.getSteamUrls();
        } catch (IOException e) {
            e.printStackTrace();
        }

        steamGameScraper = new SteamScraper(steamUrls);
        try {
            steamGameScraper.scrapeGames();
        } catch (IOException e) {
            e.printStackTrace();
        }

        listOfSteamGames = steamGameScraper.getSteamGames();
        ObservableList<String> gameList = FXCollections.observableArrayList();
        ArrayList<SteamGame> list = listOfSteamGames.getSteamGames();
        for(SteamGame game : list) {
            String gameName = game.getName();
            gameList.add(gameName);
        }
        gameListView.setItems(gameList);

        gameListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String gameName = gameListView.getSelectionModel().getSelectedItem();
                SteamGame selectedGame = listOfSteamGames.getGame(gameName);
                Stage gameStage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("extraGameWindow.fxml"));
                Parent rootTwo = null;
                try {
                    rootTwo = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                gameStage.setTitle(selectedGame.getName());
                gameStage.setMaximized(false);

                Scene scene = new Scene(rootTwo);
                scene.getStylesheets().add("steamdata.css");

                gameStage.setScene(scene);
                gameStage.show();


                System.out.println(selectedGame.getImageUrl());

                gameController = loader.getController();

                gameController.setGameImage(new ImageView(selectedGame.getImageUrl()));
                gameController.setGameDescText(selectedGame.getDescription());
                gameController.setGameNameText(selectedGame.getName());
            }
        });

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HashSet<String> subRedditUrls = new HashSet<>();
        subRedditUrls.add("https://www.reddit.com/r/GameDeals/");
        subRedditUrls.add("https://www.reddit.com/r/steamdeals/");
        redditGameScraper = new RedditScraper(subRedditUrls);
    }
}
