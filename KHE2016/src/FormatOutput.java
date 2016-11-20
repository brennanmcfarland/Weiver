import java.util.LinkedList;
import java.util.Iterator;

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
    
    //formats data from all the pages, individually and in aggregate
    public String formatPages() {
        StringBuilder output = new StringBuilder();
        
        output.append("_____OUTPUT_____");
                
        for(int i=0; i<parsedPages.size(); i++) {
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
            output.append("Pros: ");
            output.append(parsed.findPros());
            output.append("\n\n");
            
            //format the cons
            output.append("Cons: ");
            output.append(parsed.findCons());
            output.append("\n\n");
            
        }catch(Exception IOException) {
            System.out.println("Error formatting output");
            return output.append("[ERROR]").toString();
        }
        return output.toString();
    }
    
}
