package Models;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameObject {
    private String url;
    private String title;
    private String price;
    private String discount;

    GameObject(String url, String title, String price, String discount) {
        this.url = url;
        this.title = title;
        this.price = price;
        this.discount = discount;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return price;
    }


    public String getDiscount() {
        return discount;
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

    public int getDiscountInt() {
        //Regular expression to extract numbers from string
        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher matcher = pattern.matcher(discount);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group());
        }
        return 0;
    }
}
