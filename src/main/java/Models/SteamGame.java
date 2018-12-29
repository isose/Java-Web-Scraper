package Models;

import javafx.scene.image.Image;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SteamGame extends GameObject{
    private String description;
    private String rating;
    private String imageUrl;
    private Image image;

    public SteamGame(String url, String title, String description, String rating, String price, String discount, String imageUrl) {
        super(url, title, price, discount);
        this.description = description;
        this.rating = rating;
        this.imageUrl = imageUrl;
        try {
            this.image = new Image(imageUrl);
        } catch (Exception e) {
            this.image = null;
        }
    }

    public String getDescription() {
        return description;
    }

    public String getRating() {
        return rating;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public float getPriceFloat() {
        return super.getPriceFloat();
    }

    @Override
    public int getDiscountInt() {
        return super.getDiscountInt();
    }

    public String getPriceString() { return super.getPrice(); }

    public String getDiscountString() {return super.getDiscount();}

    public Image getImage() {
        return image;
    }
}
