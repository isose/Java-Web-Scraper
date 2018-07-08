package Comparators;

import Models.SteamGame;

import java.util.Comparator;

public class SortGame implements Comparator<SteamGame> {

    public SortGame(boolean isDescending, boolean discountSort, boolean alphabeticalSort) {
        this.isDescending = isDescending;
        this.discountSort = discountSort;
        this.alphabeticalSort = alphabeticalSort;
    }

    private boolean isDescending;
    private boolean discountSort;
    private boolean alphabeticalSort;

    @Override
    public int compare(SteamGame o1, SteamGame o2) {
        if(alphabeticalSort) {
            return alphabeticalCompare(o1, o2);
        }
        else if(discountSort) {
            return discountCompare(o1, o2);
        }
        else {
            return priceCompare(o1, o2);
        }

    }

    private int alphabeticalCompare(SteamGame o1, SteamGame o2) {
        if (isDescending) {
            return o1.getTitle().compareTo(o2.getTitle());
        }
        else {
            return o2.getTitle().compareTo(o1.getTitle());
        }
    }

    private int discountCompare(SteamGame o1, SteamGame o2) {
        if(o1.getDiscount().equals("") && isDescending) {
            return 1;
        }
        else if(o1.getDiscount().equals("")) {
            return -1;
        }
        else if(o2.getDiscount().equals("") && isDescending) {
            return 1;
        }
        else if(o2.getDiscount().equals("")) {
            return -1;
        }

        String gameOne = o1.getDiscount().substring(1,2);
        String gameTwo = o2.getDiscount().substring(1,2);
        float gameOneDisc = Integer.parseInt(gameOne);
        float gameTwoDisc = Integer.parseInt(gameTwo);

        return getCompareFloat(gameOneDisc, gameTwoDisc);
    }

    private int priceCompare(SteamGame o1, SteamGame o2) {
        String gameOne = o1.getPrice();
        String gameTwo = o2.getPrice();

        if(gameOne.equals("")) {
            return 1;
        }
        else if(gameTwo.equals("")) {
            return -1;
        }

        float priceOne;
        float priceTwo;

        priceOne = breakFloat(gameOne);
        priceTwo = breakFloat(gameTwo);

        return getCompareFloat(priceOne, priceTwo);
    }

    private float breakFloat(String o1) {
        float gameFloat = 0;
        for(int i = 0; i < o1.length(); i++) {
            if(o1.equals("Free To Play")) {
                break;
            }

            if(o1.charAt(i) == ' ') {
                o1 = o1.substring(i, o1.length());
                gameFloat = Float.parseFloat(o1);
                return gameFloat;
            }
        }
        return gameFloat;
    }

    private int getCompareFloat(float gameOneDisc, float gameTwoDisc) {
        if(gameOneDisc > gameTwoDisc && isDescending) {
            return -1;
        }
        else if(gameOneDisc < gameTwoDisc && isDescending) {
            return 1;
        }
        else return Float.compare(gameOneDisc, gameTwoDisc);
    }
}
