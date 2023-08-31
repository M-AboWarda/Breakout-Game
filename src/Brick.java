import java.awt.Color;
import java.awt.Graphics2D;
public class Brick extends Rectangle{
	private int lives;
	private Color color;
	public Brick(int x, int y, int width, int height) {
		super(x,y,width, height);
		lives = 2;
		color = Color.blue;
	}
	public int getLives() {
		return this.lives;
	}
	public void update(Keyboard keyboard) {
		
	}
	public void hit() {
		lives--;
		this.color = Color.orange;
	}
	@Override
	public void draw(Graphics2D g) {
		g.setColor(this.color);
		super.draw(g);
	}
}
