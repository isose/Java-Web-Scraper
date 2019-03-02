package Scrapers;

import Models.SteamGame;
import Models.SteamGames;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class SteamScraper {
    private static SteamScraper steamScraper = new SteamScraper();
    private static Map<String, String> steamCookies;
    static {
        Map<String, String> steamCookies = new HashMap<>();
        steamCookies.put("birthtime", "915177601");
        steamCookies.put("mature_content", "1");
        SteamScraper.steamCookies = Collections.unmodifiableMap(steamCookies);
    }

    private SteamGames steamGames;

    private SteamScraper() {
        steamGames = new SteamGames();
    }

    public static SteamScraper getSteamScraper() {
        return steamScraper;
    }

    public void scrapeGames(HashSet<String> listOfSteamUrls) {
        listOfSteamUrls.parallelStream().forEach((steamUrl) -> {
            try {
                SteamGame steamGame = scrapeGame(getSteamDocument(steamUrl));
                if (!steamGames.getSteamGameList().contains(steamGame)) {
                    steamGames.addGame(steamGame);
                }
            } catch (IOException e) {
                System.out.print("Jsoup could not connect to steamUrl");
            }
        });
    }

    public SteamGame scrapeGame(Document doc) {
        String name = scrapeName(doc);
        String description = scrapeDescription(doc);
        String rating = scrapeRating(doc);
        String[] priceAndDiscount = scrapePriceAndDiscount(doc);
        String imageUrl = scrapeImageUrl(doc);
        return new SteamGame(doc.location(), name, description, rating, priceAndDiscount[0], priceAndDiscount[1], imageUrl);
    }

    private Document getSteamDocument(String steamUrl) throws IOException {
        return Jsoup.connect(steamUrl).cookies(steamCookies).get();
    }

    private String scrapeName(Document doc) {
        return doc.select(".apphub_AppName").text();
    }

    private String scrapeDescription(Document doc) {
        return doc.select(".game_description_snippet").text();
    }

    private String scrapeRating(Document doc) {
        return doc.select(".nonresponsive_hidden.responsive_reviewdesc").text();
    }

    private String[] scrapePriceAndDiscount(Document doc) {
        Element price = doc.selectFirst(".game_purchase_price.price,.discount_final_price");
        if (price == null) {
            return new String[]{"", ""};
        }
        //Scrapes for discount percent if game is on sale
        String[] priceAndDiscount = {price.text(), ""};
        if (price.className().equals("discount_final_price")) {
            priceAndDiscount[1] = doc.selectFirst(".discount_pct").text();
        }
        return priceAndDiscount;
    }

    private String scrapeImageUrl(Document doc) {
        return doc.select(".game_header_image_full").attr("src");
    }

    public SteamGames getSteamGames() {
        return steamGames;
    }
}
