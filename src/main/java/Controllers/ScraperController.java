package Controllers;

import Models.RedditScraper;
import Models.SteamGame;
import Models.SteamGames;
import Models.SteamScraper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;

public class ScraperController implements Initializable {
    private RedditScraper redditGameScraper;
    private SteamScraper steamGameScraper;
    private SteamGames listOfSteamGames;

    @FXML
    private ListView<String> gameListView;

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
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HashSet<String> subRedditUrls = new HashSet<>();
        subRedditUrls.add("https://www.reddit.com/r/GameDeals/");
        subRedditUrls.add("https://www.reddit.com/r/steamdeals/");
        redditGameScraper = new RedditScraper(subRedditUrls);
    }
}
