package Controllers;

import Comparators.sortByAlphabeticalOrder;
import Comparators.sortByDiscount;
import Comparators.sortByPrice;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class ScraperController implements Initializable {
    public Button scrapeBtn;
    private RedditScraper redditGameScraper;
    private SteamScraper steamGameScraper;
    private SteamGames listOfSteamGames;
    ArrayList<SteamGame> sortList = new ArrayList<>();

    @FXML
    private ListView<String> gameListView;

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

        listOfSteamGames = steamGameScraper.getSteamGames();
        ObservableList<String> gameList = FXCollections.observableArrayList();
        sortList = listOfSteamGames.getSteamGames();
        for(SteamGame game : sortList) {
            String gameName = game.getName();
            gameList.add(gameName);
        }
        gameListView.setItems(gameList);


    }

    public void sortByAlphabeticalOrder() {
        sortList.sort(new sortByAlphabeticalOrder());
        redisplaySortedItems();
    }

    public void sortByDiscount() {
        sortList.sort(new sortByDiscount());
        redisplaySortedItems();
    }

    public void sortByPrice() {
        sortList.sort(new sortByPrice());
        redisplaySortedItems();
    }

    private void redisplaySortedItems() {
        ObservableList<String> gameList = FXCollections.observableArrayList();
        for(SteamGame game : sortList) {
            String gameName = game.getName();
            gameList.add(gameName);
        }
        gameListView.getItems().clear();
        gameListView.setItems(gameList);
    }

    public void onComboChanged(ActionEvent event) {
        String option = sortComboBox.getValue();
        switch (option) {
            case "Alphabetical":
                sortByAlphabeticalOrder();
                break;
            case "Price":
                sortByPrice();
                break;
            case "Discount":
                sortByDiscount();
                break;
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HashSet<String> subRedditUrls = new HashSet<>();
        subRedditUrls.add("https://www.reddit.com/r/GameDeals/");
        subRedditUrls.add("https://www.reddit.com/r/steamdeals/");
        redditGameScraper = new RedditScraper(subRedditUrls);
        ObservableList<String> optionsList = FXCollections.observableArrayList("Alphabetical", "Price", "Discount");
        sortComboBox.setItems(optionsList);
    }
}
