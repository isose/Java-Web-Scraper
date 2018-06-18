import java.util.HashMap;
import java.util.HashSet;

public class SteamScraper {
    private HashMap<String, String> steamCookies;
    private HashSet<String> listOfSteamGameUrls;
    private SteamGames steamGames;

    public SteamScraper(HashSet<String> listOfSteamGameUrls) {
        steamCookies = new HashMap<String, String>();
        steamCookies.put("birthtime", "915177601");
        steamCookies.put("mature_content", "1");
        this.listOfSteamGameUrls = listOfSteamGameUrls;

    }

    private void validateFreeGame() {
        //TODO
    }

    public SteamGames getSteamGames() {
        return steamGames;
    }

}
