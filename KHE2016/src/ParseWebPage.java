
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Josh Reichman, Emilio Lopez
 */

/**
 * interface for parsing a web page's data
 */


public interface ParseWebPage {
    public String websiteTitle()throws IOException;
    public String findArticleBody()throws IOException;
    public String findPros()throws IOException;
    public String findCons()throws IOException;
}
