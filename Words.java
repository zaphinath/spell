package spell;

import java.util.Arrays;
import java.lang.StringBuilder;

public class Words implements Trie {
	
  private WordNode root;
  private int numberNodes;
  private int numberWords;
  private int hashCode;

  Words() {
    root = new WordNode();
    numberNodes = 1;
    numberWords = 0;
  }
  
  public void add(String word) {  
    WordNode scanner = root;
    word = word.toLowerCase();
    //System.out.println(word);
    int length = word.length();
    this.numberWords++;
    for (int i = 0; i < length; i++) {
      char letter = word.charAt(i);
      int iChar = Character.getNumericValue(letter) - 10;
      hashCode = hashCode + 7 * iChar;
      //if exists than just up count, change scanning node, keep traversing
      Character c1, c2;
      c1 = new Character(letter);
      if (scanner.subNodes[iChar] != null)
        c2 = new Character(scanner.subNodes[iChar].letter);
      else
        c2 = null;
      if (c2 != null) {
        if (c1.compareTo(c2) == 0) {
          scanner.count++;
          scanner = scanner.subNodes[iChar];
        }
      } else {
      //if !exists we need to make it
        WordNode nn = scanner.subNodes[iChar] = new WordNode();
        nn.letter = letter;
        nn.count++;
        nn.prev = scanner;
        //Arrays.sort(scanner.subNodes);
        scanner = nn;
        this.numberNodes++;
      }
      
      
    }
  }
	
	/**
	 * Searches the trie for the specified word
	 * @param word The word being searched for
	 * @return A reference to the trie node that represents the word,
	 * 			or null if the word is not in the trie
	 */
	public Trie.Node find(String word) { 
		WordNode s = root;
		word = word.toLowerCase();
    int length = word.length();
    for (int i = 0; i < length; i++) {
      char letter = word.charAt(i);
      int iChar = Character.getNumericValue(letter) - 10;
      Character c1, c2;
      c1 = new Character(letter);
      if (s.subNodes[iChar] != null) 
        c2 = new Character(s.subNodes[iChar].letter);
      else
        c2 = null;
      if (c2 != null) {
        if (c1.compareTo(c2) == 0) {
          s = s.subNodes[iChar];
        } 
      }else {
        return null;
      }
    }
    return s;
	}

  protected String findSimilar(String word) {
    Trie.Node t = new WordNode();
    word = word.toLowerCase();
    String shortest = null;
    //Delete Edit
    for (int i = 0; i < word.length(); i++) {
      StringBuilder foo = new StringBuilder(word);
      //System.out.println("Delete");
      Trie.Node s = new WordNode();
      String tmp = foo.deleteCharAt(i).toString();
      //System.out.println("tmp: "+tmp);
      s = find(tmp);
      if (s != null) {
        if (s.getValue() > t.getValue()) {
          t = s;
          shortest = tmp;
        }
      }
    }
    //Transpose
    for (int i = 0; i < word.length() -1; i++) {
      StringBuilder foo = new StringBuilder(word);
      //System.out.println("Transpose");
      Trie.Node s = new WordNode();
      char a = foo.charAt(i);
      char b = foo.charAt(i+1);
      StringBuilder tmp = foo;
      tmp.setCharAt(i, b);
      tmp.setCharAt(i+1,a);
      s = find(tmp.toString());
      //System.out.println("tmp: "+tmp.toString());
      if (s != null) {
        if (s.getValue() > t.getValue()) {
          t = s;
          shortest = tmp.toString();
        }
      }
    }
    //Alteration
    
    for (int i = 0; i < word.length()-1; i++) {
      for (int j = 97; j < 123; j++) {
        StringBuilder foo = new StringBuilder(word);
        Trie.Node s = new WordNode();
        char a = (char) j;
        StringBuilder tmp = foo;
        tmp.setCharAt(i,a);
        //System.out.println(tmp.toString());
        s = find(tmp.toString());
        if (s != null) {
          if (s.getValue() > t.getValue()) {
            t = s;
            shortest = tmp.toString();
          }
        }     
      }
    }
    //Insertion
    for (int i = 0; i < word.length()+1; i++) {
      for (int j = 97; j < 123; j++) {
        StringBuilder foo = new StringBuilder(word);
        Trie.Node s = new WordNode();
        char a = (char) j;
        StringBuilder tmp = foo;
        tmp.insert(i,a);
        s = find(tmp.toString());
        if (s != null) {
          if (s.getValue() > t.getValue()) {
            t = s;
            shortest = tmp.toString();
          }
        }     
      }
    }
    return shortest;
  }
  private Trie.Node findTranspose(String word) {
    WordNode s = new WordNode();
    return s;
  }
   Trie.Node findAlteration(String word) {
    WordNode s = new WordNode();
    return s;
  }
  public Trie.Node findInsertion(String word) {
    WordNode s = new WordNode();
    return s;
  }

	public int getWordCount() { 
		return this.numberWords;
	}

	public int getNodeCount() { 
		return this.numberNodes;
	}
	public String toString() { 
		return "string";
	}

	@Override
	public int hashCode() { 
		return this.hashCode;
	}

	@Override
	public boolean equals(Object o) { 
		WordNode s = root;
    WordNode t = (WordNode) o;
    System.out.println("equals");
    return compare(s,t);
	}
  private boolean compare(WordNode a, WordNode b) {
    if (a.count != b.count || a.letter != b.letter) {
      return false;
    }
    for (int i = 0; i < 26; i++) {
      compare(a.subNodes[i], b.subNodes[i]);
    }
    return true;
  }

}
