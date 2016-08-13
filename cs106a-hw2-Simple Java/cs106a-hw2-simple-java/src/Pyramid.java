/*
 * File: Pyramid.java
 * Name:
 * Section Leader:
 * ------------------
 * My code for Problem5
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Pyramid extends GraphicsProgram {

/** Width of each brick in pixels */
	private static final int BRICK_WIDTH = 30;

/** Height of each brick in pixels */
	private static final int BRICK_HEIGHT = 12;

/** Number of bricks in the base of the pyramid */
	private static final int BRICKS_IN_BASE = 14;

	
	public void run() {	
		int bricks_number = BRICKS_IN_BASE;
		int bricks_level = 1;
		
		while(bricks_number > 0) {
			drawBricksByLevel(bricks_number, bricks_level);
			bricks_number--;
			bricks_level++;
		}
	}
	
	private void drawBricksByLevel(int bricks_number, int bricks_level) {
		double x_start = (getWidth() - bricks_number * BRICK_WIDTH)/2;
		double y_start = getHeight() - bricks_level * BRICK_HEIGHT;
		for(int i=0;i<bricks_number;++i) {
			drawRect(x_start+i*BRICK_WIDTH, y_start, BRICK_WIDTH, BRICK_HEIGHT);
		}
	}
	
	private void drawRect(double x, double y, double width, double height) {
		GRect rect = new GRect(x, y, width, height);
		add(rect);
	}
	
}