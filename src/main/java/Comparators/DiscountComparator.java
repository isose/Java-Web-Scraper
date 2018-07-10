package Comparators;

import Models.SteamGame;

import java.util.Comparator;

public class DiscountComparator implements Comparator<SteamGame> {

    @Override
    public int compare(SteamGame o1, SteamGame o2) {
        return o1.getDiscountInt() - o2.getDiscountInt();
    }
}
