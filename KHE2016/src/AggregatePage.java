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
    private String words = " ";

    public AggregatePage(String publisher, String title, String body, String pros, String cons) {
        this.publisher = publisher;
        this.title = title;
        this.body = body.toLowerCase().replaceAll("[^A-Za-z0-9 ]", "");
        this.pros = pros;
        this.cons = cons;
    }

    private int findTermCount(String w, String page) {
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

        this.words = words + " ";
    }

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

    public FrequencyTable findTermFrequency() {
        wordsContained(body + " ");
        String[] splits = words.split(" ");
        for (String word : splits) {
            frq.insert(word, findTermCount(word, body));
        }
        return frq;
    }

    private void findPriorityTermFrequency() {

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

    public static void main(String[] args) {
        AggregatePage p = new AggregatePage("Emilio", "Title", "This is a test to figure out if anything works. This is seriously test.", "ye", "nah");
        System.out.println(p.findTermFrequency());
    }

}
