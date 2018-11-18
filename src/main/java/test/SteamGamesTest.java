package test;

import Models.SteamGame;
import Models.SteamGames;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SteamGamesTest {
    private SteamGames steamGames;
    private SteamGame cheapGame;
    private SteamGame middleGame;
    private SteamGame expensiveGame;

    @BeforeEach
    public void setUp() {
        steamGames = new SteamGames();
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
        steamGames.addGame(cheapGame);
        steamGames.addGame(middleGame);
        steamGames.addGame(expensiveGame);
    }

    @Test
    public void beforeSortingTest() {
        ArrayList<SteamGame> gameList = steamGames.getSteamGames();
        assertEquals(cheapGame, gameList.get(0));
        assertEquals(middleGame, gameList.get(1));
        assertEquals(expensiveGame, gameList.get(2));
    }

    @Test
    public void sortTitleAscendingTest() {
        //Alphabetical A-Z sorting
        steamGames.sortTitleAscending();
        ArrayList<SteamGame> gameList = steamGames.getSteamGames();
        assertEquals("Cheapest Game", gameList.get(0).getTitle());
        assertEquals("Expensive Game", gameList.get(1).getTitle());
        assertEquals("Middle Game",gameList.get(2).getTitle());
    }

    @Test
    public void sortTitleDescendingTest() {
        //Alphabetical Z-A sorting
        steamGames.sortTitleDescending();
        ArrayList<SteamGame> gameList = steamGames.getSteamGames();
        assertEquals("Middle Game", gameList.get(0).getTitle());
        assertEquals("Expensive Game", gameList.get(1).getTitle());
        assertEquals("Cheapest Game", gameList.get(2).getTitle());
    }

    @Test
    public void sortPriceAscendingTest() {
        steamGames.sortPriceAscending();
        ArrayList<SteamGame> gameList = steamGames.getSteamGames();
        assertEquals("CDN$ 0.99", gameList.get(0).getPriceString());
        assertEquals("CDN$ 1.99", gameList.get(1).getPriceString());
        assertEquals("CDN$ 100.99", gameList.get(2).getPriceString());
    }

    @Test
    public void sortPriceDescendingTest() {
        steamGames.sortPriceDescending();
        ArrayList<SteamGame> gameList = steamGames.getSteamGames();
        assertEquals("CDN$ 100.99", gameList.get(0).getPriceString());
        assertEquals("CDN$ 1.99", gameList.get(1).getPriceString());
        assertEquals("CDN$ 0.99", gameList.get(2).getPriceString());
    }

    @Test
    public void sortDiscountAscendingTest() {
        steamGames.sortDiscountAscending();
        ArrayList<SteamGame> gameList = steamGames.getSteamGames();
        assertEquals("-75%", gameList.get(0).getDiscountString());
        assertEquals("-80%", gameList.get(1).getDiscountString());
        assertEquals("-90%", gameList.get(2).getDiscountString());
    }

    @Test
    public void sortDiscountDescendingTest() {
        steamGames.sortDiscountDescending();
        ArrayList<SteamGame> gameList = steamGames.getSteamGames();
        assertEquals("-90%", gameList.get(0).getDiscountString());
        assertEquals("-80%", gameList.get(1).getDiscountString());
        assertEquals("-75%", gameList.get(2).getDiscountString());
    }
}
