/*
 * File: Hangman.java
 * Name:
 * Section Leader:
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4. 
 * TODO: add a class comment
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;

public class Hangman extends ConsoleProgram { 
	
	private HangmanLexicon lexicon;
	private int guess;
	private int correct_cnt;
	private String answer_correct;
	private String answer_now;
	private HangmanCanvas canvas;
	
    public void run() {
		
    	setFont("Consolas-*-24");
    	setSize(1000, 800);
    	lexicon = new HangmanLexicon();
    	
    	gameInit();    	  	    	
    	
    	while(true) {
    		println("The word now looks like this : " + answer_now);
    		println("You have " + guess + " guesses left.");
    		canvas.displayWord(answer_now);
    		char ch_guess = readChar("Your guess: ");
    		if(!Character.isLetter(ch_guess)) {
    			println("Please input a letter !");
    			continue;
    		}
    		checkGuess(ch_guess);
    		if(guess==0){
    			println("You're completedly hung.");
    			println("The word was " + answer_correct);
    			println("You lose.");
    			break;
    		} else if(correct_cnt == answer_correct.length()) {
    			canvas.displayWord(answer_now);
    			println("You guesses the word: " + answer_correct);
    			printf("You win.");
    			break;
    		} else {
    			continue;
    		}
    	}
	}
    
    public void init(){
    	canvas = new HangmanCanvas();
    	canvas.setSize(1000, 800);
    	add(canvas);
    }
    
    void gameInit() {
    	int lexicon_wordcnt = lexicon.getWordCount();
    	RandomGenerator rgen = RandomGenerator.getInstance();
    	int lexicon_index = rgen.nextInt(0, lexicon_wordcnt-1);
    	answer_correct = lexicon.getWord(lexicon_index);
    	answer_now = "";
    	for(int i=0;i<answer_correct.length();++i){
    		answer_now += '-';
    	}
    	guess = 8;
    	correct_cnt = 0;
    	println("Welcome to Hangman!");
    	println(answer_correct);
    	
    	canvas.reset();
    	
    }
    
    char readChar(String prompt){
    	String buffer = readLine(prompt);
    	char ch = buffer.charAt(0);
    	return ch;
    }
    
    void checkGuess(char ch_guess) {
    	boolean isGuessCorrect = false;
    	String answer_buf = "";
    	char ch_buffer1 = Character.toUpperCase(ch_guess);
    	for(int i=0; i<answer_correct.length();++i) {
    		char ch_buffer2 = Character.toUpperCase(answer_correct.charAt(i));
    		char ch_buffer3 = answer_now.charAt(i);
    		if(ch_buffer1 == ch_buffer2) {
    			if(ch_buffer3!='-')
    				return;
    			isGuessCorrect = true;
    			correct_cnt++;
    			answer_buf += answer_correct.charAt(i);
    		} else {
    			answer_buf += answer_now.charAt(i);
    		}
    	}
    	answer_now = answer_buf;
    	if(isGuessCorrect){
    		println("That guess is correct.");
    	} else{
    		--guess;
    		println("There are no " + ch_guess +"'s in the world.");
    		canvas.noteIncorrectGuess(ch_guess);
    	}
    }
}
