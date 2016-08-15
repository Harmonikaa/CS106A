/*
 * File: HangmanLexicon.java
 * Name:
 * Section Leader:
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 * TODO: add a class comment
 */


import acm.util.*;
import java.io.*;


public class HangmanLexicon {
	
		
/** 
 * Returns the number of words in the lexicon.
 * TODO: update this in part 3 of the assignment to use the wordbank
 * and it's associated methods.
 */
	public int getWordCount() {
		return 10;
	}

/** 
 * Returns the word at the specified index. 
 * TODO: update this in part 3 of the assignment to use the 
 * wordbank and it's associated methods
 */
	public String getWord(int index) {
		switch (index) {
			case 0: return "BUOY";
			case 1: return "COMPUTER";
			case 2: return "CONNOISSEUR";
			case 3: return "DEHYDRATE";
			case 4: return "FUZZY";
			case 5: return "HUBBUB";
			case 6: return "KEYHOLE";
			case 7: return "QUAGMIRE";
			case 8: return "SLITHER";
			case 9: return "ZIRCON";
			default: throw new ErrorException("getWord: Illegal index");
		}
	}
}
