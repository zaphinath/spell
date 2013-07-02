package spell;

import java.util.ArrayList;

public class WordNode implements Trie.Node {
  public int count = 0;
  public WordNode next;
  public WordNode prev;
  public WordNode[] subNodes;
  public char letter;
  
  public WordNode(){
    prev = null;
    next = null;
    subNodes = new WordNode[26];
  }

	
  /**
	* Returns the frequency count for the word represented by the node
	* @return The frequency count for the word represented by the node
  */
  public int getValue() { 
		return this.count;
	}
}

