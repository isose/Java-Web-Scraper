package Scrapers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RedditScraper {
    private static final String STEAM_URL_PATTERN = "https://store.steampowered.com/app/\\d+";

    private Set<String> subRedditUrls;

    public RedditScraper(HashSet<String> subredditUrls) {
        this.subRedditUrls = subredditUrls;
    }

    public HashSet<String> getSteamUrls() throws IOException {
        HashSet<String> listOfSteamUrls = new HashSet<>();
        for(String subReddit : subRedditUrls) {
            //Go to the subreddit page and select all steam pages on that subreddit
            Document doc = Jsoup.connect(subReddit).get();
            Elements redditElements = doc.select("a[href*=store.steampowered.com/app][class*=' ']");
            listOfSteamUrls.addAll(getUrlSetFromElements(redditElements));
        }
        return listOfSteamUrls;
    }

    private Set<String> getUrlSetFromElements(Elements redditElements) {
        Set<String> urlSet = new HashSet<>();
        Pattern pattern = Pattern.compile(STEAM_URL_PATTERN);
        //For each element selected add it's url to the list of urls
        for(Element element : redditElements) {
            //Extracts substring of the url containing up to the game id to avoid duplicates of the same url with different ending
            Matcher matcher = pattern.matcher(element.attr("href"));
            if (matcher.find()) {
                urlSet.add(matcher.group());
            }
        }
        return urlSet;
    }

    /**
     * scrapes urls from subReddits containing a table
     * @param doc
     * @deprecated used only during Steam summer sale
     */
    @Deprecated
    private void obtainUrlsFromTable(Document doc) {
        Elements redditUrls = doc.select("td a");
        for(Element url : redditUrls) {
            String gameUrl = url.attr("href");
            if(gameUrl.contains("https://store.steampowered.com/app/")) {
//                listOfSteamUrls.add(gameUrl);
            }
        }
    }
}
