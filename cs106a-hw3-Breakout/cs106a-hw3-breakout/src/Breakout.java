// My code for HW3

import acm.graphics.*;     // GOval, GRect, etc.
import acm.program.*;      // GraphicsProgram
import acm.util.*;         // RandomGenerator
import java.applet.*;      // AudioClip
import java.awt.*;         // Color
import java.awt.event.*;   // MouseEvent

public class Breakout extends BreakoutProgram {
	
	private GRect paddle;
	private GLabel scorelabel;
	private GOval ball;
	private double dx,dy;
	private int turn = NTURNS;
	private int score = 0;
	
	public void run() {
		// Set the window to a nice size for our game
		setSize(APPLICATION_WIDTH+20, APPLICATION_HEIGHT);
		addMouseListeners();
		setUpTheBricks();
		createThePaddle();
		scorelabel = createTheScoreLabel();
		ball = createTheBouncingBall();
		while(true) {
			moveball();
			scorelabel.setLabel("Score: "+score+", Turns: "+turn);
			if(turn == 0){
				gameOver();
				break;
			}
			if(score == NBRICKS_PER_ROW * NBRICK_ROWS){
				gameWin();
				break;
			}
		}
		
	}
	private void gameOver() {
		remove(ball);
		remove(paddle);
		GLabel endlabel = new GLabel("Game Over!");
		endlabel.setFont(SCREEN_FONT);
		endlabel.setLocation((getWidth() - endlabel.getWidth())/2, (getHeight() - endlabel.getHeight())/2);
		add(endlabel);
	}
	private void gameWin() {
		remove(ball);
		remove(paddle);
		GLabel endlabel = new GLabel("You Win!");
		endlabel.setFont(SCREEN_FONT);
		endlabel.setLocation((getWidth() - endlabel.getWidth())/2, (getHeight() - endlabel.getHeight())/2);
		add(endlabel);
	}
	private void setUpTheBricks() {
		for(int i=0; i<NBRICK_ROWS; ++i) {
			if((i/2)%5 == 0)
				setUpBricksByLine(i,Color.RED);
			else if((i/2)%5 == 1)
				setUpBricksByLine(i,Color.ORANGE);
			else if((i/2)%5 == 2)
				setUpBricksByLine(i,Color.YELLOW);
			else if((i/2)%5 == 3)
				setUpBricksByLine(i,Color.GREEN);
			else if((i/2)%5 == 4)
				setUpBricksByLine(i,Color.CYAN);
		}
	}
	
	private void setUpBricksByLine(int linenum, Color color) {
		double x_start = (BOARD_WIDTH - (NBRICKS_PER_ROW - 1.0) * BRICK_SEP
				          - NBRICKS_PER_ROW * BRICK_WIDTH) / 2;
		double y_start = BRICK_Y_OFFSET + BRICK_HEIGHT * linenum + BRICK_SEP * linenum;
		
		for(int i=0;i<NBRICKS_PER_ROW;++i) {
			double x = x_start + BRICK_WIDTH * i + BRICK_SEP * i;
			createBrick(x, y_start, color);
		}
	}
	
	private void createBrick(double x, double y, Color color) {
		GRect rect = new GRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);
		rect.setColor(color);
		rect.setFilled(true);
		add(rect);
	}
	
	private void createThePaddle() {
		double x = (getWidth() - PADDLE_WIDTH)/2;
		double y = getHeight() - PADDLE_Y_OFFSET - PADDLE_HEIGHT;
		
		paddle = new GRect(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle.setColor(Color.BLACK);
		paddle.setFilled(true);
		add(paddle);
	}
	
	public void mouseMoved(MouseEvent e) {
		double x = e.getX() - BRICK_WIDTH/2;
		if(x<0)
			x = 0;
		else if((x + BRICK_WIDTH) > BOARD_WIDTH) 
			x = BOARD_WIDTH - BRICK_WIDTH;
		double y = paddle.getY();
		
		paddle.setLocation(x, y);
	}
	
	private GLabel createTheScoreLabel() {
		GLabel scorelabel = new GLabel("Score: "+score+", Turns: "+turn);
		scorelabel.setFont(SCREEN_FONT);
		scorelabel.setLocation(0, scorelabel.getHeight());
		add(scorelabel);
		return scorelabel;
	}
	
	private GOval createTheBouncingBall() {
		RandomGenerator rgen = RandomGenerator.getInstance();
		double x = getWidth()/2 - BALL_RADIUS;
		double y = getHeight()/2 - BALL_RADIUS;
		boolean dir = rgen.nextBoolean();
		dy = 3;
		dx = rgen.nextDouble(1.0, 3.0);
		if(!dir)
			dx = (-1.0) * dx;
		
		GOval ball = new GOval(x,y,BALL_RADIUS,BALL_RADIUS);
		ball.setFillColor(Color.BLACK);
		ball.setFilled(true);
		add(ball);
		return ball;
	}
	
	private void moveball() {
		ball.move(dx, dy);
		checkBounceWithBricks();
		checkBounceWithWall();	
		pause(DELAY);
	}
	
	private void checkBounceWithWall() {
		if(ball.getX() + ball.getWidth() >= getWidth()) {
			ball.setX(getWidth() - ball.getWidth());
			dx = (-1.0) * dx;
		}
		if(ball.getX() <= 0) {
			ball.setX(0);
			dx = (-1.0) * dx;
		}
		if(ball.getY() + ball.getHeight() >= getHeight()) {
			//ball.setY(getHeight() - ball.getHeight());
			//dy = (-1.0) * dy;
			--turn;
			remove(ball);
			ball = createTheBouncingBall();
		}
		if(ball.getY() <= 0) {
			ball.setY(0);
			dy = (-1.0) * dy;
		}
	}
	
	private void checkBounceWithBricks() {
		double x1 = ball.getX(), y1 = ball.getY();
		double x2 = ball.getX() + ball.getWidth(), y2 = ball.getY();
		double x3 = ball.getX(), y3 = ball.getY() + ball.getHeight();
		double x4 = ball.getX() + ball.getWidth(), y4 = ball.getY() + ball.getHeight();
		GObject collider = getElementAt(x1, y1, x2, y2, x3, y3, x4, y4);
		if(collider == null)
			return;
		if(collider == paddle){
			dy = (-1.0) * Math.abs(dy);
		} else if(collider != scorelabel){
			dy = (-1.0) * dy;
			remove(collider);
			++score;
		}
			
	}
}