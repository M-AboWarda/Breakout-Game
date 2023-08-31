import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
public class InfoScreen {
	private String title;
	private String subTitle;
	private Counter countDown;
	public InfoScreen(String title, String subTitle) {
		this(title, subTitle, 2);
	}
	public InfoScreen(String title, String subTitle, int period) {
		this.title = title;
		this.subTitle = subTitle;
		countDown = new Counter();
		countDown.set(period);//info screen is shown for 5 seconds
	}
	
	public void update() {
		countDown.update();
	}
	public void draw(Graphics2D g) {
		g.setColor(new Color(255,255,255));
		g.setFont(new Font("impact",2, 60));
		g.drawString(title, Const.CANVAS_MAX_WIDTH/2 - 125, Const.CANVAS_MAX_HEIGHT/2 + 30);
		g.setFont(new Font("monospace",0, 30));
		g.drawString(subTitle, Const.CANVAS_MAX_WIDTH/2 - 120, Const.CANVAS_MAX_HEIGHT/2 + 75);
	}
	public boolean isDoneShowingInfo() {
		return countDown.isTimeout();
	}
	
}
