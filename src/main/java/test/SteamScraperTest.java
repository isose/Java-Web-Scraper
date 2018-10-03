package test;

import Models.SteamGame;
import Scrapers.SteamScraper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SteamScraperTest {
    private SteamScraper steamScraper;
    private Document freeGameDoc, paidGameDoc, discountGameDoc;


    @BeforeEach
    public void setUp() {
        steamScraper = new SteamScraper(new HashSet<>());
        try {
            File input = new File("./src/main/java/test/testResources/Path of Exile on Steam.html");
            freeGameDoc = Jsoup.parse(input, null);
            input = new File("./src/main/java/test/testResources/Tom Clancy's Rainbow Six® Siege on Steam.html");
            paidGameDoc = Jsoup.parse(input, null);
            input = new File("./src/main/java/test/testResources/Save 75% on Borderlands 2 on Steam.html");
            discountGameDoc = Jsoup.parse(input, null);
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
        assertEquals("Free to Play", freeGame.getPriceString());
        assertEquals("", freeGame.getDiscountString());
        assertEquals("https://steamcdn-a.akamaihd.net/steam/apps/238960/header.jpg?t=1528342368", freeGame.getImageUrl());
    }

    @Test
    public void testPaidGame() {
        SteamGame paidGame = steamScraper.scrapeGame(paidGameDoc);
        assertNotNull(paidGame.getUrl());
        assertEquals("Tom Clancy's Rainbow Six® Siege", paidGame.getTitle());
        assertEquals("Tom Clancy's Rainbow Six Siege is the latest installment of the acclaimed first-person shooter franchise developed by the renowned Ubisoft Montreal studio.", paidGame.getDescription());
        assertEquals("- 76% of the 7,207 user reviews in the last 30 days are positive. - 80% of the 165,243 user reviews for this game are positive.", paidGame.getRating());
        assertEquals("CDN$ 19.49", paidGame.getPriceString());
        assertEquals("", paidGame.getDiscountString());
        assertEquals("https://steamcdn-a.akamaihd.net/steam/apps/359550/header_alt_assets_2.jpg?t=1529431540", paidGame.getImageUrl());
    }

    @Test
    public void testDiscountGame() {
        SteamGame discountGame = steamScraper.scrapeGame(discountGameDoc);
        assertNotNull(discountGame.getUrl());
        assertEquals("Borderlands 2", discountGame.getTitle());
        assertEquals("The Ultimate Vault Hunter’s Upgrade lets you get the most out of the Borderlands 2 experience.", discountGame.getDescription());
        assertEquals("- 92% of the 1,735 user reviews in the last 30 days are positive. - 96% of the 82,631 user reviews for this game are positive.", discountGame.getRating());
        assertEquals("CDN$ 5.49", discountGame.getPriceString());
        assertEquals("-75%", discountGame.getDiscountString());
        assertEquals("https://steamcdn-a.akamaihd.net/steam/apps/49520/header.jpg?t=1527189355", discountGame.getImageUrl());
    }
}