package Controllers;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class GamePopUpController {
    public GamePopUpController() {

    }
    @FXML
    public Text recentRatingText;

    @FXML
    public Text overallRatingText;

    @FXML
    public Text gameNameText;

    @FXML
    public Text gameDescText;

    @FXML
    public ImageView gameImage;

    public void setRecentRatingText(String recentRatingText) {
        this.recentRatingText.setText(recentRatingText);
    }

    public void setOverallRatingText(String overallRatingText) {
        this.overallRatingText.setText(overallRatingText);
    }

    public void setGameNameText(String gameNameText) {
        this.gameNameText.setText(gameNameText);
    }

    public void setGameDescText(String gameDescText) {
        this.gameDescText.setText(gameDescText);
    }

    public void setGameImage(ImageView gameImage) {
        this.gameImage = gameImage;
    }
}
