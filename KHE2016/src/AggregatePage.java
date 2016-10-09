//Sophie Salomon
//Takes text from each webpage, sort by term frequency, then eliminate irrelevant words
//Choose priority terms 
//Count positive/negative/neutral phrases 
//import java.net.URL;

public class AggregatePage extends WebPage {
    //private Terms terms = new Terms();  --- Make static 
    private String link; 
    private String pageText; 
    private String pub; 
    private FrequencyTable frq = new FrequencyTable(); 
    private Object[][] priorityTermFrequency = new Object[10][2]; 
    private String words; 
    
    public AggregatePage (WebPage page) {
        link = URL; 
        pageText = page.articleBody.toLowerCase().replaceAll("[^A-Za-z0-9 ]", "");
        pub = publisher; 
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
        while (j < pageText.length()) {
            x = getString(pageText, j);
            if (!words.contains(x)) 
                words += " " + x; 
            j += x.length(); 
        }
        return words; 
    }
    private String getString (String pT, int m) {
        String s = null; 
        for (int i = m; i < pageText.length(); i ++) {
            if(pageText.charAt(i)!=' ')
            s += pageText.charAt(i); 
        }
        System.out.println("Word"); 
        return s; 
    }
    public FrequencyTable findTermFrequency () {
        words = wordsContained (pageText); 
        System.out.println("Help"); 
        for (int k = 0; k < words.length(); ) {
            String t = getString(words, k); 
            k += t.length(); 
            frq.insert(t,findTermCount(t,pageText)); 
        }
        return frq; 
    }
    private void findPriorityTermFrequency () {
        
    } 
    
    public String getLink () {
        return link; 
    }
    
    public String getPageText () {
        return pageText; 
    }
    
    public String getPublisher () {
        return pub; 
    }
    
    public static void main (String[] args) {
        AggregatePage p = new AggregatePage(new CNETPage ("Title", "This is a test of the Aggregate page class. This is a test of the Aggregate"));  
        System.out.println(p.findTermFrequency()); 
    }
}
