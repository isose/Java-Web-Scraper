package test;

import Comparators.sortGame;
import Models.RedditScraper;
import Models.SteamGame;
import Models.SteamScraper;
import com.sun.javadoc.SeeTag;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SteamScraperTest {
    private ArrayList<SteamGame> gameList = new ArrayList<>();
    private SteamScraper steamScraper;
    private Document freeGameDoc, paidGameDoc, discountGameDoc, subRedditOne, subRedditTwo;
    private SteamGame cheapGame;
    private SteamGame middleGame;
    private SteamGame expensiveGame;


    @BeforeEach
    public void setUp() {
        cheapGame = new SteamGame("placeholder.com",
                "Cheapest Game",
                "This game is cheap",
                "100% of people say this game is cheap",
                "CDN$ 0.99",
                "-90%",
                "testimage");

        middleGame = new SteamGame("abcPlaceHold.com",
                "Middle Game",
                "This game isn't so expensive",
                "90% of people say this game is fair",
                "CDN$ 1.99",
                "-75%",
                "testimage2");

        expensiveGame = new SteamGame("testingPaceHold.com",
                "Expensive Game",
                "This game is really expensive",
                "25% of people said this game made their wallet die",
                "CDN$ 100.99",
                "-80%",
                "testImage3");
        gameList.add(cheapGame);
        gameList.add(middleGame);
        gameList.add(expensiveGame);
        steamScraper = new SteamScraper(new HashSet<>());
        try {
            File input = new File("./src/main/java/test/testResources/Path of Exile on Steam.html");
            freeGameDoc = Jsoup.parse(input, null);
            input = new File("./src/main/java/test/testResources/Tom Clancy's Rainbow Six® Siege on Steam.html");
            paidGameDoc = Jsoup.parse(input, null);
            input = new File("./src/main/java/test/testResources/Save 75% on Borderlands 2 on Steam.html");
            discountGameDoc = Jsoup.parse(input, null);
            subRedditOne = Jsoup.connect("https://www.reddit.com/r/GameDeals/").get();
            subRedditTwo = Jsoup.connect("https://www.reddit.com/r/steamdeals/").get();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    @Test
    public void testFreeGame() {
        SteamGame freeGame = steamScraper.scrapeGame(freeGameDoc);
        assertNotNull(freeGame.getUrl());
        assertEquals("Path of Exile", freeGame.getTitle());
        assertEquals("You are an Exile, struggling to survive on the dark continent of Wraeclast, as you fight to earn power that will allow you to exact your revenge against those who wronged you. Created by hardcore gamers, Path of Exile is an online Action RPG set in a dark fantasy world.", freeGame.getDescription());
        assertEquals("- 85% of the 1,414 user reviews in the last 30 days are positive. - 92% of the 61,008 user reviews for this game are positive.", freeGame.getRating());
        assertEquals("Free to Play", freeGame.getPrice());
        assertEquals("", freeGame.getDiscount());
        assertEquals("https://steamcdn-a.akamaihd.net/steam/apps/238960/header.jpg?t=1528342368", freeGame.getImageUrl());
    }

    @Test
    public void testPaidGame() {
        SteamGame paidGame = steamScraper.scrapeGame(paidGameDoc);
        assertNotNull(paidGame.getUrl());
        assertEquals("Tom Clancy's Rainbow Six® Siege", paidGame.getTitle());
        assertEquals("Tom Clancy's Rainbow Six Siege is the latest installment of the acclaimed first-person shooter franchise developed by the renowned Ubisoft Montreal studio.", paidGame.getDescription());
        assertEquals("- 76% of the 7,207 user reviews in the last 30 days are positive. - 80% of the 165,243 user reviews for this game are positive.", paidGame.getRating());
        assertEquals("CDN$ 19.49", paidGame.getPrice());
        assertEquals("", paidGame.getDiscount());
        assertEquals("https://steamcdn-a.akamaihd.net/steam/apps/359550/header_alt_assets_2.jpg?t=1529431540", paidGame.getImageUrl());
    }

    @Test
    public void testDiscountGame() {
        SteamGame discountGame = steamScraper.scrapeGame(discountGameDoc);
        assertNotNull(discountGame.getUrl());
        assertEquals("Borderlands 2", discountGame.getTitle());
        assertEquals("The Ultimate Vault Hunter’s Upgrade lets you get the most out of the Borderlands 2 experience.", discountGame.getDescription());
        assertEquals("- 92% of the 1,735 user reviews in the last 30 days are positive. - 96% of the 82,631 user reviews for this game are positive.", discountGame.getRating());
        assertEquals("CDN$ 5.49", discountGame.getPrice());
        assertEquals("-75%", discountGame.getDiscount());
        assertEquals("https://steamcdn-a.akamaihd.net/steam/apps/49520/header.jpg?t=1527189355", discountGame.getImageUrl());
    }

    @Test
    public void priceSortCheckerLowest() {
        Collections.shuffle(gameList);

        //Price Sorting for lowest price
        gameList.sort(new sortGame(false, false, false));

        assertEquals("CDN$ 0.99", gameList.get(0).getPrice());
        assertEquals("CDN$ 1.99", gameList.get(1).getPrice());
        assertEquals("CDN$ 100.99", gameList.get(2).getPrice());
    }

    @Test
    public void discountSortCheckerLowest() {
        Collections.shuffle(gameList);

        //Discount Sorting for lowest discount
        gameList.sort(new sortGame(false, true, false));

        assertEquals("-75%", gameList.get(0).getDiscount());
        assertEquals("-80%", gameList.get(1).getDiscount());
        assertEquals("-90%", gameList.get(2).getDiscount());
    }

    @Test
    public void priceCheckerHigher() {
        Collections.shuffle(gameList);

        //Price Sorting for highest price
        gameList.sort(new sortGame(true, false, false));

        assertEquals("CDN$ 100.99", gameList.get(0).getPrice());
        assertEquals("CDN$ 1.99", gameList.get(1).getPrice());
        assertEquals("CDN$ 0.99", gameList.get(2).getPrice());
    }

    @Test
    public void discountCheckerHigher() {
        Collections.shuffle(gameList);

        //Discount checking for highest discount
        gameList.sort(new sortGame(true, true, false));

        assertEquals("-90%", gameList.get(0).getDiscount());
        assertEquals("-80%", gameList.get(1).getDiscount());
        assertEquals("-75%", gameList.get(2).getDiscount());
    }

    @Test
    public void sortByAlphabeticalAZ() {
        Collections.shuffle(gameList);

        //Alphabetical A-Z sorting
        gameList.sort(new sortGame(true, false, true));

        assertEquals("Cheapest Game", gameList.get(0).getTitle());
        assertEquals("Expensive Game", gameList.get(1).getTitle());
        assertEquals("Middle Game",gameList.get(2).getTitle());
    }

    @Test
    public void sortByAlphabeticalZA() {
        Collections.shuffle(gameList);

        //Alphabetical Z-A sorting
        gameList.sort(new sortGame(false, false, true));

        assertEquals("Middle Game", gameList.get(0).getTitle());
        assertEquals("Expensive Game", gameList.get(1).getTitle());
        assertEquals("Cheapest Game", gameList.get(2).getTitle());
    }

}