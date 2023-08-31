import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
public class Score{
	private int score;
	public void increment(int brickLevel) {
		score += 100 * brickLevel;
	}
	public int getScore() {
		return this.score;
	}
	public void draw(Graphics2D g) {
		g.setColor(Color.white);
		g.setFont(new Font("Mono Space", 1, 39));
		g.drawString(String.valueOf(score), Const.CANVAS_MAX_WIDTH/2 - 30, 45);
	}
}
