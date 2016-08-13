/*
 * File: QuadraticEquation.java
 * Name:
 * Section Leader:
 * -----------------------------
 * My code for Problem1
 */

import acm.program.*;

public class QuadraticEquation extends ConsoleProgram {
	
	public void run() {
		setFont("Consolas-*-24");
		println("CS 106A Quadratic Solver!");
		int a = readInt("Enter a: ");
		int b = readInt("Enter b: ");
		int c = readInt("Enter c: ");
		
		int discriminant = b*b - 4*a*c;
		
		if(discriminant > 0) {
			double delta = Math.sqrt(discriminant);
			println(delta);
			double x1 = ((-1.0)*b + delta)/(2*a);
			double x2 = ((-1.0)*b - delta)/(2*a);
			println("Two roots: " + x1 + " and " +x2);
		} else if(discriminant == 0) {
			double x1 = ((-1.0)*b)/(2*a);
			println("One root: " + x1);
		} else {
			println("No real roots");
		}
	}
	
}