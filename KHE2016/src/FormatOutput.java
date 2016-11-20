import java.util.LinkedList;
import java.util.Iterator;
import java.util.Hashtable;
import java.util.Enumeration;
import java.util.Map;

/**
 *
 * @author Brennan McFarland
 */

/**
 * formats the data for neatly presented output to the user
 */
public class FormatOutput {
    
    LinkedList<ParseWebPage> parsedPages;
    LinkedList<AggregatePage> pageData;
    AggregatePages aggregateData;
    
    public FormatOutput(LinkedList<ParseWebPage> parsedPages, 
            LinkedList<AggregatePage> pageData, AggregatePages aggregateData) {
        this.parsedPages = parsedPages;
        this.pageData = pageData;
        this.aggregateData = aggregateData;
    }
    
    //print the formatted output
    public void printOutput() {
        System.out.println(formatPages());
    }
    
    //formats data from all the pages, individually and in aggregate
    public String formatPages() {
        StringBuilder output = new StringBuilder();
        
        output.append("\n\n\n_____OUTPUT_____\n");
        
        //format data from the pages in aggregate
        //format the top 10 terms in order
        output.append("Top ten terms: ");
        String[] mostcommonterms = aggregateData.getAggregateTerms().termArray();
        output.append(mostcommonterms[0]);
        for(int i=1; i<10; i++) {
            output.append(", ");
            output.append(mostcommonterms[i]);
        }
            
        //format the frequencies of the priority terms
        // prioritizedTerms = aggregateData.getAggregatePrioritizedTerms().elements();
        
        
        //format data from the individual pages
        for(int i=0; i<parsedPages.size(); i++) {
            output.append("\n\n");
            output.append(formatPage(parsedPages.get(i),pageData.get(i)));
            output.append("\n\n");
        }
        return output.toString();
    }
    
    //given a ParseWebPage and AggregatePage object, returns a formatted string
    public String formatPage(ParseWebPage parsed, AggregatePage aggregated) {
        StringBuilder output = new StringBuilder();
        try{
            //format the title
            output.append(parsed.websiteTitle());
            output.append("\n");
            
            //format the publisher
            output.append("Publisher: ");
            output.append(aggregated.getPublisher());
            output.append("\n\n");
            
            //format the pros
            output.append(parsed.findPros());
            output.append("\n\n");
            
            //format the cons
            output.append(parsed.findCons());
            output.append("\n\n");
            
            //format the top 10 terms in order
            output.append("Top ten terms from this page: ");
            String[] mostcommonterms = aggregated.getFilteredTerms().termArray();
            output.append(mostcommonterms[0]);
            for(int i=1; i<10; i++) {
                output.append(", ");
                output.append(mostcommonterms[i]);
            }
                        
        }catch(Exception IOException) {
            System.out.println("Error formatting output");
            return output.append("[ERROR]").toString();
        }
        return output.toString();
    }
    
}
