package Models;

import javafx.scene.image.Image;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

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
            // Open image url connection with User-Agent set to prevent 403 response from cloudflare urls
            URLConnection connection = new URL(imageUrl).openConnection();
            connection.setRequestProperty("User-Agent", System.getProperty("http.agent"));
            InputStream stream = connection.getInputStream();
            this.image = new Image(stream);
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

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (this == o) {
            return true;
        }

        return (o instanceof SteamGame) && ((SteamGame) o).getUrl().equals(this.getUrl());
    }

    @Override
    public int hashCode() {
        return this.getUrl().hashCode();
    }
}
