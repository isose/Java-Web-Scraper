package Controllers;

import Models.GameListViewCell;
import Models.SteamGame;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.ArrayList;

public class GameListViewController {
    @FXML
    private ListView<SteamGame> gameListView;
    private ObservableList<SteamGame> observableList = FXCollections.observableArrayList();

    public void setListView(ArrayList<SteamGame> gameList) {
        observableList.setAll(gameList);
        gameListView.setItems(observableList);
        gameListView.setCellFactory(e -> new GameListViewCell());
    }

    public void clearListView() {
        gameListView.getItems().clear();
    }
}
