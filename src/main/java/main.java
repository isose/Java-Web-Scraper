import org.jsoup.Jsoup;
        import org.jsoup.nodes.Document;
        import org.jsoup.nodes.Element;
        import org.jsoup.select.Elements;

        import java.io.IOException;

public class main {
    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect("https://www.reddit.com/r/FreeGameFindings/").get();
        Elements steamUrls = doc.select("a[href*=store.steampowered][class*='s1yomx28-0']");
        for (Element url : steamUrls) {
            System.out.println("\nLink: " + url.attr("href"));
        }
    }
}

