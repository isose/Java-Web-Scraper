package Comparators;

import Models.SteamGame;

import java.util.Comparator;

public class sortGame implements Comparator<SteamGame> {

    public sortGame(boolean isDescending, boolean discountSort, boolean alphabeticalSort) {
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

        if(gameOneDisc > gameTwoDisc && isDescending) {
            return -1;
        }
        else if(gameOneDisc < gameTwoDisc && isDescending) {
            return 1;
        }
        else return Float.compare(gameOneDisc, gameTwoDisc);
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

        float priceOne = 0;
        float priceTwo = 0;

        for(int i = 0; i < gameOne.length(); i++) {
            if(gameOne.equals("Free To Play")) {
                break;
            }

            if(gameOne.charAt(i) == ' ') {
                gameOne = gameOne.substring(i, gameOne.length());
                priceOne = Float.parseFloat(gameOne);
                break;
            }
        }

        for(int i = 0; i < gameTwo.length(); i++) {
            if(gameTwo.equals("Free To Play")) {
                break;
            }

            if(gameTwo.charAt(i) == ' ') {
                gameTwo = gameTwo.substring(i, gameTwo.length());
                priceTwo = Float.parseFloat(gameTwo);
                break;
            }
        }

        if(priceOne > priceTwo && isDescending) {
            return -1;
        }
        else if(priceOne < priceTwo && isDescending) {
            return 1;
        }
        else return Float.compare(priceOne, priceTwo);
    }



}
