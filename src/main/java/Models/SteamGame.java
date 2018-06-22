package Models;

public class SteamGame {
    private String url;
    private String name;
    private String description;
    private String rating;
    private String price;
    private String discount;
    private String imageUrl;

    public SteamGame(String url, String name, String description, String rating, String price, String discount, String imageUrl) {
        this.url = url;
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.price = price;
        this.discount = discount;
        this.imageUrl = imageUrl;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getRating() {
        return rating;
    }

    public String getPrice() {
        return price;
    }

    public String getDiscount() {
        return discount;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\n";
    }


}
