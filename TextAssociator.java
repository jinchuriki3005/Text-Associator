//Max Tran, CSE 373 Winter 2017, HW#3
//this class used hashTable with separate chaining to represent a collection
//of associations between words with the skeleton code provided
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/* CSE 373 Starter Code
 * @Author Kevin Quinn
 * 
 * TextAssociator represents a collection of associations between words.
 * See write-up for implementation details and hints
 * 
 */
public class TextAssociator {
	private WordInfoSeparateChain[] table;
	private double elementSize;
   private int sizeIndex;
   private static final double LOAD_THRESHOLD = 0.75;
   private final int[] PRIMES_TABLE = {17, 37, 73, 149, 307, 617, 1237, 2543, 5101, 
		10289, 20593, 41117, 82699, 170003, 340007, 680159, 1360531, 2730181, 
		5470219, 11000027	, 22000129};

	/* INNER CLASS
	 * Represents a separate chain in your implementation of your hashing
	 * A WordInfoSeparateChain is a list of WordInfo objects that have all
	 * been hashed to the same index of the TextAssociator
	 */
	private class WordInfoSeparateChain {
		private List<WordInfo> chain;
		
		/* Creates an empty WordInfoSeparateChain without any WordInfo
		 */
		public WordInfoSeparateChain() {
			this.chain = new ArrayList<WordInfo>();
		}
		
		/* Adds a WordInfo object to the SeparateCahin
		 * Returns true if the WordInfo was successfully added, false otherwise
		 */
		public boolean add(WordInfo wi) {
		   if (!chain.contains(wi)) {
				chain.add(wi);
				return true;
			}
			return false;
			// TODO: implement as explained in spec
		}
		
		/* Removes the given WordInfo object from the separate chain
		 * Returns true if the WordInfo was successfully removed, false otherwise
		 */
		public boolean remove(WordInfo wi) {
      	if (chain.contains(wi)) {
				chain.remove(wi);
				return true;
			}
			return false;
		}
		
		// Returns the tableSize of this separate chain
		public int size() {
			return chain.size();
		}
		
		// Returns the String representation of this separate chain
		public String toString() {
			return chain.toString();
		}
		
		// Returns the list of WordInfo objects in this chain
		public List<WordInfo> getElements() {
			return chain;
		}
	}
	
	/* Creates a new TextAssociator without any associations 
	 */
	public TextAssociator() {
      sizeIndex = 0;
      elementSize = 0;
      table = new WordInfoSeparateChain[PRIMES_TABLE[sizeIndex]];
	}
	
	/* Adds a word with no associations to the TextAssociator 
	 * Returns False if this word is already contained in your TextAssociator ,
	 * Returns True if this word is successfully added
	 */
	public boolean addNewWord(String word) {
      boolean succesfullyAdded = false;
      int index = getHashIndex(word);
      //if it's empty, create a new WordInfoSeparateChain
      if (isEmpty(index)) { 
  			table[index] = new WordInfoSeparateChain();
      }
      //determine whether the word is in the list
		for (WordInfo item: table[index].getElements()) {
			if (item.getWord().equalsIgnoreCase(word)) {
				return false;
			}
		}
      //if the load factor reach its limit, expand the hashTable
      if (loadFactor() > LOAD_THRESHOLD) { 
         sizeIndex++;
   	   table = increaseTableSize(table);
         return addNewWord(word);
  		} else { //add word to the list at the index
         elementSize++;
      	table[index].add(new WordInfo(word));
         succesfullyAdded = true;         
      }
      return succesfullyAdded;
	}  
   
   //calculating the loadFactor of the current hashTable
   private double loadFactor(){
      return (double)elementSize/table.length;
   }
   
   //Expands the passed table size with the size of a prime number in the PRIME_TABLE
   //and copies all over the element and rehash them appropriately
   private WordInfoSeparateChain[] increaseTableSize(WordInfoSeparateChain[] table){
      WordInfoSeparateChain[] tempTable = new WordInfoSeparateChain[PRIMES_TABLE[sizeIndex]];
      //goes through every possible index in the table
      for(WordInfoSeparateChain chain : table){
         if (chain != null) {
            //for each separate chain, grab each individual WordInfo
   		   for (WordInfo wordItem: chain.getElements()) {
   				int hashIndex = getHashIndex(wordItem.getWord());
   					//Pull the word out of the previous table, assign a new
   					//hash code, and place it in new table
   				if (tempTable[hashIndex] == null) {
   						tempTable[hashIndex] = new WordInfoSeparateChain();
   				}
   				tempTable[hashIndex].add(wordItem);
   			}
         }
      }
      return tempTable;
   }
	
	/* Adds an association between the given words. Returns true if association correctly added, 
	 * returns false if first parameter does not already exist in the TextAssociator or if 
	 * the association between the two words already exists
	 */
	public boolean addAssociation(String word, String association) {
		int index = getHashIndex(word);
      //check if the current index is empty
      if (!isEmpty(index)) {
         //determine whether the word is already in the list at referenced index
			for(WordInfo item : table[index].getElements()) {
   		   if (item.getWord().equalsIgnoreCase(word)) {
               //if the word exists, add the association
      			item.addAssociation(association);
      			return true;
   		   }
   		}
      } 
      return false;
	}
	
	/* Remove the given word from the TextAssociator, returns false if word 
	 * was not contained, returns true if the word was successfully removed.
	 * Note that only a source word can be removed by this method, not an association.
	 */
	public boolean remove(String word) {
      int index = getHashIndex(word);
      if (!isEmpty(index)) {
         for (WordInfo item: table[index].getElements()) {
			   if (item.getWord().equalsIgnoreCase(word)) {
				//Remove the word from the list
				   table[index].remove(item);
				   elementSize--;
					return true;
   			}
   		}
     	}
     	return false;
   }
   
   //Assign a hashIndex to the passed in word
   private int getHashIndex(String word){
   	if (word == null) {
			throw new IllegalArgumentException();
		}
      //hash function
		int index = Math.abs(word.hashCode() % PRIMES_TABLE[sizeIndex]);
		return index;
   }
   
   //return the size of the hashTable
   private int getTableSize() {
		return PRIMES_TABLE[sizeIndex];
	}
	
	/* Returns a set of all the words associated with the given String  
	 * Returns null if the given String does not exist in the TextAssociator
	 */
	public Set<String> getAssociations(String word) {
      int index = getHashIndex(word);
      if(!isEmpty(index)){
      	for(WordInfo item : table[index].getElements()) {
   			if (item.getWord().equalsIgnoreCase(word)) {
   				//Return set of associations
   				return item.getAssociations();
   			}
   		}
      }
      return null;
	}
	
	/* Prints the current associations between words being stored
	 * to System.out
	 */
	public void prettyPrint() {
		System.out.println("Current number of elements : " + elementSize);
		System.out.println("Current table tableSize: " + table.length);
		//Walk through every possible index in the table
		for (int i = 0; i < table.length; i++) {
			if (table[i] != null) {
				WordInfoSeparateChain bucket = table[i];
				//For each separate chain, grab each individual WordInfo
				for (WordInfo curr : bucket.getElements()) {
					System.out.println("\tin table index, " + i + ": " + curr);
				}
			}
		}
		System.out.println();
	}
   
   //check if the table at index n is empty
   public boolean isEmpty(int n) {
		return table[n] == null;
	}
}
