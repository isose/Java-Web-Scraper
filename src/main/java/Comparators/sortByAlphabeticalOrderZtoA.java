package Comparators;

import Models.SteamGame;

import java.util.Comparator;

public class sortByAlphabeticalOrderZtoA implements Comparator<SteamGame> {
    @Override
    public int compare(SteamGame o1, SteamGame o2) {
        return o2.getTitle().compareTo(o1.getTitle());
    }
}
