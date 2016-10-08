<<<<<<< HEAD

import java.io.IOException;
import java.util.regex.*;
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
        StringBuilder sb = new StringBuilder();
        String title = d.select("div.quickinfo > *").toString();        
        title = title.replaceAll("\\<.*?>", "");
        title = title.substring(8);
        
        // The Good section of a review is separated by a new line 
        for (int i = 0; i < title.length(); i++){
            if (title.charAt(i) != '\n')
                sb.append(title.charAt(i));
            else
                break;
        }
        
        return("Pros: " + sb.toString()); 
    }

    public String findCons(Document d) throws IOException {
        // TODO Auto-generated method stub
        int newLineCounter = 0;
        
        StringBuilder sb = new StringBuilder();
        String title = d.select("div.quickinfo > *").toString();        
        title = title.replaceAll("\\<.*?>", "");
        
        
        /* Filters the Bad part of the summary from the Good and Bottomline*/
        for (int i = 0; i < title.length(); i++){
            
            if (newLineCounter == 1)
                sb.append(title.charAt(i));
                        
            if (title.charAt(i) == '\n')
                newLineCounter++;
            
            if (newLineCounter > 1)
                break;            
        }        
        String returnString = sb.toString().substring(7);
        return("Cons: " + returnString); 
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
=======

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

    
    public String findArticleBody(Document d) throws IOException {
        // TODO Auto-generated method stub
        String title = d.select("div.editorReview").text();
        title = title.replaceAll("\\<.*?>", "");
        return("Summery: " +  title); 
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
        System.out.print("Input Product (Brand and Phone): ");
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
        product = ("https://www.cnet.com/products/" + sb.toString());
        input.close();
        try{
            d = Jsoup.connect(product).timeout(6000).get();
        }
        catch(Exception e){
           System.out.println("ERROR! Invalid Product!"); 
           System.exit(0);
        }
        System.out.println("Success! Valid Product!");
        System.out.println(parseCnet.websiteTitle(d) + "\n\n");
        System.out.println(parseCnet.findArticleBody(d) + "\n");
        System.out.println(parseCnet.findPros(d) + "\n");
        System.out.println(parseCnet.findCons(d) + "\n");

        
    }

    @Override
    public String websiteTitle() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String findArticleBody() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String findPros() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String findCons() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
>>>>>>> bd31d959ab06582b575cfcabf24a811fa804ab25
