package test;

import Comparators.SortGame;
import Models.SteamGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SortGameTest {
    private ArrayList<SteamGame> gameList = new ArrayList<>();
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
    }

    @Test
    public void testBeforeSorting() {
        assertEquals(cheapGame, gameList.get(0));
        assertEquals(middleGame, gameList.get(1));
        assertEquals(expensiveGame, gameList.get(2));
    }

    @Test
    public void testSortLowestPrice() {
        gameList.sort(new SortGame(false, false, false));
        assertEquals("CDN$ 0.99", gameList.get(0).getPrice());
        assertEquals("CDN$ 1.99", gameList.get(1).getPrice());
        assertEquals("CDN$ 100.99", gameList.get(2).getPrice());
    }

    @Test
    public void testSortHighestPrice() {
        gameList.sort(new SortGame(true, false, false));
        assertEquals("CDN$ 100.99", gameList.get(0).getPrice());
        assertEquals("CDN$ 1.99", gameList.get(1).getPrice());
        assertEquals("CDN$ 0.99", gameList.get(2).getPrice());
    }

    @Test
    public void testSortLowestDiscount() {
        gameList.sort(new SortGame(false, true, false));
        assertEquals("-75%", gameList.get(0).getDiscount());
        assertEquals("-80%", gameList.get(1).getDiscount());
        assertEquals("-90%", gameList.get(2).getDiscount());
    }

    @Test
    public void testSortHighestDiscount() {
        gameList.sort(new SortGame(true, true, false));
        assertEquals("-90%", gameList.get(0).getDiscount());
        assertEquals("-80%", gameList.get(1).getDiscount());
        assertEquals("-75%", gameList.get(2).getDiscount());
    }

    @Test
    public void testSortAlphabeticalAZ() {
        //Alphabetical A-Z sorting
        gameList.sort(new SortGame(true, false, true));
        assertEquals("Cheapest Game", gameList.get(0).getTitle());
        assertEquals("Expensive Game", gameList.get(1).getTitle());
        assertEquals("Middle Game",gameList.get(2).getTitle());
    }

    @Test
    public void testSortByAlphabeticalZA() {
        //Alphabetical Z-A sorting
        gameList.sort(new SortGame(false, false, true));
        assertEquals("Middle Game", gameList.get(0).getTitle());
        assertEquals("Expensive Game", gameList.get(1).getTitle());
        assertEquals("Cheapest Game", gameList.get(2).getTitle());
    }
}
