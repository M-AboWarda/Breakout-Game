import java.util.ArrayList;
import java.awt.Graphics2D;
public class Runner {
	private Game game;
	
	private Bat bat;
	private Ball ball;
	private ArrayList<Brick> bricks;
	private Score score;
	private Counter countDown;
	
	public Runner(Game game){
		this.game = game;
		
		bat = new Bat(Const.CANVAS_MAX_WIDTH/2 - Const.DEFAULT_BAT_WIDTH/2, Const.CANVAS_MAX_HEIGHT - Const.DEFAULT_BAT_HEIGHT - 5);
		ball = new Ball(bat.getX() + bat.getWidth()/2 - Const.DEFAULT_BALL_RADIUS, bat.getY() - Const.DEFAULT_BALL_RADIUS * 2);
		bricks = new ArrayList<Brick>();
		score = new Score();
		countDown = new Counter();
		countDown.set(Const.GAME_MAX_TIME);
		
		for(int j = 0; j < 8; j++) {// 8 rows 
			for(int i = 0; i < 10; i++) {// 10 columns
				if(j == 0 || j == 1 || j == 2)continue;//Create a gap at the top
				int xOffset = 5;  // x and y offset is used to create
				int yOffset = 10; // the gaps between the bricks
				int x = ( i * (Const.CANVAS_MAX_WIDTH/10)) + xOffset;
				int y = ( j * (Const.CANVAS_MAX_HEIGHT/2) / 8 ) + yOffset;
				int width  =  (Const.CANVAS_MAX_WIDTH/10) - xOffset *2;
				int height = ((Const.CANVAS_MAX_HEIGHT/2) / 8 ) - yOffset;
				bricks.add(new Brick(x, y, width, height));
			}
		}
		
		//test brick
		//bricks.add(new Brick(Const.CANVAS_MAX_WIDTH/2 -75, Const.CANVAS_MAX_HEIGHT - 200,150, 30));
		//bricks.remove(0);
		//bricks.remove(10);
		//bricks.remove(20);
		
	}
	public void update(Keyboard keyboard) {
		bat.update(keyboard);
		ball.update(keyboard);
		if(ball.getLives() == 0) {
			game.setState(GameState.GameOver);
			countDown.set(0);// no extra points of you loss
			countDown.pause();
		}
		if(countDown.isTimeout()) {
			game.setState(GameState.GameOver);
			countDown.pause();
		}
		if(ball.isColliding(bat)) {
			//ball.bounce(false);// the bounce is implemented in the isCollionding method
		}
		for(int i = 0; i < bricks.size(); i++) {
			bricks.get(i).update(keyboard);
			if(ball.isColliding(bricks.get(i))) {
				score.increment(bricks.get(i).getLives());
				bricks.get(i).hit();
				if(bricks.get(i).getLives() == 0) {
					bricks.remove(i--);
				}
			}
		}
		if(bricks.size() == 0) {
			game.setState(GameState.YouWin);
			countDown.pause();
		}
		countDown.update();
	}
	
	public void draw(Graphics2D graphics) {
		bat.draw(graphics);
		ball.draw(graphics);
		for(int i = 0; i < bricks.size(); i++) {
			bricks.get(i).draw(graphics);
		}
		countDown.draw(graphics);
		score.draw(graphics);
	}
	public int getGameScore() {
		return score.getScore() + 25 * countDown.getSeconds() + 300 * ball.getLives();
	}
	
}