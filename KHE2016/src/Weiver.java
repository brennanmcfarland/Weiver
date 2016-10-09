import java.util.Scanner;

public class Weiver {
    
    public static void main(String[] args) {
        
        //get the product as input
        System.out.print("Input Product (Brand and Phone): ");
        Scanner input = new Scanner(System.in);
        String product = input.nextLine();
        
        //initialize parsing objects
        ParseDigitalTrends parseDigitalTrends = new ParseDigitalTrends(product);
        ParseCnet parseCnet = new ParseCnet(product);
        
        //print the results of the parsing
        try{
            System.out.println("Summaries: \n");
            System.out.println(parseDigitalTrends.findArticleBody());
            System.out.println(parseCnet.findArticleBody());
        }catch(Exception IOException) {
            System.out.println("IOException!");
        }
        
        /*
        TODO:
        create a custom exception for if a product is not found so the program
        can still continue on the other data
        */
    }
}