import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

//the frequency sorted lists of terms, in a hashtable-like implementation
//the key is the frequency
public class FrequencyTable {

  public class Entry {
    public String term;
    public int frequency;

    public Entry(String trm, int freq) {
      term = trm;
      frequency = freq;
    }
    public String toString() {
      return term + " " + frequency;
    }
    public String getString () {
        return term; 
    }
  }

  //the table itself is an array list of linked list buckets of entries
  private ArrayList<LinkedList<Entry>> table;
  private int numentries;
  private int tablesize;
  
  //initialize the table
  public FrequencyTable() {
      numentries = 0;
      //initialize with 20 entries
      table = new ArrayList<LinkedList<Entry>>();
      tablesize = 20; 
      table.ensureCapacity(tablesize);
      for(int i=0; i<tablesize; i++) { //initialize the LLs
        table.add(new LinkedList<Entry>());
      }
  }

  //return the number of entries in the hashtable
  public int getSize() {
      return numentries;
  }
  
  public int getTableSize() {
      return tablesize; 
  }

  //probe the hashtable to return a table position given a frequency as key
  public int probe(int freq) {
    if(freq < table.size()-1)
      return freq;
    else
      return table.size()-1;
  }

  //get an entry from the hashtable
  public Entry get(String trm, int freq) {
      int bucket = probe(freq); //find the index of the list to search in
      ListIterator<Entry> iterator =
        table.get(bucket).listIterator(0); //get the iterator
      //the index of the node in the LL
      Entry index = iterator.next();
      index = iterator.previous();
      while(index != null) {
        //if this is the element
        if(index.term == trm) {
          return index; //return it
        }
        //otherwise go to the next element
        index = iterator.next();
      }
      //if we didnt' find the element, just return null
      return null;

  }
  
  //retrieve LinkedList at ArrayList index 
  public LinkedList<Entry> get (int frq) {
      int bucket = probe(frq); 
      return table.get(frq); 
  }

  //insert an entry in the hashtable
  //if larger than hashtable size, put in last spot instead of taking mod
  public void insert(String trm, int freq) {
    int bucket = probe(freq); //find the index to place the new entry in
    //System.out.println("bucket: " + bucket + "table size: " + table.size());
    table.get(bucket).add(new Entry(trm,freq)); //add to list @ that index
    numentries++;
    if(numentries/table.size() > .5) //grow table if needed
      growTable();
  }

  //remove and return an entry from the hashtable
  public Entry remove(String trm, int freq) {
    int bucket = probe(freq); //find the index to remove the new entry from
    //return table.get(bucket).remove()
    Entry removed;
    //return table.get(bucket).remove(removed = (removed.term.equals(trm))?Entry:null);
    ListIterator<Entry> iterator =
      table.get(bucket).listIterator(0); //get the iterator
    //the index of the node in the LL
    Entry index = iterator.next();
    index = iterator.previous();
    while(index != null) {
      //if this is the element
      if(index.term == trm) {
        numentries--;//decrement table size
        table.get(bucket).remove(index);
        return index;
        //and remove the key from the LL
        /*if(iterator.hasPrevious())
          index.previous().next = index.next;
        else
          return table.get(bucket).removeFirst();*/
      }
      //otherwise go to the next element
      index = iterator.next();
    }
    //if we didnt' find the element, just return null
    return null;
  }
  
//remove and return an entry from the hashtable
  public Entry remove(int freq) {
    int bucket = probe(freq); //find the index to remove the new entry from
    //return table.get(bucket).remove()
    Entry removed;
    //return table.get(bucket).remove(removed = (removed.term.equals(trm))?Entry:null);
    ListIterator<Entry> iterator =
      table.get(bucket).listIterator(0); //get the iterator
    //the index of the node in the LL
    Entry index = iterator.next();
    index = iterator.previous();
    while(index != null) {
      //if this is the element
        numentries--;//decrement table size
        table.get(bucket).remove(index);
        return index;
        //and remove the key from the LL
        /*if(iterator.hasPrevious())
          index.previous().next = index.next;
        else
          return table.get(bucket).removeFirst();*/
      }
      //otherwise go to the next element
      index = iterator.next();
    //if we didnt' find the element, just return null
    return null;
  }
  
  public String toString () {
      String tableString = ""; 
      for (int i = 0; i < this.getTableSize(); i++) 
      {
        if(table.get(i).size() > 0)
            tableString += (this.get(i)).toString();
      }
      return tableString; 
  }

  //double table size
  private void growTable() {
    //double the size of the table
    ArrayList<LinkedList<Entry>> oldtable = table;
    table = new ArrayList<LinkedList<Entry>>();
    table.ensureCapacity(table.size()*2);
    for(int i=0; i<table.size()*2; i++) { //initialize the LLs
      table.add(new LinkedList<Entry>());
    }
    tablesize = table.size(); 
    //move the last LL to the new last space
    table.set(table.size()-1, oldtable.get(table.size()-1));
    //rehash everything except the last LL
    for(int i=0; i<table.size()-1; i++) { //for each bucket
      LinkedList bucket = table.get(i);
      if(bucket != null) { //in each non-null LL
        ListIterator<Entry> iterator =
          bucket.listIterator(0); //get the iterator
        //the index of the node in the LL
        Entry index = iterator.next();
        index = iterator.previous();
        while(index != null) {
          //rehash each element
          insert(index.term, index.frequency);
        }
      }
    }
  }

}
