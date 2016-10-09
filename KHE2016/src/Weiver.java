import java.util.Scanner;

public class Weiver {
    
    public static void main(String[] args) {
        
        //get the product as input
        System.out.print("Input Product (Brand and Phone): ");
        Scanner input = new Scanner(System.in);
        String product = input.nextLine();
        
        //initialize parsing objects, set to null if search returns null
        ParseDigitalTrends parseDigitalTrends;
        try{
            parseDigitalTrends = new ParseDigitalTrends(product);
        }catch(Exception NullQueryException) {
            parseDigitalTrends = null;
        }
        ParseCnet parseCnet;
        try{
            parseCnet = new ParseCnet(product);
        }catch(Exception NullQueryException) {
            parseCnet = null;
        }
        
        //print the results of the parsing
        
        try{
            System.out.println("Summaries: \n");
            if(parseDigitalTrends != null)
                System.out.println(parseDigitalTrends.findArticleBody());
            if(parseCnet != null)
                System.out.println(parseCnet.findArticleBody());
        }catch(Exception IOException) {
            System.out.println("IOException!");
        }
        
        /*
        TODO:
        check for null objects
        */
    }
}