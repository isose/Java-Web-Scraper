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
    private HashSet<String> listOfSteamUrls = new HashSet<>();

    public HashSet<String> getSteamUrls() throws IOException {

        for(String subReddit : subRedditUrls) {
            //Go to the subreddit page and select all steam pages on that subreddit
            Document doc = Jsoup.connect(subReddit).get();
            Elements redditUrls = doc.select("a[href*=store.steampowered.com/app][class*=' ']");
            urlScrape(redditUrls);
            obtainUrlsFromTable(doc);
        }
        return listOfSteamUrls;
    }

    private void urlScrape(Elements redditUrls) {
        //For each element selected add it's url to the list of urls
        for(Element url : redditUrls) {
            String gameUrl = url.attr("href");
            listOfSteamUrls.add(gameUrl);
        }
    }

    private void obtainUrlsFromTable(Document doc) {
        Elements redditUrls = doc.select("td a");
        for(Element url : redditUrls) {
            String gameUrl = url.attr("href");
            if(gameUrl.contains("https://store.steampowered.com/app/")) {
                listOfSteamUrls.add(gameUrl);
            }
        }


    }
}
