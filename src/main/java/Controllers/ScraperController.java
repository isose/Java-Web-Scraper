package Controllers;

import Models.SteamGames;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;

public class ScraperController implements Initializable {
    private static final String SORTING_COMBOBOX_OPTION_ALPHABETICAL_ASCENDING = "Alphabetical (A-Z)";
    private static final String SORTING_COMBOBOX_OPTION_ALPHABETICAL_DESCENDING = "Alphabetical (Z-A)";
    private static final String SORTING_COMBOBOX_OPTION_PRICE_ASCENDING = "Price (Lowest)";
    private static final String SORTING_COMBOBOX_OPTION_PRICE_DESCENDING = "Price (Highest)";
    private static final String SORTING_COMBOBOX_OPTION_DISCOUNT_ASCENDING = "Discount (Lowest)";
    private static final String SORTING_COMBOBOX_OPTION_DISCOUNT_DESCENDING = "Discount (Highest)";

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
        displayItems();
    }

    public void onComboChanged(ActionEvent event) {
        String option = sortComboBox.getValue();
        switch (option) {
            case SORTING_COMBOBOX_OPTION_ALPHABETICAL_ASCENDING:
                steamGames.sortTitleAscending();
                break;
            case SORTING_COMBOBOX_OPTION_ALPHABETICAL_DESCENDING:
                steamGames.sortTitleDescending();
                break;
            case SORTING_COMBOBOX_OPTION_PRICE_ASCENDING:
                steamGames.sortPriceAscending();
                break;
            case SORTING_COMBOBOX_OPTION_PRICE_DESCENDING:
                steamGames.sortPriceDescending();
                break;
            case SORTING_COMBOBOX_OPTION_DISCOUNT_ASCENDING:
                steamGames.sortDiscountAscending();
                break;
            case SORTING_COMBOBOX_OPTION_DISCOUNT_DESCENDING:
                steamGames.sortDiscountDescending();
                break;
        }
        displayItems();
    }

    private void displayItems() {
        gameListViewController.setListView(steamGames.getSteamGames());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HashSet<String> subRedditUrls = new HashSet<>();
        subRedditUrls.add("https://www.reddit.com/r/GameDeals/");
        subRedditUrls.add("https://www.reddit.com/r/steamdeals/");
        redditGameScraper = new RedditScraper(subRedditUrls);
        ObservableList<String> optionsList = FXCollections.observableArrayList(SORTING_COMBOBOX_OPTION_ALPHABETICAL_ASCENDING,
                                                                                    SORTING_COMBOBOX_OPTION_ALPHABETICAL_DESCENDING,
                                                                                    SORTING_COMBOBOX_OPTION_PRICE_ASCENDING,
                                                                                    SORTING_COMBOBOX_OPTION_PRICE_DESCENDING,
                                                                                    SORTING_COMBOBOX_OPTION_DISCOUNT_ASCENDING,
                                                                                    SORTING_COMBOBOX_OPTION_DISCOUNT_DESCENDING );
        sortComboBox.setItems(optionsList);
        gameListViewController.setCellFactory();
    }
}