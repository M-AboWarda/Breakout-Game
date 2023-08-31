import java.awt.Color;
import java.awt.Graphics2D;

public abstract class Rectangle extends Sprite{
	public Rectangle(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	
	public void draw(Graphics2D g) {
		g.fillRect(getX(), getY(), getWidth(), getHeight());
	}
}
