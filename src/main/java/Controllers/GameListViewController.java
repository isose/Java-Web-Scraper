package Controllers;

import Models.SteamGame;
import Models.SteamGameCellFactory;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.ArrayList;

public class GameListViewController {
    @FXML
    private ListView<SteamGame> gameListView;

    public void setListView(ArrayList<SteamGame> gameList) {
        gameListView.setItems(FXCollections.observableArrayList(gameList));
    }

    @FXML
    private void initialize() {
        gameListView.setCellFactory(new SteamGameCellFactory());
    }
}
