package Comparators;

import Models.SteamGame;

import java.util.Comparator;

public class sortByPrice implements Comparator<SteamGame> {

    public sortByPrice(boolean isHighest) {
        this.isHighest = isHighest;
    }

    boolean isHighest;


    @Override
    public int compare(SteamGame o1, SteamGame o2) {
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

        if(priceOne > priceTwo && isHighest) {
            return -1;
        }
        else if(priceOne < priceTwo && isHighest) {
            return 1;
        }
        else if(priceOne > priceTwo) {
            return 1;
        }
        else if(priceOne < priceTwo) {
            return -1;
        }
        else {
            return 0;
        }
    }
}
