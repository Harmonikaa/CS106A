/*
 * My code for Problem 4: MidPointFinder
 */

import stanford.karel.*;

public class MidpointFinder extends SuperKarel {
	
	public void run() {
		BeeperInit();
		while(beepersPresent()) {
			pickBeeper();
			MoveToEnd();		
		}
		putBeeper();
	}
	//fill the row with beeper
	private void BeeperInit() {
		putBeeper();
		while(frontIsClear()) {
			move();
			putBeeper();
		}
		turnAround();
	}
	//Find the opposite corner with beeper, considering corner cases
	private void MoveToEnd() {
		if(frontIsClear()) {//In case that there is only one avenue
			move();
			while(frontIsClear() && beepersPresent()) {
				move();
			}
			if(noBeepersPresent()) {//Get to a place without beeper, move back
				turnAround();
				move();
			}
			else {//Meet a wall, stay here
				turnAround();
			}
		}

	}

}
