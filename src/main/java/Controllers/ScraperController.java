package Controllers;

import Scrapers.RedditScraper;
import Scrapers.SteamScraper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ScraperController implements Initializable {
    private static final String SORTING_COMBOBOX_OPTION_ALPHABETICAL_ASCENDING = "Alphabetical (A-Z)";
    private static final String SORTING_COMBOBOX_OPTION_ALPHABETICAL_DESCENDING = "Alphabetical (Z-A)";
    private static final String SORTING_COMBOBOX_OPTION_PRICE_ASCENDING = "Price (Lowest)";
    private static final String SORTING_COMBOBOX_OPTION_PRICE_DESCENDING = "Price (Highest)";
    private static final String SORTING_COMBOBOX_OPTION_DISCOUNT_ASCENDING = "Discount (Lowest)";
    private static final String SORTING_COMBOBOX_OPTION_DISCOUNT_DESCENDING = "Discount (Highest)";

    private static RedditScraper redditGameScraper;
    private static SteamScraper steamGameScraper;

    @FXML
    private GameListViewController gameListViewController;

    @FXML
    public ComboBox<String> sortComboBox;

    public void refresh(ActionEvent event) {
        scrape();
    }

    public void onComboChanged(ActionEvent event) {
        String option = sortComboBox.getValue();
        switch (option) {
            case SORTING_COMBOBOX_OPTION_ALPHABETICAL_ASCENDING:
                steamGameScraper.getSteamGames().sortTitleAscending();
                break;
            case SORTING_COMBOBOX_OPTION_ALPHABETICAL_DESCENDING:
                steamGameScraper.getSteamGames().sortTitleDescending();
                break;
            case SORTING_COMBOBOX_OPTION_PRICE_ASCENDING:
                steamGameScraper.getSteamGames().sortPriceAscending();
                break;
            case SORTING_COMBOBOX_OPTION_PRICE_DESCENDING:
                steamGameScraper.getSteamGames().sortPriceDescending();
                break;
            case SORTING_COMBOBOX_OPTION_DISCOUNT_ASCENDING:
                steamGameScraper.getSteamGames().sortDiscountAscending();
                break;
            case SORTING_COMBOBOX_OPTION_DISCOUNT_DESCENDING:
                steamGameScraper.getSteamGames().sortDiscountDescending();
                break;
        }
        displayItems();
    }

    private void scrape() {
        steamGameScraper.scrapeGames(redditGameScraper.scrapeSteamUrls());
        displayItems();
    }

    private void displayItems() {
        gameListViewController.setListView(steamGameScraper.getSteamGames().getSteamGameList());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        redditGameScraper = RedditScraper.getRedditScraper();
        steamGameScraper = SteamScraper.getSteamScraper();
        scrape();
        sortComboBox.setItems(FXCollections.observableArrayList(SORTING_COMBOBOX_OPTION_ALPHABETICAL_ASCENDING,
                                                                SORTING_COMBOBOX_OPTION_ALPHABETICAL_DESCENDING,
                                                                SORTING_COMBOBOX_OPTION_PRICE_ASCENDING,
                                                                SORTING_COMBOBOX_OPTION_PRICE_DESCENDING,
                                                                SORTING_COMBOBOX_OPTION_DISCOUNT_ASCENDING,
                                                                SORTING_COMBOBOX_OPTION_DISCOUNT_DESCENDING));
    }
}