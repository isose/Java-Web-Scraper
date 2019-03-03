package Models;

import com.sun.javafx.scene.control.skin.ScrollBarSkin;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class SteamGameListCell extends ListCell<SteamGame> {
    private String gameUrl;
    @FXML private Label gameTitle;
    @FXML private Label gameDescription;
    @FXML private Label gameRating;
    @FXML private Label gamePrice;
    @FXML private Label gameDiscount;
    @FXML private ImageView gameImage;
    @FXML private VBox steamGameImageVBox;
    @FXML private VBox steamGameInfoVBox;

    public SteamGameListCell() {
        try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/SteamGameListCell.fxml"));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);
        fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateItem(SteamGame item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setText(null);
            setContentDisplay(ContentDisplay.TEXT_ONLY);
        } else {
            setGameInfo(item);
            steamGameInfoVBox.prefWidthProperty().bind(getListView().widthProperty().subtract(steamGameImageVBox.getMaxWidth() + ScrollBarSkin.DEFAULT_WIDTH + 15));
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        }
    }

    public void setGameInfo(SteamGame steamGame) {
        gameUrl = steamGame.getUrl();
        gameTitle.setText(steamGame.getTitle());
        gameDescription.setText(steamGame.getDescription());
        gameRating.setText(steamGame.getRating());
        gamePrice.setText(steamGame.getPriceString());
        gameDiscount.setText(steamGame.getDiscountString());
        gameImage.setImage(steamGame.getImage());
    }

    public void openUrl() {
        try {
            Desktop.getDesktop().browse(new URI(gameUrl));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
