import java.util.LinkedList;
import java.util.Hashtable;
import java.util.Enumeration;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author brennanmcfarland
 * 
 * Lucas Alva-Ganoza
 */
public class AggregatePages {
    
    private LinkedList<FrequencyTable> page_termFrequencies; //pages' term frequencies
    private LinkedList<Hashtable> page_prioritizedTermFrequencies; //pages' prioritized freqs
    FrequencyTable page_termFinal;
    Hashtable page_prioFinal;
    
    //constructor 
    public AggregatePages(LinkedList<FrequencyTable> page_term, LinkedList<Hashtable> page_prio) {
        page_termFrequencies = page_term;
        page_prioritizedTermFrequencies = page_prio;
    }
    
    //iterates through the frequency table and the hashtable and combines them
    public LinkedList aggregateTerms(LinkedList<FrequencyTable> page_term, LinkedList<Hashtable> page_prio) {
        FrequencyTable page_termReturn;
        Hashtable page_prioReturn;
        
        //iterates through the frequency table and hash table to aggregate and sort
        Iterator<FrequencyTable> iterableFrequencyTable = page_term.descendingIterator();
        while(iterableFrequencyTable.hasNext()) {
            
        }
        
    }
    //aggregrate most frequent terms across all pages
    /*public LinkedList aggregateTerms() {
        //for now, it will just take the 5 most common terms from each
        //table and aggregate, can make more sophisticated later
        LinkedList aggregatedTerms = new LinkedList();
        
        for(int i=0; i<page_termFrequencies.size(); i++) {
            int j=0;
            //also test for edge cases in the future
            while(j < 5) {
                //and this only works if there's enough terms in the max bucket
                aggregatedTerms.add(page_termFrequencies.removeFirst());
                j++;
            }
            
            
        }
        //combine all the tables
        //by combining all but the last bucket of the smaller one with the
        //corresponding larger one, then merging the last bucket in each,
        //then rehashing the larger one
        
        /*FrequencyTable biggerTable,
                       smallerTable;
        for(int bucket=0; bucket<page_termFrequencies.size()-1; bucket++) {
            biggerTable = page_termFrequencies.get(bucket);
            smallerTable = page_termFrequencies.get(bucket+1);
            //swap if smallerTable is actually bigger
            if(biggerTable.getSize() < smallerTable.getSize()) {
                FrequencyTable temp = smallerTable;
                smallerTable = biggerTable;
                biggerTable = temp;
            }
            int i=0; //merge each element of smaller table into larger
            while(i < smallerTable.getSize()) {
                for(int j=0; j<smallerTable.getSize(); j++){
                    LinkedList bucketlist = smallerTable.get(j);
                    for(int k=0; k<bucketlist.size(); k++) {
                        if(biggerTable.get())
                    }
                }
                i++;
            }
            
        }*/
       // return aggregatedTerms;
    //}
    
    //aggregate frequency of prioritized terms across all pages
    public Hashtable aggregatePrioritizedTerms() {
        //clone first table
        Hashtable aggregatePrioritizedTerms = (Hashtable)(page_prioritizedTermFrequencies.get(0).clone());
        Enumeration e = aggregatePrioritizedTerms.keys();
        while(e.hasMoreElements()){ //for each key
            int key = (int)e.nextElement();
            System.out.println(page_prioritizedTermFrequencies.get(0));
            //int val = page_prioritizedTermFrequencies.get(0).get();
            int freqadd = 0;
            //add the frequency from all the other hash tables
            for(int i=1; i<page_prioritizedTermFrequencies.size(); i++) {
                freqadd += (int)page_prioritizedTermFrequencies.get(i).get(key);
            }
            //aggregatePrioritizedTerms.replace(key,aggregatePrioritizedTerms.get(key).frequency+freqadd);
            
        }
        //for each value in the table
        //find it in all the others and add the frequency
        /*Hashtable table1,
                table2;
        for(int bucket=0; bucket<page_prioritizedTermFrequencies.size()-1; bucket++) {
            table1 = page_prioritizedTermFrequencies.get(bucket);
            table2 = page_prioritizedTermFrequencies.get(bucket+1);
            int i=0; //merge each element of smaller table into larger
            while(i < table1.size()) {
                for(int j=0; j<table1.size(); j++){
                    
                    LinkedList bucketlist = smallerTable.get(j);
                    for(int k=0; k<bucketlist.size(); k++) {
                        if(biggerTable.get())
                    }
                }
                i++;
            }
        }*/
        return null;
    }
    
    //aggregate pros across all pages
    public String aggregatePros() {
        
        return null;
    }
    
    //aggregate cons across all pages
    public String aggregateCons() {
        
        return null;
    }
    
    public static void main(String[] args) {
        AggregatePages aggregatePages = new AggregatePages();
        aggregatePages.aggregatePrioritizedTerms();
    }
}
