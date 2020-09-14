package Comparators;

import Models.SteamGame;

import java.util.Comparator;

public class PriceComparator implements Comparator<SteamGame> {

    @Override
    public int compare(SteamGame o1, SteamGame o2) {
        float price1 = o1.getPriceFloat();
        float price2 = o2.getPriceFloat();
        if (price1 == price2) {
            return 0;
        }
        return Float.compare(price1, price2);
    }
}
