import java.util.LinkedList;
import java.util.Hashtable;
import java.util.Enumeration;
import java.util.Iterator;

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
    private FrequencyTable page_termFinal;
    private Hashtable page_prioFinal;
    
    public AggregatePages() {
        
    }
    
    //constructor 
    public AggregatePages(LinkedList<FrequencyTable> page_term, LinkedList<Hashtable> page_prio) {
        page_termFrequencies = page_term;
        page_prioritizedTermFrequencies = page_prio;
    }
    
    //return the aggregate frequency table
    public FrequencyTable getAggregateTerms() {
        return page_termFinal;
    }
    
    //return the aggregate prioritized terms hash table
    public Hashtable getAggregatePrioritizedTerms() {
        return page_prioFinal;
    }
    
    //iterates through the frequency table and the hashtable and combines them
    public void aggregateTerms(LinkedList<FrequencyTable> page_termFreq, LinkedList<Hashtable> page_termPrio) {
        page_prioritizedTermFrequencies = page_termPrio;
        FrequencyTable page_FreqTemp = new FrequencyTable();
        //merge the elements in the hash tables
        String term;
        int freq;
        //for each subsequent hash table,
        for(int i=1; i<page_termPrio.size(); i++) {
            //search for each element in first hash table
            Enumeration tablekeys = page_termPrio.get(i).keys();
            Iterator tablevalues = page_termPrio.get(i).values().iterator();
            while(tablekeys.hasMoreElements()) {
                term = (String)tablekeys.nextElement();
                freq = (int)tablevalues.next();
                //if it's not present, add it, if it is then add frequencies
                if(page_termPrio.get(0).containsKey(term)) {
                    page_termPrio.get(0).get(term);
                    page_termPrio.get(0).replace(term,
                            (int)(page_termPrio.get(0).get(term))+freq);
                }
                else {
                    page_termPrio.get(0).put(term, freq);
                }
            }
        }
        //and copy the value over to page_prioFinal
        page_prioFinal = page_termPrio.get(0);
        
       //Experimental code starts here
       //while(!stop) {
       int max = maxFrequencyTableList(page_termFreq);// keeps track of maximum number of runs to get all items in all Frequency tables in the list'
       int idx = 0; //index to increment up until max
       String[] terms = page_termFreq.get(0).termArray();
       int[] freqs = page_termFreq.get(0).freqArray();
       while(idx < max) { //MAY HAVE PROBLEMS WITH RUNNING OUT OF BOUNDS BUT HONESTLY I HAVENT SLEPT IN A DAY GIVE US A BREAK
       for(int i = 0; i < page_termFreq.size(); i++) { //loop for iterating through the elements in the list
           if (page_termFreq.get(i).getSize() > idx) {
               terms = page_termFreq.get(i).termArray();
               freqs = page_termFreq.get(i).freqArray();
               if(page_FreqTemp.getTerm(terms[idx]) != null) {  //case where aggregation is needed
                   page_FreqTemp.getTerm(terms[idx]).frequency = page_FreqTemp.getTerm(terms[idx]).frequency + freqs[idx];
               }
               else { //simply insert
               page_FreqTemp.insert(terms[idx], freqs[idx]);
               }
           }
       }
        idx++;   //increases the paralel aray index 
      }
       page_termFinal = page_FreqTemp; 
    }
    
    //Finds the size of the maximum Frequency table in the list
    public int maxFrequencyTableList(LinkedList<FrequencyTable> tableList) {
        int max = 0;
        for(int i = 0; i < tableList.size(); i++) {
            if(tableList.get(0).getSize() > max)
                max = tableList.get(0).getSize();
            
        }
        return max;
    }
    
    //aggregrate most frequent terms across all pages
    public LinkedList aggregateTerms() {
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
        return aggregatedTerms;
    }
    
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
    /*
    public static void main(String[] args) {
        AggregatePages aggregatePages = new AggregatePages();
        aggregatePages.aggregatePrioritizedTerms();
    }*/
}
