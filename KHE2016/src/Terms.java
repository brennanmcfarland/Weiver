import java.util.Hashtable;
import org.json.simple.*;

class Terms {
    public FrequencyTable termFrequency; //holds terms and their frequencies
    public Hashtable ignorePhrases; //holds words/phrases to ignore
    
    //parses a json file to load the phrases to ignore when finding frequencies
    public void parseFileIgnorePhrases(String filename) {
        
    }
    
    public static void main(String[] args) {
        System.out.println("hello");
    }
}
