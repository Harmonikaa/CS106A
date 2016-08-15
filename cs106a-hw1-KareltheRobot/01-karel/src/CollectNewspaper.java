/*
 * My code for Problem 1: CollectNewspaper
 * Karel go out of room, pick up newspaper and then return to the starting point
 */

import stanford.karel.*;

public class CollectNewspaper extends SuperKarel {
	
	public void run() {
		MoveToTheNewspaper();
		pickBeeper();
		ReturnToStartingPoint();
	}
	
	//Go from starting point to where the newspaper is placed
	private void MoveToTheNewspaper() {
		move();
		move();
		turnRight();
		move();
		turnLeft();
		move();
	}
	
	//Move back to starting point, facing east
	private void ReturnToStartingPoint() {
		turnAround();
		move();
		move();
		move();
		turnRight();
		move();
		turnRight();
	}
}
