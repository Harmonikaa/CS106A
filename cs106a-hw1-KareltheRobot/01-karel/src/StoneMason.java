/*
 * My code for Problem 2: StoneMason
 */

import stanford.karel.*;

public class StoneMason extends SuperKarel {
	
	public void run() {
		FixCurrentColumn();
		while(frontIsClear()) {
			MoveToNextColumn();
			FixCurrentColumn();
		}
	}
	
	//Go up and fix current column, move down to the first street, facing east
	private void FixCurrentColumn() {
		turnLeft();
		FixCurrentLocation();
		while(frontIsClear()) {
			move();
			FixCurrentLocation();
		}
		turnAround();
		while(frontIsClear()) {
			move();
		}
		turnLeft();
	}
	//If beeper present, do nothing; else put a beeper here
	private void FixCurrentLocation() {
		if (noBeepersPresent())
			putBeeper();
	}
	//Go to next column
	private void MoveToNextColumn() {
		move();
		move();
		move();
		move();
	}

}
