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
    private ArrayList<SteamGame> gameList = new ArrayList<>();

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

        SteamGames steamGames = steamGameScraper.getSteamGames();
        gameList = steamGames.getSteamGames();
        gameListViewController.setListView(gameList);

    }

    private void sortByAlphabeticalOrderAZ() {
        gameList.sort(new sortByAlphabeticalOrder(true));
        redisplaySortedItems();
    }

    private void sortByAlphabeticalOrderZA() {
        gameList.sort(new sortByAlphabeticalOrder(false));
        redisplaySortedItems();
    }

    private void sortByDiscountLowest() {
        gameList.sort(new sortByDiscount(false));
        redisplaySortedItems();
    }

    private void sortByDiscountHighest() {
        gameList.sort(new sortByDiscount(true));
        redisplaySortedItems();
    }

    private void sortByPriceLowest() {
        gameList.sort(new sortByPrice(false));
        redisplaySortedItems();
    }

    private void sortByPriceHighest() {
        gameList.sort(new sortByPrice(true));
        redisplaySortedItems();
    }

    private void redisplaySortedItems() {
        gameListViewController.clearListView();
        gameListViewController.setListView(gameList);
    }

    public void onComboChanged(ActionEvent event) {
        String option = sortComboBox.getValue();
        switch (option) {
            case "Alphabetical (A-Z)":
                sortByAlphabeticalOrderAZ();
                break;
            case "Price (Lowest)":
                sortByPriceLowest();
                break;
            case "Discount (Lowest)":
                sortByDiscountLowest();
                break;
            case "Alphabetical (Z-A)":
                sortByAlphabeticalOrderZA();
                break;
            case "Price (Highest)":
                sortByPriceHighest();
                break;
            case "Discount (Highest)":
                sortByDiscountHighest();
                break;
        }
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
