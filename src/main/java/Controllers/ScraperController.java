package Controllers;

import Models.RedditScraper;
import Models.SteamGame;
import Models.SteamGames;
import Models.SteamScraper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class ScraperController {

    public Text title;
    private String gameDealsSub = "https://www.reddit.com/r/GameDeals/";
    private String gameFindingsSub = "https://www.reddit.com/r/steamdeals/";
    private HashSet<String> subRedditUrls = new HashSet<>();

    private RedditScraper redditGameScraper;
    private SteamScraper steamGameScraper;
    private SteamGames listOfSteamGames;

    @FXML
    private Button scrapeBtn;
    @FXML
    private Button exitBtn;
    @FXML
    private ListView<String> gameListView;



    public ScraperController() {
        subRedditUrls.add(gameDealsSub);
        subRedditUrls.add(gameFindingsSub);
        redditGameScraper = new RedditScraper(subRedditUrls);
    }

    public void scrape(ActionEvent event) {
        HashSet<String> steamUrls = new HashSet<>();

        try {
            steamUrls = redditGameScraper.getSteamUrls();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(steamUrls.isEmpty()) {
            System.out.println("No subreddit links to scrape....");
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
            System.out.println(gameName);
            gameList.add(gameName);
        }

        gameListView.setItems(gameList);
    }

    public void exit(ActionEvent event) {
        Stage stage = (Stage) exitBtn.getScene().getWindow();
        stage.close();
    }




}
