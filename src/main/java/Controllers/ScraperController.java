package Controllers;

import Models.RedditScraper;
import Models.SteamGame;
import Models.SteamGames;
import Models.SteamScraper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;

public class ScraperController implements Initializable {
    private RedditScraper redditGameScraper;
    private SteamScraper steamGameScraper;
    @FXML
    private GameListViewController gameListViewController;

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

        SteamGames steamGames = steamGameScraper.getSteamGames();
        ArrayList<SteamGame> gameList = steamGames.getSteamGames();
        gameListViewController.setListView(gameList);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HashSet<String> subRedditUrls = new HashSet<>();
        subRedditUrls.add("https://www.reddit.com/r/GameDeals/");
        subRedditUrls.add("https://www.reddit.com/r/steamdeals/");
        redditGameScraper = new RedditScraper(subRedditUrls);
    }
}
