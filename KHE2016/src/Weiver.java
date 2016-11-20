import java.util.Scanner;
import java.util.LinkedList;
import java.util.Hashtable;

/**
 *
 * @author Brennan McFarland
 */

public class Weiver {

    public static void main(String[] args) {
        
        /***********************************
            GET INPUT
        ************************************/
        System.out.println("Input Product(Brand and Phone): ");
        Scanner input = new Scanner(System.in);
        String product = input.nextLine();
        
        /***********************************
            CREATE LISTS TO HOLD TERM
            * FREQUENCIES FROM ALL PAGES
            * AND LOAD TERMS
        ************************************/
        LinkedList<FrequencyTable> termFrequencies = new LinkedList();
        LinkedList<Hashtable> prioritizedTermFrequencies = new LinkedList();
        Terms terms = new Terms();
        try{
            terms.parseFileIgnorePhrases("ignorePhrases.json");
        }catch(Exception IOException) {
            System.out.println("IOException parsing terms");
        }
        LinkedList<ParseWebPage> parsedPages = new LinkedList(); 
        LinkedList<AggregatePage> pageData = new LinkedList();
        
        /***********************************
            PROCESS EACH WEBPAGE
            * INDIVIDUALLY
        ************************************/
        ParseDigitalTrends parseDigitalTrends;
        try {
            parseDigitalTrends = new ParseDigitalTrends(product);
        } catch (Exception NullQueryException) {
            parseDigitalTrends = null;
        }
        ParseCnet parseCnet;
        try {
            parseCnet = new ParseCnet(product);
        } catch (Exception NullQueryException) {
            parseCnet = null;
        }
        
        System.out.println("getting results...");
        
        if(parseDigitalTrends != null) {
            try{
                AggregatePage aggregateDigitalTrends = new AggregatePage(
                    parseDigitalTrends.publisher, parseDigitalTrends.websiteTitle(),
                    parseDigitalTrends.findArticleBody());
                termFrequencies.add(AggregatePage.filter(
                        aggregateDigitalTrends.findTermFrequency(), terms));
                prioritizedTermFrequencies.add(
                        aggregateDigitalTrends.findPrioritizedTermFrequency());
                parsedPages.add(parseDigitalTrends);
                pageData.add(aggregateDigitalTrends);
            }catch(Exception IOException) {
                System.out.println("IOException!");
            }
        }
        
        if(parseCnet != null) {
            try{
                AggregatePage aggregateCnet = new AggregatePage(
                    parseCnet.publisher, parseCnet.websiteTitle(),
                    parseCnet.findArticleBody());
                termFrequencies.add(AggregatePage.filter(
                    aggregateCnet.findTermFrequency(), terms));
                prioritizedTermFrequencies.add(
                    aggregateCnet.findPrioritizedTermFrequency());
                parsedPages.add(parseCnet);
                pageData.add(aggregateCnet);
            }catch(Exception IOException) {
                System.out.println("IOException!");
            }
        }
        
        /***********************************
            PROCESS ALL PAGES AS A GROUP
        ************************************/
        AggregatePages pages = new AggregatePages(
                termFrequencies, prioritizedTermFrequencies);
        
        /***********************************
            FORMAT AND DISPLAY OUTPUT
        ************************************/
        FormatOutput output = new FormatOutput(parsedPages, pageData, pages);
        SecretClass x = new SecretClass();
        /*
        TODO:
        check for null objects
         */
    }
}
