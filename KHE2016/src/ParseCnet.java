
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.Scanner;

/**
 *
 * @author Josh Eeichman, Emilio Lopez
 */

/**
 * given the product name, searches for the corresponding webpage on CNet
 * and parses relevant data on that page as described in ParseWebPage
 */

public class ParseCnet implements ParseWebPage {

    protected Document d; //jsoup document for parsing
    protected String publisher = "CNet";

    public ParseCnet(String prdct) throws NullQueryException {
        StringBuilder sb = new StringBuilder();
        String product = prdct;
        for (int i = 0; i < product.length(); i++) {
            if (product.charAt(i) != ' ') {
                sb.append(product.charAt(i));
            } else {
                sb.append('-');
            }
        }
        product = ("https://www.cnet.com/products/" + sb.toString());

        try {
            d = Jsoup.connect(product).timeout(6000).get();
        } catch (Exception e) {
            throw new NullQueryException("product not found on Cnet!");
        }

    }

    //returns the article title parsed on the webpage as a string
    public String websiteTitle() throws IOException {
        return ("Title: " + d.title());
    }

    //returns the article body on the webpage parsed as a string
    public String findArticleBody() throws IOException {
        Elements body = d.getElementsByTag("article");
        return (body.text());
    }

    //returns the pros on the webpage parsed as a string
    public String findPros() throws IOException {
        StringBuilder sb = new StringBuilder();
        String title = d.select("div.quickinfo > *").toString();
        title = title.replaceAll("\\<.*?>", "");
        title = title.substring(8);

        // The Good section of a review is separated by a new line 
        for (int i = 0; i < title.length(); i++) {
            if (title.charAt(i) != '\n') {
                sb.append(title.charAt(i));
            } else {
                break;
            }
        }

        return ("Pros: " + sb.toString());
    }

    //returns the cons on the webpage parsed as a string
    public String findCons() throws IOException {
        int newLineCounter = 0;

        StringBuilder sb = new StringBuilder();
        String title = d.select("div.quickinfo > *").toString();
        title = title.replaceAll("\\<.*?>", "");

        /* Filters the Bad part of the summary from the Good and Bottomline*/
        for (int i = 0; i < title.length(); i++) {

            if (newLineCounter == 1) {
                sb.append(title.charAt(i));
            }

            if (title.charAt(i) == '\n') {
                newLineCounter++;
            }

            if (newLineCounter > 1) {
                break;
            }
        }
        String returnString = sb.toString().substring(7);
        return ("Cons: " + returnString);
    }
    /*
    public static void main(String[] args) throws IOException, IllegalArgumentException {
        // TODO Auto-generated method stub

        ParseCnet parseCnet;

        try {
            parseCnet = new ParseCnet();
            System.out.println("Success! Valid Product!\n\n");
            System.out.println(parseCnet.websiteTitle() + "\n");
            System.out.println(parseCnet.findPros() + "\n");
            System.out.println(parseCnet.findCons() + "\n");
            System.out.println(parseCnet.findArticleBody() + "\n");
        } catch (Exception e) {
            System.out.println("ERROR! Invalid Product!");
            System.exit(0);
        }

    }*/
}
