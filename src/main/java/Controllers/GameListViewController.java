package Controllers;

import Models.SteamGame;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.ArrayList;


public class GameListViewController {
    @FXML
    private ListView<String> gameListView;

    public void setItems(ArrayList<SteamGame> gameList) {
        ObservableList<String> gameObservableList = FXCollections.observableArrayList();
        for(SteamGame game : gameList) {
            String gameName = game.getName();
            gameObservableList.add(gameName);
        }
        gameListView.setItems(gameObservableList);
    }
}
