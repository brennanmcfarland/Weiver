
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
 * @author joshreichman
 */
public class ParseCnet implements ParseWebPage {


    public ParseCnet() {
        // TODO Auto-generated constructor stub
    }

    public String websiteTitle(Document d) throws IOException {
        // TODO Auto-generated method stub
        return ("Title: " + d.title());
    }

    
    public void findArticleBody() throws IOException {
        // TODO Auto-generated method stub
        // Not Complete!
    }


    public String findPros(Document d) throws IOException {
        // TODO Auto-generated method stub
        String title = d.select("div.quickinfo > *").toString();
        title = title.replaceAll("\\<.*?>", "");
        return("Pros: " + title); 
    }

    public String findCons(Document d) throws IOException {
        // TODO Auto-generated method stub
        String title = d.select("div.quickinfo > *").toString();
        title = title.replaceAll("\\<.*?>", "");
        return("Cons: " + title); 
    }
    
    public static void main(String[] args) throws IOException, IllegalArgumentException {
        // TODO Auto-generated method stub
        ParseCnet parseCnet = new ParseCnet();
        Scanner input = new Scanner(System.in);
        Document d = null;
        System.out.print("input url: ");
        String url = input.next();
        input.close();
        try{
            d = Jsoup.connect(url).timeout(6000).get();
        }
        catch(Exception e){
           System.out.println("ERROR! Invalid URL!"); 
           System.exit(0);
        }
        System.out.println("Success! Valid URL!");
        System.out.println(parseCnet.websiteTitle(d) + "\n");
        System.out.println(parseCnet.findPros(d) + "\n");
        System.out.println(parseCnet.findCons(d) + "\n");

        
    }

    @Override
    public void websiteTitle() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void findCons() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void findPros() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
