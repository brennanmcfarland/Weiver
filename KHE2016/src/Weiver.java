import java.util.Scanner;

public class Weiver {

    public static void main(String[] args) {

        //get the product as input
        System.out.print("Input Product (Brand and Phone): ");
        Scanner input = new Scanner(System.in);
        String product = input.nextLine();

        //initialize parsing objects, set to null if search returns null
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

        try {
            //aggregate data in the parsing objects
            
            
            if (parseCnet != null) {
                System.out.println("got to here");
                AggregatePage aggregateCnet = new AggregatePage(
                        parseCnet.publisher, parseCnet.websiteTitle(),
                        parseCnet.findArticleBody());
                System.out.println("and to here");
                Terms terms = new Terms();
                terms.parseFileIgnorePhrases("ignorePhrases.json");
                FrequencyTable agCnet = aggregateCnet.findTermFrequency(); 
                System.out.println("Test"); 
                System.out.println(AggregatePage.filter(aggregateCnet.findTermFrequency(), terms));
                System.out.println("and also to here");
                if (parseDigitalTrends != null) {
                    AggregatePage aggregateDigitalTrends = new AggregatePage(
                            parseDigitalTrends.publisher, parseDigitalTrends.websiteTitle(),
                            parseDigitalTrends.findArticleBody());
                }
            }

            //print the results of the parsing
            System.out.println("Summaries: \n");
            if (parseDigitalTrends != null) {
                System.out.println(parseDigitalTrends.findArticleBody());
            }
            if (parseCnet != null) {
                System.out.println(parseCnet.findArticleBody());
            }
        } catch (Exception IOException) {
            System.out.println("IOException!");
        }

        /*
        TODO:
        check for null objects
         */
    }
}
