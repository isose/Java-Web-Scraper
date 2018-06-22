package Models;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;

public class RedditScraper {
    private HashSet<String> subRedditUrls;

    public RedditScraper(HashSet<String> subredditUrls) {
        this.subRedditUrls = subredditUrls;
    }

    public HashSet<String> getSteamUrls() throws IOException {
        HashSet<String> listOfSteamUrls = new HashSet<>();
        for(String subReddit : subRedditUrls) {
            
            //Go to the subreddit page and select all steam pages on that subreddit
            Document doc = Jsoup.connect(subReddit).get();
            Elements redditUrls = doc.select("a[href*=store.steampowered.com/app][class*=' ']");

            //For each element selected add it's url to the list of urls
            for(Element url : redditUrls) {
                String gameUrl = url.attr("href");
                listOfSteamUrls.add(gameUrl);
            }
        }
        return listOfSteamUrls;
    }
}
