import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
public class Ball extends Circle{
	private int lives;
	private double speed;
	private Vec dir;
		
	public Ball(int x, int y) {
		super(x, y, Const.DEFAULT_BALL_RADIUS);
		
		speed = Const.DEFAULT_BALL_SPEED;
		
		dir = new Vec(Const.DEFAULT_BALL_STARTING_ANGLE);
		dir.setMag(speed);
		
		lives = Const.DEFAULT_BALL_LIVES;
		
	}
	public int getLives() {
		return this.lives;
	}
	public void update(Keyboard keyboard) {
		move();
		bounceOnEdges();

		incrementSpeed();
		
		//death
		if(getY() > Const.CANVAS_MAX_HEIGHT) {
			restart();
		}
	}
	private void move() {
		dir.setMag(speed);
		posVec().add(dir);
	}
	private void applyForce(Vec force) {
		dir.add(force);
	}
	private void incrementSpeed() {
		speed = speed + ( ( speed  * Const.DEFAULT_BALL_ACCELERATION_FACTOR ) - speed)/Const.GAME_FPS;
		dir.setMag(speed);
	}
	private void bounceOnEdges() {
		if(getY() < 0) {
			setY(0);
			bounce(false);
		}
		if(getX() < 0) {
			setX(0);
			bounce(true);
		}
		if(getX() + getWidth() > Const.CANVAS_MAX_WIDTH) {
			setX(Const.CANVAS_MAX_WIDTH - getWidth());
			bounce(true);
		}
	}
	private void bounce(boolean isXBounce) {
		if(isXBounce) {// bounce on horizontel hit 
			//reverse x dirction
			dir.setX(dir.getX() * -1);
		}else {// bonce on vertical hit
			//reverse y dirction
			dir.setY(dir.getY() * -1);
		}
	}
	public boolean isColliding(Rectangle box) {
		boolean isInXRange = (getMidX() > box.getX() && getMidX() < box.getX() + box.getWidth()); 
		boolean isInYRange = (getMidY() > box.getY() && getMidY() < box.getY() + box.getHeight());
		boolean isCollidingTheBallBox = ( getMidX() + getR() > box.getX() && getMidX() - getR() < box.getX() + box.getWidth() ) && (getMidY() + getR() > box.getY() && getMidY() - getR() < box.getY() + box.getHeight());
		if(isInXRange && isCollidingTheBallBox) {
			//Vertical bounce illusion//
				// if the ball is above the box
			if(getMidY() < box.getY() + box.getHeight()/2) {
				applyForce(new Vec(0, - Math.abs( dir.getY() *2)) );
			}else {
				applyForce(new Vec(0,   Math.abs( dir.getY() *2)) );
			}
			return true;
		}
		if(isInYRange && isCollidingTheBallBox) {			
			//Horizontal bounce illusion//
			 	//if the ball is to the box's left or in the left half of the box
			if(getMidX() < box.getX() + box.getWidth()/2) {	
				//apply horizontal force to its direction against its horizontal velocity (to the right)
				applyForce(new Vec( -Math.abs(dir.getX() *2), 0.35));// 0.35 to avoid getting stuck when the bat is trying to catch the ball at the last moment and it hits the ball with the left/right side and the ball is consistently applying this force to its direction until the ball gets in a horizontal state the 0.35 force the ball fall down
			}else {// if the ball is to the box's right or in the right half of the box
				//apply horizontal force to its direction against its horizontal velocity (to the left)
				applyForce(new Vec( Math.abs(dir.getX() *2), 0.35));
			}
			return true;
		}
		// when the ball box is collied with the box corner
		if(!isInXRange && !isInYRange && isCollidingTheBallBox) {
			//their is four corners, the quarter is returned the box number which the ball is inside 
				//		__1__|_______|__2__
				//		_____|__BOX__|_____
				//		  3  |       |  4  
			int quarter = calcQuarter(box);
			double dx = 0;
			double dy = 0;
			if(quarter == 1) {
				dx = box.getX() - getMidX();
				dy = box.getY() - getMidY();
			}else if(quarter == 2) {
				dx = getMidX() - (box.getX() + box.getWidth());
				dy = box.getY() - getMidY();
			}else if(quarter == 3) {
				dx = box.getX() - getMidX();
				dy = getMidY() - (box.getY() + box.getHeight());
			}else if(quarter == 4) {
				dx = getMidX() - (box.getX() + box.getWidth());
				dy = getMidY() - (box.getY() + box.getHeight());
			}
			//check for real collision between the ball and the box
			if( dx * dx + dy * dy < getR() * getR()) {//فيثاغورث is used to check real collision
				//System.out.println("real corner collision!");// 
				double verticalDeltaY = Math.sqrt(getR() * getR() - dx * dx);//same as getR() * Math.cos(Math.asin(dx /getR())) but simplified
				double verticalDeltaX = dx;
				//System.out.println( "vdx: " + verticalDeltaX + " vdy: " + verticalDeltaY);
				double horizontalDeltaY = dy;
				double horizontalDeltaX = Math.sqrt(getR() * getR() - dy * dy);
				//System.out.println("hdx: " + horizontallDeltaX + " hdy: " + horizontallDeltaY);
				double[] verticalPoint = new double[2];//{getMidX() + verticalDeltaX, getMidY() + verticalDeltaY};
				double[] horizontalPoint = new double[2];// = {getMidX() + horizontallDeltaX, getMidY() + horizontallDeltaY};
				if(quarter == 1) {
					verticalPoint[0] = getMidX() + verticalDeltaX;
					verticalPoint[1] = getMidY() + verticalDeltaY;
					horizontalPoint[0] = getMidX() + horizontalDeltaX;
					horizontalPoint[1] = getMidY() + horizontalDeltaY;
				}else if(quarter == 2) {
					verticalPoint[0] = getMidX() - verticalDeltaX;
					verticalPoint[1] = getMidY() + verticalDeltaY;
					horizontalPoint[0] = getMidX() - horizontalDeltaX;
					horizontalPoint[1] = getMidY() + horizontalDeltaY;
				}else if(quarter == 3) {
					verticalPoint[0] = getMidX() + verticalDeltaX;
					verticalPoint[1] = getMidY() - verticalDeltaY;
					horizontalPoint[0] = getMidX() + horizontalDeltaX;
					horizontalPoint[1] = getMidY() - horizontalDeltaY;
				}else{//if(quarter == 4)
					verticalPoint[0] = getMidX() - verticalDeltaX;
					verticalPoint[1] = getMidY() - verticalDeltaY;
					horizontalPoint[0] = getMidX() - horizontalDeltaX;
					horizontalPoint[1] = getMidY() - horizontalDeltaY;
				}
				//System.out.println("( " + verticalPoint[0] + " , " + verticalPoint[1] + " )" );
				//System.out.println("( " + horizontalPoint[0] + " , " + horizontalPoint[1] + " )" );
				double forceVectorStartX = verticalPoint[0] + (horizontalPoint[0] - verticalPoint[0])/2;
				double forceVectorStartY = horizontalPoint[1] + (verticalPoint[1] - horizontalPoint[1])/2;
				double forceVectorEndX = getMidX();//the vector will always point to the middle of the ball
				double forceVectorEndY = getMidY();
				Vec force = new Vec(new Vec(forceVectorStartX, forceVectorStartY), new Vec(forceVectorEndX, forceVectorEndY));
				force.setMag( 1 / (force.getMag() / getR() ) * speed *1.5);//how effective the force should be
				//force.setY(force.getY() * -1);
				applyForce(force);
				return true;
			}
		}
		return false;
	}

