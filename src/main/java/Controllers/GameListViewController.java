package Controllers;

import Models.GameListViewCell;
import Models.SteamGame;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.ArrayList;

public class GameListViewController {
    @FXML
    private ListView<SteamGame> gameListView;

    public void setCellFactory() {
        gameListView.setCellFactory(e -> new GameListViewCell());
    }

    public void setListView(ArrayList<SteamGame> gameList) {
        gameListView.setItems(FXCollections.observableArrayList(gameList));
    }
}
