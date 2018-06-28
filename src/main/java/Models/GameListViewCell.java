package Models;

import javafx.scene.control.ListCell;

public class GameListViewCell extends ListCell<SteamGame> {

    @Override
    public void updateItem(SteamGame steamGame, boolean empty) {
        super.updateItem(steamGame, empty);
        if (steamGame != null) {
            GameItem gameItem = new GameItem();
            gameItem.setGameInfo(steamGame);
            setGraphic(gameItem.getGameHBox());
        }
    }
}
