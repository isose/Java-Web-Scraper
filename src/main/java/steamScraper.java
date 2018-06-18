import java.util.HashSet;

public class steamScraper {
    private HashSet<String> listOfFreeSteamGames;
    private HashSet<Game> listOfGames;


    public steamScraper(HashSet<String> listOfFreeSteamGames) {
        this.listOfFreeSteamGames = listOfFreeSteamGames;
    }

    void validateFreeGame() {

    }

    HashSet<Game> getAllGames() {
        return listOfGames;
    }

}
