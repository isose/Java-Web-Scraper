package Comparators;

import Models.SteamGame;

import java.util.Comparator;

public class sortByAlphabeticalOrderAtoZ implements Comparator<SteamGame> {
    @Override
    public int compare(SteamGame o1, SteamGame o2) {
        return o1.getTitle().compareTo(o2.getTitle());
    }
}
