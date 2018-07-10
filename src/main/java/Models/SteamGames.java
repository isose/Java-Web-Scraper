package Models;

import Comparators.DiscountComparator;
import Comparators.PriceComparator;
import Comparators.TitleComparator;

import java.util.ArrayList;
import java.util.Collections;

public class SteamGames {
    private ArrayList<SteamGame> steamGames;

    public SteamGames() {
        steamGames = new ArrayList<>();
    }

    public void addGame(SteamGame steamGame) {
        steamGames.add(steamGame);
    }

    public ArrayList<SteamGame> getSteamGames() {
        return steamGames;
    }

    public void sortTitleAscending() {
        Collections.sort(steamGames, new TitleComparator());
    }

    public void sortTitleDescending() {
        Collections.sort(steamGames, Collections.reverseOrder(new TitleComparator()));
    }

    public void sortPriceAscending() {
        Collections.sort(steamGames, new PriceComparator());
    }

    public void sortPriceDescending() {
        Collections.sort(steamGames, Collections.reverseOrder(new PriceComparator()));
    }

    public void sortDiscountAscending() {
        Collections.sort(steamGames, new DiscountComparator());
    }

    public void sortDiscountDescending() {
        Collections.sort(steamGames, Collections.reverseOrder(new DiscountComparator()));
    }
}
