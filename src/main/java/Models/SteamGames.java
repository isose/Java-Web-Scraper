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

    public ArrayList<SteamGame> getSteamGames() {
        return steamGames;
    }

    //TODO
}