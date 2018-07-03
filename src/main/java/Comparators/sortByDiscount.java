package Comparators;

import Models.SteamGame;
import java.util.Comparator;

public class sortByDiscount implements Comparator<SteamGame> {

    public  sortByDiscount(boolean isHighest) {
        this.isHighest = isHighest;
    }

    private boolean isHighest;

    @Override
    public int compare(SteamGame o1, SteamGame o2) {
        if(o1.getDiscount().equals("") && isHighest) {
            return 1;
        }
        else if(o1.getDiscount().equals("")) {
            return -1;
        }
        else if(o2.getDiscount().equals("") && isHighest) {
            return 1;
        }
        else if(o2.getDiscount().equals("")) {
            return -1;
        }

        String gameOne = o1.getDiscount().substring(1,2);
        String gameTwo = o2.getDiscount().substring(1,2);
        int gameOneDisc = Integer.parseInt(gameOne);
        int gameTwoDisc = Integer.parseInt(gameTwo);

        if(gameOneDisc > gameTwoDisc && isHighest) {
            return -1;
        }
        else if(gameOneDisc < gameTwoDisc && isHighest) {
            return 1;
        }
        else if(gameOneDisc < gameTwoDisc) {
            return -1;
        }
        else if(gameOneDisc > gameTwoDisc) {
            return 1;
        }
        else {
            return 0;
        }
    }

}
