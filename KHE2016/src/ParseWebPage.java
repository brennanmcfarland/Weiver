
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author joshreichman, Emilio Lopez
 */
public interface ParseWebPage {
    public String websiteTitle()throws IOException;
    public String findArticleBody()throws IOException;
    public String findPros()throws IOException;
    public String findCons()throws IOException;
}
