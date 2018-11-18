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
        steamGames.sort(new TitleComparator());
    }

    public void sortTitleDescending() {
        steamGames.sort(Collections.reverseOrder(new TitleComparator()));
    }

    public void sortPriceAscending() {
        steamGames.sort(new PriceComparator());
    }

    public void sortPriceDescending() {
        steamGames.sort(Collections.reverseOrder(new PriceComparator()));
    }

    public void sortDiscountAscending() {
        steamGames.sort(new DiscountComparator());
    }

    public void sortDiscountDescending() {
        steamGames.sort(Collections.reverseOrder(new DiscountComparator()));
    }
}
