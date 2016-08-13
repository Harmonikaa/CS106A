/*
 * File: ExamScores.java
 * Name:
 * Section Leader:
 * --------------------
 * My code for Problem2
 */

import acm.program.*;

public class ExamScores extends ConsoleProgram { 
	
	private static final int SENTINEL_VALUE = -1;
	
	public void run() {
		setFont("Consolas-*-24");
		
		int score_buffer;
		double score_sum = 0;
		int score_cnt = 0;
		int score_highest = -1;
		int score_lowest = 999;
		int fail_cnt = 0;
		double score_average = 0;
		
		println("CS 106A Exam Master");
		while(true) {
			score_buffer = readInt("Next exam score (or " + SENTINEL_VALUE + " to quit)? ");
			if(score_buffer == SENTINEL_VALUE)
				break;
			score_sum += score_buffer;
			score_cnt++;
			if(score_highest < score_buffer)
				score_highest = score_buffer;
			if(score_lowest > score_buffer)
				score_lowest = score_buffer;
			if(score_buffer < 60)
				fail_cnt++;
		}
		if(score_cnt == 0)
			println("No scores were entered.");
		else {
			score_average = score_sum / score_cnt;
			println("Highest score = " + score_highest);
			println("Lowest score = " + score_lowest);
			println("Average = " + score_average);
			println(fail_cnt + " student(s) failed the exam.");
		}
	}
}