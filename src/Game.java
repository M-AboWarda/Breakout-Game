import java.awt.Graphics2D;

import javax.swing.JOptionPane;

public class Game {	
	private InfoScreen endScreen;//this screen is visible for only 3 seconds
	
	//Runner is containing everything for the game while the game is running
	private Runner gameRunner;
	
	//Parent reference 
	// used to check GameState and set game 
	private GameBoard board;

	public Game(GameBoard board) {
		this.board = board;
		gameRunner = new Runner(this);
	}


	public void update(Keyboard keyboard) {
		
		if(board.getState() == GameState.Running) {			
			gameRunner.update(keyboard);
		}
		
		//end info screen winning/losing 
		// creating InfoScreen
		if(endScreen == null) {
			if(board.getState() == GameState.YouWin){
				endScreen = new InfoScreen("You Win!", "Score: " + gameRunner.getGameScore());
				
			}else if (board.getState() == GameState.GameOver) {
				endScreen = new InfoScreen("Game Over!", "Score: " + gameRunner.getGameScore());
			}
		}
		//updating InfoScreen
		if(board.getState() == GameState.YouWin || board.getState() == GameState.GameOver) {
			endScreen.update();
			if(endScreen.isDoneShowingInfo()) {
				board.setState(GameState.Stop);
			}
		}
		
	}

	public void draw(Graphics2D graphics) {
		if(board.getState() == GameState.Running) {			
			gameRunner.draw(graphics);
		}
		if(endScreen != null) {			
			if(board.getState() == GameState.YouWin || board.getState() == GameState.GameOver) {
				endScreen.draw(graphics);
			}
		}
		
	}
	//create score
	public ScoreBlock getScoreBlock() {
		String name = "";
		//the name should be 3 Characters long
		while(name.length() != 3) {			
			name = JOptionPane.showInputDialog("Sign your Score! with 3 Characters! \n Your Score is " + gameRunner.getGameScore());
			if(name == null) {
				name = "   ";
			}
		}
		return new ScoreBlock(name, gameRunner.getGameScore());
	}
	public void setState(GameState state) {
		board.setState(state);
	}
	
	public void reset() {
		
		this.gameRunner = new Runner(this);
		endScreen = null;
		board.setState(GameState.Running);
	}
}
