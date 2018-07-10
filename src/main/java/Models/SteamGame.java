package Models;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SteamGame {
    private String url;
    private String title;
    private String description;
    private String rating;
    private String price;
    private String discount;
    private String imageUrl;

    public SteamGame(String url, String title, String description, String rating, String price, String discount, String imageUrl) {
        this.url = url;
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.price = price;
        this.discount = discount;
        this.imageUrl = imageUrl;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getRating() {
        return rating;
    }

    public String getPriceString() {
        return price;
    }

    public float getPriceFloat() {
        //Regular expression to extract float from string
        Pattern pattern = Pattern.compile("[0-9]+\\.[0-9]+");
        Matcher matcher = pattern.matcher(price);
        if (matcher.find()) {
            return Float.parseFloat(matcher.group());
        }
        return 0;
    }

    public String getDiscountString() {
        return discount;
    }

    public int getDiscountInt() {
        //Regular expression to extract numbers from string
        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher matcher = pattern.matcher(discount);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group());
        }
        return 0;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
