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
        ************************************/
        LinkedList<FrequencyTable> termFrequencies = new LinkedList();
        LinkedList<Hashtable> prioritizedTermFrequencies = new LinkedList();
        
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
                Terms terms = new Terms();
                terms.parseFileIgnorePhrases("ignorePhrases.json");
                termFrequencies.add(AggregatePage.filter(
                        aggregateDigitalTrends.findTermFrequency(), terms));
                prioritizedTermFrequencies.add(
                        aggregateDigitalTrends.findPrioritizedTermFrequency());
            }catch(Exception IOException) {
                System.out.println("IOException!");
            }
        }
        
        /***********************************
            PROCESS ALL PAGES AS A GROUP
        ************************************/
        AggregatePages pages = new AggregatePages(
                termFrequencies, prioritizedTermFrequencies);
        
        /*
        TODO:
        check for null objects
         */
    }
}
