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
    
	public static String websiteTitle(String url) throws IOException {
		// TODO Auto-generated method stub
        Document d=Jsoup.connect(url).timeout(6000).get();
        System.out.println("Connected! \n");
	return d.title();	
	}

    	@Override
	public void findArticleBody() throws IOException{
		// TODO Auto-generated method stub
	}

	@Override
	public void findSubtitle() throws IOException{
		// TODO Auto-generated method stub

	}

	@Override
	public void findPros() throws IOException{
		// TODO Auto-generated method stub

	}

	@Override
	public void findCons() {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
Scanner input = new Scanner(System.in);
    System.out.print("input url: ");
		System.out.println("Title: " + websiteTitle(input.next()));
		input.close();
	}

    @Override
    public void websiteTitle() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
