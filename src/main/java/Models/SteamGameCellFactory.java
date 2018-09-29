package Models;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class SteamGameCellFactory implements Callback<ListView<SteamGame>, ListCell<SteamGame>> {

    @Override
    public ListCell<SteamGame> call(ListView<SteamGame> param) {
        return new SteamGameListCell();
    }
}
