/*
 * File: Target.java
 * Name:
 * Section Leader:
 * -----------------
 * My code for Problem4 
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {	
	
	private static final double OUTER_RADIUS = 72;
	
	public void run() {
		drawCenterCircle(OUTER_RADIUS, Color.ORANGE);
		drawCenterCircle(OUTER_RADIUS * 0.65, Color.WHITE);
		drawCenterCircle(OUTER_RADIUS * 0.3, Color.ORANGE);
		
	}
	
	private void drawCenterCircle(double radius, Color color) {
		double x = getWidth()/2 - radius;
		double y = getHeight()/2 - radius;
		GOval circle = new GOval(x, y, 2*radius, 2*radius);
		circle.setColor(color);
		circle.setFillColor(color);
		circle.setFilled(true);
		add(circle);
	}
	
}