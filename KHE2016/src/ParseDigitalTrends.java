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
    
    String websiteTitle()throws IOException {
        // TODO Auto-generated method stub
	return ("Title: " + d.title());
    }
    
    void findArticleBody()throws IOException {
        // TODO Auto-generated method stub
        Elements body = d.getElementsByTag("article");
        System.out.println(body.text());
    }

    String findPros()throws IOException {
        // TODO Auto-generated method stub
        return null;
    }
    
    String findCons()throws IOException {
        // TODO Auto-generated method stub
        return null;
    }
    
    public static void main(String[] args) throws IOException, IllegalArgumentException {
        // TODO Auto-generated method stub
        ParseDigitalTrends parseDigitalTrends;
        Scanner input = new Scanner(System.in);
        System.out.print("input url: ");
        String url = input.next();
        input.close();
        try{
            parseDigitalTrends = new ParseDigitalTrends(url);
            System.out.println("Success! Valid URL!");
            System.out.println(parseDigitalTrends.websiteTitle() + "\n");
            System.out.println(parseDigitalTrends.findPros() + "\n");
            System.out.println(parseDigitalTrends.findCons() + "\n");
        }
        catch(Exception e){
           System.out.println("ERROR! Invalid URL!"); 
           System.exit(0);
        }
        
    }
    /*
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
  */
}
