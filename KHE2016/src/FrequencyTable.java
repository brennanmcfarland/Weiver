import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.TreeSet;

/**
 *
 * @author Brennan McFarland
 */

/**
 * holds a frequency-sorted list of terms, in a hashtable-like implementation
 * the key is the frequency of the term
 */

public class FrequencyTable {

  //comparable interface orders the elements by frequency
  public class Entry implements Comparable<Entry> {
      
    public String term;
    public int frequency;

    public Entry(String trm, int freq) {
      term = trm;
      frequency = freq;
    }
    
    public String toString() {
      return term + " " + frequency;
    }
    
    //returns the term
    public String getString () {
        return term; 
    }
    
    //compares the frequency to another frequency
    public int compareTo(Entry o) {
        return this.frequency-o.frequency;
    }
  }

  private TreeSet<Entry> entries;
          
  public FrequencyTable() {
      entries = new TreeSet<Entry>();
  }
  
  //returns the number of elements in the frequency table
  public int getSize() {
      return entries.size();
  }

  //get an entry from the frequency table
  public Entry get(String trm, int freq) {
      Iterator<Entry> iterableentries = entries.descendingIterator();
      while(iterableentries.hasNext()) {
          Entry entry = iterableentries.next();
          if(entry.term.equals(trm) && entry.frequency == freq)
              return entry;
      }
      return null;
  }
  
  //get an entry from the frequency table
  public Entry get(int freq) {
      Iterator<Entry> iterableentries = entries.descendingIterator();
      while(iterableentries.hasNext()) {
          Entry entry = iterableentries.next();
          if(entry.frequency == freq)
              return entry;
      }
      return null;
  }
  //insert an entry in the frequency table
  public void insert(String trm, int freq) {
      entries.add(new Entry(trm,freq));
  }

  //remove and return the top entry
  public Entry removeFirst() {
    return entries.pollFirst();
  }
  
  public String toString () {
      return entries.toString(); 
  }
}
