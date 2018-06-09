import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class main {

    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect("https://www.reddit.com/r/steamdeals/").get();
        Elements steamUrls = doc.select("a[href*=store.steampowered.com/app][class*='s1yomx28-0']");

        Map<String, String> steamCookies = new HashMap<String, String>();
        steamCookies.put("birthtime", "915177601");
        steamCookies.put("mature_content", "1");

        for (Element url : steamUrls) {
            String gameUrl = url.attr("href");
            if(isGameFree(gameUrl, steamCookies)) {
                printDescription(gameUrl, steamCookies);
                System.out.println("Link: " + gameUrl + "\n");
            }
        }
    }

    private static boolean isGameFree(String gameUrl, Map<String, String> cookies) throws IOException {
        String freeGameText = "Install Game";
        String freeToPlay = "Play Game";
        Document doc = Jsoup.connect(gameUrl).cookies(cookies).get();
        Elements priceBox = doc.select("[class~=btnv6_green_white_innerfade]");
        return (priceBox.text().equals(freeGameText) || priceBox.text().equals(freeToPlay));
    }

    private static void printDescription(String gameUrl, Map<String, String> cookies) throws IOException{
        Document doc = Jsoup.connect(gameUrl).cookies(cookies).get();
        Elements steamUrls = doc.select(".game_description_snippet");
        System.out.println(steamUrls.text());


    }

}

