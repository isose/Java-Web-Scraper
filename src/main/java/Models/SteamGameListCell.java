package Models;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class SteamGameListCell extends ListCell<SteamGame> {
    private String gameUrl;
    @FXML private HBox gameHBox;
    @FXML private Label gameTitle;
    @FXML private Label gameDescription;
    @FXML private Label gameRating;
    @FXML private Label gamePrice;
    @FXML private Label gameDiscount;
    @FXML private ImageView gameImage;

    public SteamGameListCell() {
        try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/GameListCellItem.fxml"));
        fxmlLoader.setController(this);
        fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateItem(SteamGame item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setGraphic(null);
        } else {
            setGameInfo(item);
            setGraphic(gameHBox);
        }
    }

    public void setGameInfo(SteamGame steamGame) {
        gameUrl = steamGame.getUrl();
        gameTitle.setText(steamGame.getTitle());
        gameDescription.setText(steamGame.getDescription());
        gameRating.setText(steamGame.getRating());
        gamePrice.setText(steamGame.getPriceString());
        gameDiscount.setText(steamGame.getDiscountString());

        if (!steamGame.getImageUrl().isEmpty()) {
            gameImage.setImage(new Image(steamGame.getImageUrl()));
        } else {
            gameImage.setImage(new Image("not_found.jpg"));
        }
    }

    public void openUrl() {
        try {
            Desktop.getDesktop().browse(new URI(gameUrl));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
