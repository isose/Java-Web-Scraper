package Scrapers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RedditScraper {
    private static final String STEAM_URL_PATTERN = "https://store.steampowered.com/app/\\d+";
    private static final String[] DEFAULT_SUBREDDIT_URLS = new String[] { "https://www.reddit.com/r/gamedeals/", "https://www.reddit.com/r/steamdeals/" };

    private static RedditScraper redditScraper = new RedditScraper();
    private Set<String> subRedditUrls = new HashSet<>(Arrays.asList(DEFAULT_SUBREDDIT_URLS));

    private RedditScraper() {}

    public static RedditScraper getRedditScraper() {
        return redditScraper;
    }

    public HashSet<String> scrapeSteamUrls() {
        HashSet<String> listOfSteamUrls = new HashSet<>();
        subRedditUrls.parallelStream().forEach((subRedditUrl) -> {
            try {
                //Go to the subReddit page and get all the steam urls on that subReddit
                Document doc = Jsoup.connect(subRedditUrl).get();
                Elements redditElements = doc.select("a[href*=store.steampowered.com/app][class*=' ']");
                listOfSteamUrls.addAll(getSteamUrlSetFromElements(redditElements));
            } catch (IOException e) {
                System.out.print("Jsoup could not connect to subRedditUrl");
            }
        });
        return listOfSteamUrls;
    }

    /**
     * gets set of steam urls from given elements
     * @param elements elements to get steam url from
     * @return set of urls
     */
    private Set<String> getSteamUrlSetFromElements(Elements elements) {
        Set<String> steamUrlSet = new HashSet<>();
        Pattern pattern = Pattern.compile(STEAM_URL_PATTERN);
        //For each element add it's url to the list of urls
        for(Element element : elements) {
            //Extracts substring of the url containing up to the game id to avoid duplicates of the same url with different ending
            Matcher matcher = pattern.matcher(element.attr("href"));
            if (matcher.find()) {
                steamUrlSet.add(matcher.group());
            }
        }
        return steamUrlSet;
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
            if(gameUrl.contains(STEAM_URL_PATTERN)) {
                // listOfSteamUrls.add(gameUrl);
            }
        }
    }
}
