import java.awt.Graphics2D;

public class Bat extends Rectangle{
	public Bat(int x, int y) {
		super(x, y, Const.DEFAULT_BAT_WIDTH, Const.DEFAULT_BAT_HEIGHT);
	}
	public void update(Keyboard keyboard) {
		if(isBatInGameBox()) {// allow movers
			if(keyboard.isKeyDown(Key.Left)) {
				setX(getX() - Const.DEFAULT_BAT_SPEED);
			}
			if(keyboard.isKeyDown(Key.Right)) {
				setX(getX() + Const.DEFAULT_BAT_SPEED);
			}
		}else {
			// the bat is stuck now and can't move
			// put the bat on the road
			if(getX() < Const.CANVAS_MAX_WIDTH/2) { //stuck left side
				setX(-getWidth()/2);
			}
			else {//stuck right side
				setX(Const.CANVAS_MAX_WIDTH - getWidth()/2);
			}
		}
	}
	public boolean isBatInGameBox() {
		return (getX() + getWidth()/2 <= Const.CANVAS_MAX_WIDTH && getX() + getWidth()/2 >= 0);
	}
	public void draw(Graphics2D g) {
		g.setColor(Const.DEFAULT_BAT_COLOR);
		g.fillRect(getX(), getY(), getWidth(), getHeight());
	}
	
}
