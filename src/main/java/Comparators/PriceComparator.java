package Comparators;

import Models.SteamGame;

import java.util.Comparator;

public class PriceComparator implements Comparator<SteamGame> {

    @Override
    public int compare(SteamGame o1, SteamGame o2) {
        float p1 = o1.getPriceFloat();
        float p2 = o2.getPriceFloat();
        return (p1 < p2) ? -1 : 1;
    }
}
