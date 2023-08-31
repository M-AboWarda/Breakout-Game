import java.awt.Graphics2D;

public abstract class Sprite {
	private int width, height;
	private Vec pos;
	public int getX() { return (int)this.pos.getX(); }
	public int getY() { return (int)this.pos.getY(); }
	public Vec posVec() { return this.pos; }
	public int getWidth() { return this.width; }
	public int getHeight() { return this.height; }
	public void setX(int x) { this.pos.setX((double)x); };
	public void setY(int y) { this.pos.setY((double)y); };
	public void setX(double x) { this.pos.setX(x); };
	public void setY(double y) { this.pos.setY(y); };
	public void setWidth(int width) { this.width = width; };
	public void setHeight(int height) { this.height = height; };
	public Sprite(int x, int y, int width, int height) {
		this.pos = new Vec(x, y);
		this.width = width;
		this.height = height;
	}
	public abstract void update(Keyboard keyboard);
	public abstract void draw(Graphics2D graphics);
}