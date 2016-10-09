
import java.io.IOException;
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
 * @author joshreichman, Emilio Lopez
 */
public class ParseCnet implements ParseWebPage {

    protected Document d;
    protected String publisher = "CNet";

    public ParseCnet() {
        
        System.out.print("Input Product (Brand and Phone): ");
        Scanner input = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        String product = input.nextLine();
        for(int i= 0; i < product.length();i++){
            if(product.charAt(i) != ' '){
            sb.append(product.charAt(i));
            }
            else{
                sb.append('-');
            }
        }
        input.close();
        product = ("https://www.cnet.com/products/" + sb.toString());

        try {
            d = Jsoup.connect(product).timeout(6000).get();
            System.out.println("Connected! \n");
        } catch (Exception e) {
            System.out.println("Failed to connect!");
            System.exit(0);
        }

    }

    @Override
    public String websiteTitle() throws IOException {
        // TODO Auto-generated method stub
        return ("Title: " + d.title());
    }

    @Override
    public String findArticleBody() throws IOException {
        Elements body = d.getElementsByTag("article");
        return(body.text());
    }

    @Override
    public String findPros() throws IOException {
        // TODO Auto-generated method stub
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

    @Override
    public String findCons() throws IOException {
        // TODO Auto-generated method stub
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

    public static void main(String[] args) throws IOException, IllegalArgumentException {
        // TODO Auto-generated method stub

        ParseCnet parseCnet;
        
        try {
            parseCnet =  new ParseCnet();
            System.out.println("Success! Valid URL!");
            System.out.println(parseCnet.websiteTitle() + "\n");
            System.out.println(parseCnet.findPros() + "\n");
            System.out.println(parseCnet.findCons() + "\n");
            System.out.println(parseCnet.findArticleBody() + "\n");
        } catch (Exception e) {
            System.out.println("ERROR! Invalid URL!");
            System.exit(0);
        }

    }
}
