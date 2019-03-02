package Models;

import javafx.scene.image.Image;

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
