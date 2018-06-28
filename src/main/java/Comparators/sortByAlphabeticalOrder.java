package Comparators;

import Models.SteamGame;

import java.util.Comparator;

public class sortByAlphabeticalOrder implements Comparator<SteamGame> {
    @Override
    public int compare(SteamGame o1, SteamGame o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
