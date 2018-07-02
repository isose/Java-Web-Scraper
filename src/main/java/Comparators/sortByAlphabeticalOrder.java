package Comparators;

import Models.SteamGame;

import java.util.Comparator;

public class sortByAlphabeticalOrder implements Comparator<SteamGame> {

    public sortByAlphabeticalOrder(boolean isDescending) {
        this.isDescending = isDescending;
    }

    private boolean isDescending;

    @Override
    public int compare(SteamGame o1, SteamGame o2) {
        if (isDescending) {
            return o1.getTitle().compareTo(o2.getTitle());
        }
        else {
            return o2.getTitle().compareTo(o1.getTitle());
        }
    }
}
