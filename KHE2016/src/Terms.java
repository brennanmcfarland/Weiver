import java.util.Hashtable;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
import java.util.Scanner;
import java.io.*;

class Terms {
    public FrequencyTable termFrequency; //holds terms and their frequencies
    public Hashtable ignorePhrases; //holds words/phrases to ignore
    
    //loads+parses a json file to load the phrases to ignore
    public void parseFileIgnorePhrases(String filename) throws IOException {
        //load the unparsed json from the file
        String unparsedJson = "";
        File file = new File(filename);
        if(!file.exists()) {
            System.out.println("JSON File does not exist!");
            System.exit(0);
        }
        Scanner jsonFile = new Scanner(file);
        while(jsonFile.hasNext())
            unparsedJson += jsonFile.nextLine();
        jsonFile.close();
        
        //parse into a JSONArray
        JSONParser parser = new JSONParser();
        try {
            JSONArray parsedJson = (JSONArray)(parser.parse(unparsedJson));
            System.out.println("first element is " + parsedJson.get(0));
            System.out.println("array size is " + parsedJson.size());
        }catch(ParseException pe) {
            System.out.println("Error: parsing error");
            System.exit(0);
        }
        //then convert put the array elements of that object in the hashtable
    }
    
    public static void main(String[] args) {
        Terms terms = new Terms();
        try{
            terms.parseFileIgnorePhrases("ignorePhrases.json");
        }catch(Exception IOException) {
            System.out.println("IO Exception");
            System.exit(0);
        }
    }
}
