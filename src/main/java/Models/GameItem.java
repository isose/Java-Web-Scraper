package Models;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class GameItem {
    private String gameUrl;
    @FXML private HBox gameHBox;
    @FXML private Label gameTitle;
    @FXML private Label gameDescription;
    @FXML private Label gameRating;
    @FXML private Label gamePrice;
    @FXML private Label gameDiscount;
    @FXML private ImageView gameImage;

    public GameItem() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/GameListCellItem.fxml"));
        fxmlLoader.setController(this);
        try {
            gameHBox = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setGameInfo(SteamGame steamGame) {
        gameUrl = steamGame.getUrl();
        gameTitle.setText(steamGame.getTitle());
        gameDescription.setText(steamGame.getDescription());
        gameRating.setText(steamGame.getRating());
        gamePrice.setText(steamGame.getPrice());
        gameDiscount.setText(steamGame.getDiscount());

        Image image;
        if (!steamGame.getImageUrl().isEmpty()) {
            image = new Image(steamGame.getImageUrl());
        } else {
            image = new Image("not_found.jpg");
        }
        gameImage.setImage(image);
    }

    public HBox getGameHBox() {
        return gameHBox;
    }

    public void openUrl() {
        try {
            Desktop.getDesktop().browse(new URI(gameUrl));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
