//Sophie Salomon
//Takes text from each webpage, sort by term frequency, then eliminate irrelevant words
//Choose priority terms 
//Count positive/negative/neutral phrases 
//import java.net.URL;

public class AggregatePage {
    //private Terms terms = new Terms();  --- Make static 
    private String publisher; 
    private String title; 
    private String body;
    private String pros;
    private String cons;
    private FrequencyTable frq = new FrequencyTable(); 
    private Object[][] priorityTermFrequency = new Object[10][2]; 
    private String words; 
    
    public AggregatePage (String publisher, String title, String body, String pros, String cons) {       
        this.publisher = publisher; 
        this.title = title;
        this.body = body.toLowerCase().replaceAll("[^A-Za-z0-9 ]", "");
        this.pros = pros;
        this.cons = cons;
    }

    private int findTermCount (String w, String pageOrig) {
        int index = pageOrig.indexOf(w);
        int count = 0;
        while (index != -1) {
            count++;
            pageOrig = pageOrig.substring(index + 1);
            index = pageOrig.indexOf(w);       
        } 
        return count;
    } 
    private String wordsContained (String txt) {
        //for (int j = 0; j < pageText.length(); j) {
        int j = 0 ;
        String x = "hi"; 
        while (j < body.length()) {
            x = getString(body, j);
            if (!words.contains(x)) 
                words += " " + x; 
            j += x.length(); 
        }
        return words; 
    }
    private String getString (String pT, int m) {
        String s = null; 
        for (int i = m; i < body.length(); i ++) {
            if(body.charAt(i)!=' ')
            s += body.charAt(i); 
        }
        System.out.println("Word"); 
        return s; 
    }
    public FrequencyTable findTermFrequency () {
        words = wordsContained (body); 
        System.out.println("Help"); 
        for (int k = 0; k < words.length(); ) {
            String t = getString(words, k); 
            k += t.length(); 
            frq.insert(t,findTermCount(t,body)); 
        }
        return frq; 
    }
    private void findPriorityTermFrequency () {
        
    } 
    
    
    
    public static void main (String[] args) {
        AggregatePage p = new AggregatePage("Emilio", "Title", "This is a test to figure out if anything works. This is seriously test.", "ye", "nah");  
        System.out.println(p.findTermFrequency()); 
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

    /**
     * @return the pros
     */
    public String getPros() {
        return pros;
    }

    /**
     * @return the cons
     */
    public String getCons() {
        return cons;
    }
}
