/*
 * File: HangmanCanvas.java
 * Name:
 * Section Leader:
 * ------------------------
 * This file keeps track of the Hangman display.
 * TODO: add a class comment
 */

import java.awt.Font;

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {

	private int stage;
	private GLabel label1;
	private GLabel label2;
	private String faultstring;
	private Font font1 = new Font("SansSerif", Font.BOLD, 30);
	private Font font2 = new Font("SansSerif", Font.PLAIN, 20);
/** Resets the display so that only the scaffold appears */
	public void reset() {
		/* You fill this in */
		stage = 0;
		removeAll();
		paintScaffold();
		label1 = new GLabel("", 75, getHeight()/2 + 100);
		label1.setFont(font1);
		add(label1);
		faultstring = "";
		label2 = new GLabel(faultstring, 75, getHeight()/2 + 150);
		label2.setFont(font2);
		add(label2);
	}

/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {
		/* You fill this in */
		label1.setLabel(word);
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char letter) {
		/* You fill this in */
		faultstring += letter;
		label2.setLabel(faultstring);
		switch(stage){
		case 0:
			paintHead();
			break;
		case 1:
			paintBody();
			break;
		case 2:
			paintLeftHand();
			break;
		case 3:
			paintRightHand();
			break;
		case 4:
			paintLeftLeg();
			break;
		case 5:
			paintRightLeg();
			break;
		case 6:
			paintLeftFoot();
			break;
		case 7:
			paintRightFoot();
			break;
		}
		++stage;
		
	}

/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;
	
	private void paintScaffold(){
		double x1 = getWidth()/2 - BEAM_LENGTH;
		double y1 = getHeight()/2;
		double x2 = x1;
		double y2 = y1 - SCAFFOLD_HEIGHT;
		double x3 = x2 + BEAM_LENGTH;
		double y3 = y2;
		double x4 = x3;
		double y4 = y3 + ROPE_LENGTH;
		GLine scaffold = new GLine(x1,y1,x2,y2);
		GLine beam = new GLine(x2,y2,x3,y3);
		GLine rope = new GLine(x3,y3,x4,y4);
		add(scaffold);
		add(beam);
		add(rope);
	}
	
	private void paintHead(){
		double x1 = getWidth()/2 - HEAD_RADIUS;
		double y1 = getHeight()/2 - SCAFFOLD_HEIGHT + ROPE_LENGTH;
		GOval head = new GOval(x1,y1,2*HEAD_RADIUS,2*HEAD_RADIUS);
		add(head);
	}
	
	private void paintBody(){
		double x1 = getWidth()/2;
		double y1 = getHeight()/2 - SCAFFOLD_HEIGHT + ROPE_LENGTH + HEAD_RADIUS*2;
		double x2 = x1;
		double y2 = y1 + BODY_LENGTH;
		GLine body = new GLine(x1,y1,x2,y2);
		add(body);
	}
	private void paintLeftHand(){
		double x1 = getWidth()/2 - UPPER_ARM_LENGTH;
		double y1 = getHeight()/2 - SCAFFOLD_HEIGHT + ROPE_LENGTH + HEAD_RADIUS*2
					+ ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH;
		double x2 = x1;
		double y2 = y1 - LOWER_ARM_LENGTH;
		double x3 = x1 + UPPER_ARM_LENGTH;
		double y3 = y2;
		GLine lowerarm = new GLine(x1,y1,x2,y2);
		GLine upperarm = new GLine(x2,y2,x3,y3);
		add(lowerarm);
		add(upperarm);
	}
	private void paintRightHand(){
		double x1 = getWidth()/2 + UPPER_ARM_LENGTH;
		double y1 = getHeight()/2 - SCAFFOLD_HEIGHT + ROPE_LENGTH + HEAD_RADIUS*2
					+ ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH;
		double x2 = x1;
		double y2 = y1 - LOWER_ARM_LENGTH;
		double x3 = x1 - UPPER_ARM_LENGTH;
		double y3 = y2;
		GLine lowerarm = new GLine(x1,y1,x2,y2);
		GLine upperarm = new GLine(x2,y2,x3,y3);
		add(lowerarm);
		add(upperarm);
	}
	private void paintLeftLeg(){
		double x1 = getWidth()/2;
		double y1 = getHeight()/2 - SCAFFOLD_HEIGHT + ROPE_LENGTH + HEAD_RADIUS*2
				    + BODY_LENGTH;
		double x2 = x1 - HIP_WIDTH;
		double y2 = y1;
		double x3 = x2;
		double y3 = y2 + LEG_LENGTH;
		GLine hip = new GLine(x1,y1,x2,y2);
		GLine leg = new GLine(x2,y2,x3,y3);
		add(hip);
		add(leg);
	}
	
	private void paintRightLeg(){
		double x1 = getWidth()/2;
		double y1 = getHeight()/2 - SCAFFOLD_HEIGHT + ROPE_LENGTH + HEAD_RADIUS*2
				    + BODY_LENGTH;
		double x2 = x1 + HIP_WIDTH;
		double y2 = y1;
		double x3 = x2;
		double y3 = y2 + LEG_LENGTH;
		GLine hip = new GLine(x1,y1,x2,y2);
		GLine leg = new GLine(x2,y2,x3,y3);
		add(hip);
		add(leg);
	}
	
	private void paintLeftFoot(){
		double x1 = getWidth()/2 - HIP_WIDTH;
		double y1 = getHeight()/2 - SCAFFOLD_HEIGHT + ROPE_LENGTH + HEAD_RADIUS*2
			    + BODY_LENGTH + LEG_LENGTH;
		double x2 = getWidth()/2 - HIP_WIDTH - FOOT_LENGTH;
		double y2 = y1;
		GLine foot = new GLine(x1,y1,x2,y2);
		add(foot);
	}
	private void paintRightFoot(){
		double x1 = getWidth()/2 + HIP_WIDTH;
		double y1 = getHeight()/2 - SCAFFOLD_HEIGHT + ROPE_LENGTH + HEAD_RADIUS*2
			    + BODY_LENGTH + LEG_LENGTH;
		double x2 = getWidth()/2 + HIP_WIDTH + FOOT_LENGTH;
		double y2 = y1;
		GLine foot = new GLine(x1,y1,x2,y2);
		add(foot);
	}

}

	
