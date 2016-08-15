/*
 * File: Hailstone.java
 * Name:
 * Section Leader:
 * --------------------
 * My code for Problem3
 */

import acm.program.*;

public class Hailstone extends ConsoleProgram {
	
	public void run() {
		setFont("Consolas-*-24");
		
		int hs_number = readInt("Enter a number: ");
		int hs_cnt = 0;
		
		while(hs_number != 1) {
			if(hs_number % 2 == 0) {
				println(hs_number + " is even, so I take half: " + hs_number/2);
				hs_number /= 2;
			} else {
				println(hs_number + " is odd, so I make 3n + 1: " + (3*hs_number+1));
				hs_number = hs_number*3 + 1;
			}
			hs_cnt ++;
		}
		println("The process took " + hs_cnt + " steps to reach 1");
	}
}