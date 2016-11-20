import java.util.Hashtable;
import java.io.IOException;

/**
 *
 * @author Sophie Salomon
 */

/**
 * for a given set of parsed webpage content (see ParseWebPage), sort words on
 * that page by their frequency, filtering out common words such as "and",
 * "the", etc, and putting the remaining, relevant words in filteredTerms
 * also searches for specific priority terms and puts them in prioritizedTerms
 * and gets information about the webpage: title, publisher, etc
*/

public class AggregatePage {
    
    private String publisher;
    private String title;
    private String body;
    private FrequencyTable frq = new FrequencyTable();
    private Object[][] priorityTermFrequency = new Object[10][2];
    private String words = " ";
    private static FrequencyTable filteredTerms = new FrequencyTable(); 
    private Hashtable prioritizedTerms = new Hashtable(); 

    public AggregatePage(String publisher, String title, String body) {
        this.publisher = publisher;
        this.title = title;
        this.body = body.toLowerCase().replaceAll("[^A-Za-z0-9 ]", "");
    }
    
    //find the frequency of a specific term
    private int findTermCount(String w, String page) {
        System.out.println("Find term count"); 
        page = " " + page + " ";
        int index = page.indexOf(" " + w + " ");
        int count = 0;
        while (index != -1) {
            index = page.indexOf(" " + w + " ");
            if (index == -1) {
                return count;
            }
            count++;
            if (page.length() > w.length()) {
                page = page.substring(index + 1, page.length());
            }
        }
        return count;
    }
    
    //I'm not sure why this is here, but I think it's doing something,
    //so we will keep it
    private void wordsContained(String txt) {
        int j = 0;
        String x = "";
        while (j < body.length()) {
            x = getString(body, j);
            if (words == "" || words == null) {
                words = x + " ";
            } else if (!words.contains(" " + x + " ")) {
                words += x + " ";
            }
            j += x.length() + 1;
        }
        System.out.println(words);
        this.words = words + " ";
    }
    
    //return the first word in a block of text starting at index m
    private String getString(String pT, int m) {
        String s = "";
        for (int i = m; i < pT.length(); i++) {
            if (pT.charAt(i) != ' ') {
                s += pT.charAt(i);
            } else {
                return s;
            }
        }
        return s;
    }
    
    //filters out words to ignore from the given frequency table
    public static FrequencyTable filter (FrequencyTable ft, Terms ignore) {
        FrequencyTable.Entry e;
        for (int i = 3; i < ft.getSize(); i++) 
        {
            e =  ft.get(i);
            if (e != null)
                if (ignore.ignorePhrases.contains(e.getString())) {}
                else 
                    filteredTerms.insert(e.term, e.frequency); 
        }
        return filteredTerms; 
    }
    
    //get the unfiltered frequency table of frequent terms
    public FrequencyTable findTermFrequency() {
        wordsContained(body + " ");
        String[] splits = words.split(" ");
        System.out.println("ftf");
        for (String word : splits) {
            frq.insert(word, findTermCount(word, body));
            System.out.println(frq); 
        }
        return frq;
    }
    
    //get the hash table of prioritized terms
    public Hashtable findPrioritizedTermFrequency() {
        return prioritizedTerms;
    }

    //return the article publisher
    public String getPublisher() {
        return publisher;
    }

    //return the article title
    public String getTitle() {
        return title;
    }

    //return the article body
    public String getBody() {
        return body;
    }
    
    //for testing purposes only
    public static void main(String[] args) throws IOException {
        AggregatePage p = new AggregatePage("Emilio", "Title", "This is a a a a a he he he he Joe Joe Joe Joe Joe aardvark aardvark aardvark aardvark test to figure out if anything works. This is seriously test.");
        Terms terms = new Terms();
        terms.parseFileIgnorePhrases("ignorePhrases.json");
        System.out.println(filter(p.findTermFrequency(),terms));
    }

}
