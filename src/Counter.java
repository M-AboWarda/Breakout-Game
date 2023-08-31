import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
public class Counter {
	private int seconds;
	private int frame;
	private boolean isPaused;
	public Counter() {
		isPaused = false;
	}
	public void set(int seconds) {
		this.seconds = seconds;
	}
	public int getSeconds() {
		return this.seconds;
	}
	public void pause() {
		isPaused = true;
	}
	public void update() {
		if(++frame % Const.GAME_FPS == 0) {
			if(!isPaused) {				
				seconds--;
			}
		}
	}
	public boolean isTimeout() {
		return (seconds == 0);
	}
	public void draw(Graphics2D g) {
		g.setColor(new Color(200,200,200));
		g.setFont(new Font("Mono Space", 1, 30));
		g.drawString(String.valueOf(seconds), 33, 37);
	}
}