	// calcQuarter returns 1,2,3 or 4 if the ball is in one of the boxes the origin is (box.x, box.y) 
		//		_1_|_2_
		// 		 3 | 4 
	private int calcQuarter(Rectangle box) {
		boolean isToLeft = (getMidX() < box.getX());
		boolean isToTop = (getMidY() < box.getY());
		if(isToLeft && isToTop) {
			return 1;
		}else if(!isToLeft && isToTop) {
			return 2;
		}else if(isToLeft && !isToTop) {
			return 3;
		}else {//(!isToLeft && !isToTop)
			return 4;
		}
	}
	private void restart() {
		lives--;
		//put the ball in the middle bottom of the screen
		setY(Const.CANVAS_MAX_HEIGHT -Const.DEFAULT_BAT_HEIGHT - getR() - 20);//20 pixels y-offset
		setX(Const.CANVAS_MAX_WIDTH/2);
		dir.setY(-dir.getY());
	}
	public void draw(Graphics2D g) {
		g.setColor(Const.DEFAULT_BALL_COLOR);
		super.draw(g);
		
		//show lives //should be improved later
		g.setFont(new Font("mono space",1,20));
		g.drawString("Lives", Const.CANVAS_MAX_WIDTH - 95, 30);
		for(int i = 0; i < this.lives; i++) {
			int x = Const.CANVAS_MAX_WIDTH - 47;// 47 xOffset
			int y = 38;// 38 yOffset
			g.setStroke(new BasicStroke(1.8f));
			g.drawOval(x - 20 * i, y, 14, 14);
		}
	}
}