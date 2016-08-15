/*
 * My code for Problem 3: Checkerboard
 */

import stanford.karel.*;

public class Checkerboard extends SuperKarel {
	
	public void run() {
		WorkOnBeeperRow();
		while(frontIsClear()) {
			WorkOnNextRow();
		}
	}
	//Start by putting a beeper, end facing north
	private void WorkOnBeeperRow() {
		putBeeper();
		while(frontIsClear()) {
			if (noBeepersPresent()) {
				move();
				putBeeper();
			}
			else {
				move();
			}
		}
		if(facingEast())
			turnLeft();
		else
			turnRight();
		
	}
	//Start by not putting a beeper, end facing north
	private void WorkOnEmptyRow() {
		while(frontIsClear()) {
			if (noBeepersPresent()) {
				move();
				putBeeper();
			}
			else {
				move();
			}
		}
		if(facingEast())
			turnLeft();
		else
			turnRight();
	}
	//Choose to work on beeperrow or emptyrow by the state of the ending point of the current row
	private void WorkOnNextRow() {
		if(noBeepersPresent()) {
			move();
			AdjustDirection();
			WorkOnBeeperRow();
		} 
		else {
			move();
			AdjustDirection();
			WorkOnEmptyRow();
		}
	}
	//Change to a direction with no block
	private void AdjustDirection() {
		if(leftIsBlocked())
			turnRight();
		else
			turnLeft();
	}

}
