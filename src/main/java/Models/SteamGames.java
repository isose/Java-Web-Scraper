package Models;

import java.util.ArrayList;

public class SteamGames {
    private ArrayList<SteamGame> steamGames;

    public SteamGames() {
        steamGames = new ArrayList<>();
    }

    public void addGame(SteamGame steamGame) {
        steamGames.add(steamGame);
    }

    //Precondition - Game must be on the gamelist
    public SteamGame getGame(String gameName) {
        SteamGame chosenGame = new SteamGame();
        for(SteamGame game : steamGames) {
            if(game.getName().equals(gameName)) {
                chosenGame = game;
            }
        }
        return chosenGame;
    }

    public boolean contains(String name) {
        return steamGames.contains(name);
    }

    public ArrayList<SteamGame> getSteamGames() {
        return steamGames;
    }

    //TODO
}
