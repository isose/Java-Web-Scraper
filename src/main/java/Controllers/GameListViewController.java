package Controllers;

import Models.SteamGame;
import Models.SteamGameCellFactory;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.ArrayList;

public class GameListViewController {
    @FXML
    private ListView<SteamGame> steamGameListView;

    public void setListView(ArrayList<SteamGame> steamGames) {
        steamGameListView.setItems(FXCollections.observableArrayList(steamGames));
    }

    @FXML
    private void initialize() {
        steamGameListView.setCellFactory(new SteamGameCellFactory());
    }
}
