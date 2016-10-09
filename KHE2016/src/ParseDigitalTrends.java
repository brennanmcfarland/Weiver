
import java.io.IOException;

import org.jsoup.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author brennanmcfarland
 */
public class ParseDigitalTrends {

    Document d; //jsoup document for parsing

    public ParseDigitalTrends(String prdct) throws NullQueryException{
        StringBuilder sb = new StringBuilder();
        String product = prdct;
        for (int i = 0; i < product.length(); i++) {
            if (product.charAt(i) != ' ') {
                sb.append(product.charAt(i));
            } else {
                sb.append('-');
            }
        }
        product = ("http://www.digitaltrends.com/cell-phone-reviews/" + sb.toString() + "-review/");
        try {
            d = Jsoup.connect(product).timeout(6000).get();
        } catch (Exception e) {
            throw new NullQueryException("product not found on DigitalTrends!");
        }
    }

    /*
    //connects to the page and initializes documet for parsing
    public ParseDigitalTrends(String url) {
        try{
            d=Jsoup.connect(url).timeout(6000).get();
            System.out.println("Connected! \n");
        }
        catch(Exception IOException) {
            System.out.println("Failed to connect!");
            System.exit(0);
        }
    }
     */
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
        Elements pros = d.select("div[class='m-good-bad good']");
        return (pros.text().substring(6));
    }

    //returns the cons on the webpage parsed as a string
    public String findCons() throws IOException {
        Elements cons = d.select("div[class='m-good-bad bad']");
        return (cons.text().substring(5));
    }
    /*
    //for testing input only, not to be used in final version
    public static void main(String[] args) throws IOException, IllegalArgumentException {
        ParseDigitalTrends parseDigitalTrends;
 
        try {
            parseDigitalTrends = new ParseDigitalTrends();
            System.out.println("Success! Valid Product! \n\n");
            System.out.println(parseDigitalTrends.websiteTitle() + "\n");
            System.out.println(parseDigitalTrends.findPros() + "\n");
            System.out.println(parseDigitalTrends.findCons() + "\n");
            System.out.println(parseDigitalTrends.findArticleBody() + "\n");

        } catch (Exception e) {
            System.out.println("ERROR! Invalid Product!");
            System.exit(0);
        }

    }*/
}
