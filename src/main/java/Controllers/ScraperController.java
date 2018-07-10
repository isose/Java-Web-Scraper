package Controllers;

import Comparators.*;
import Models.RedditScraper;
import Models.SteamGame;
import Models.SteamGames;
import Models.SteamScraper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class ScraperController implements Initializable {
    private RedditScraper redditGameScraper;
    private SteamScraper steamGameScraper;
    private SteamGames steamGames;

    @FXML
    private GameListViewController gameListViewController;

    @FXML
    public ComboBox<String> sortComboBox;

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

        steamGames = steamGameScraper.getSteamGames();
        gameListViewController.setListView(steamGames.getSteamGames());
    }

    private void redisplayItems() {
        gameListViewController.clearListView();
        gameListViewController.setListView(steamGames.getSteamGames());
    }

    public void onComboChanged(ActionEvent event) {
        String option = sortComboBox.getValue();
        switch (option) {
            case "Alphabetical (A-Z)":
                steamGames.sortTitleAscending();
                break;
            case "Price (Lowest)":
                steamGames.sortPriceAscending();
                break;
            case "Discount (Lowest)":
                steamGames.sortDiscountAscending();
                break;
            case "Alphabetical (Z-A)":
                steamGames.sortTitleDescending();
                break;
            case "Price (Highest)":
                steamGames.sortPriceDescending();
                break;
            case "Discount (Highest)":
                steamGames.sortDiscountDescending();
                break;
        }
        redisplayItems();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HashSet<String> subRedditUrls = new HashSet<>();
        subRedditUrls.add("https://www.reddit.com/r/GameDeals/");
        subRedditUrls.add("https://www.reddit.com/r/steamdeals/");
        redditGameScraper = new RedditScraper(subRedditUrls);
        ObservableList<String> optionsList = FXCollections.observableArrayList("Alphabetical (A-Z)",
                                                                                    "Alphabetical (Z-A)",
                                                                                    "Price (Lowest)",
                                                                                    "Price (Highest)",
                                                                                    "Discount (Lowest)",
                                                                                    "Discount (Highest)");
        sortComboBox.setItems(optionsList);
    }
}