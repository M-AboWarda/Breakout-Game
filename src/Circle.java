import java.awt.Graphics2D;
public abstract class Circle extends Sprite{
	private double r; 
	public Circle(int x, int y, double r) {
		super(x, y,(int) r * 2, (int)r * 2);
		this.r = r;
	}
	public double getR() {
		return this.r;
	}
	public double getMidX() {
		return getX() + this.r;
	}
	public double getMidY() {
		return getY() + this.r;
	}
	public void setR(double r) {
		this.setWidth((int) r * 2);
		this.setHeight((int) r * 2);
	}
	public void setR(int r) {
		this.setWidth(r * 2);
		this.setHeight(r * 2);
	}
	public void draw(Graphics2D g) {
		g.fillOval(getX(), getY(), getWidth(), getHeight());
	}
}
