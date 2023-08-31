import java.awt.Color;
// all game constants is represented in this file
public interface Const {
	int CANVAS_MAX_WIDTH = 800;
	int CANVAS_MAX_HEIGHT = 600;
	int GAME_FPS = 160;
	int GAME_MAX_TIME = 240;
	
	int DEFAULT_BAT_WIDTH = 150;
	int DEFAULT_BAT_HEIGHT = 15;
	int DEFAULT_BAT_SPEED = 4;
	Color DEFAULT_BAT_COLOR = new Color(100, 100, 100);
	int DEFAULT_BALL_LIVES = 3;
	
	int DEFAULT_BALL_RADIUS = 15;
	double DEFAULT_BALL_SPEED = 3.7;
	double DEFAULT_BALL_ACCELERATION_FACTOR = 1.0029;
	double DEFAULT_BALL_STARTING_ANGLE = Math.PI/4;
	Color DEFAULT_BALL_COLOR = new Color(255,255,255);
	
	int HIGH_SCORE_LIST_LENGTH = 10;
	int LATEST_RUNS_LIST_LENGTH = 3;
	
}
