package Models;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

public class SteamScraper {
    private HashMap<String, String> steamCookies;
    private HashSet<String> listOfSteamUrls;
    private SteamGames steamGames;

    public SteamScraper(HashSet<String> listOfSteamUrls) {
        steamCookies = new HashMap<>();
        steamCookies.put("birthtime", "915177601");
        steamCookies.put("mature_content", "1");
        this.listOfSteamUrls = listOfSteamUrls;
        steamGames = new SteamGames();
    }

    public void scrapeGames() throws IOException {
        for (String steamUrl : listOfSteamUrls)
            steamGames.addGame(scrapeGame(getSteamDocument(steamUrl)));
    }

    public SteamGame scrapeGame(Document doc) {
        String name = scrapeName(doc);
        String description = scrapeDescription(doc);
        String rating = scrapeRating(doc);
        String price = scrapePrice(doc);
        String discount = scrapeDiscount(doc);
        String imageUrl = scrapeImageUrl(doc);
        return new SteamGame(doc.location(), name, description, rating, price, discount, imageUrl);
    }

    private Document getSteamDocument(String steamUrl) throws IOException {
        return Jsoup.connect(steamUrl).cookies(steamCookies).get();
    }

    private String scrapeName(Document doc) {
        Elements name = doc.select(".apphub_AppName");
        return name.text();
    }

    private String scrapeDescription(Document doc) {
        Elements description = doc.select(".game_description_snippet");
        return description.text();
    }

    private String scrapeRating(Document doc) {
        Elements rating = doc.select(".nonresponsive_hidden.responsive_reviewdesc");
        return rating.text();
    }

    private String scrapePrice(Document doc) {
        Element price = doc.selectFirst(".game_purchase_price.price,.discount_final_price");
        try {
            return price.text();
        } catch(NullPointerException e) {
            return "";
        }
    }

    private String scrapeDiscount(Document doc) {
        Element discount = doc.selectFirst(".discount_pct");
        try {
            return discount.text();
        } catch(NullPointerException e) {
            return "";
        }
    }

    private String scrapeImageUrl(Document doc) {
        Elements imageUrl = doc.select(".game_header_image_full");
        return imageUrl.attr("src");
    }

    public SteamGames getSteamGames() {
        return steamGames;
    }
}
