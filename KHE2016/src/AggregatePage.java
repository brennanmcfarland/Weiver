import java.util.Hashtable;
import java.io.IOException;

//Sophie Salomon
//Takes text from each webpage, sort by term frequency, then eliminate irrelevant words
//Choose priority terms 
//Count positive/negative/neutral phrases 


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

    private void wordsContained(String txt) {
        System.out.println("got to this point");
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
        System.out.println("End of WC"); 
        System.out.println(words);
        this.words = words + " ";
    }

    private String getString(String pT, int m) {
        System.out.println("got to getString");
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

    public FrequencyTable findTermFrequency() {
        wordsContained(body + " ");
        String[] splits = words.split(" ");
        System.out.println("ftf");
        for (String word : splits) {
            frq.insert(word, findTermCount(word, body));
            System.out.println(frq); 
        }
        System.out.println("got to the end of findterm frequency");
        return frq;
    }

    public Hashtable findPrioritizedTermFrequency() {
        return prioritizedTerms;
    }

    /**
     * @return the publisher
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the body
     */
    public String getBody() {
        return body;
    }

    public static void main(String[] args) throws IOException {
        AggregatePage p = new AggregatePage("Emilio", "Title", "This is a a a a a he he he he Joe Joe Joe Joe Joe aardvark aardvark aardvark aardvark test to figure out if anything works. This is seriously test.");
        Terms terms = new Terms();
        terms.parseFileIgnorePhrases("ignorePhrases.json");
        System.out.println(filter(p.findTermFrequency(),terms));
    }

}
