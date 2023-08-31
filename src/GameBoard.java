import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComponent;

public class GameBoard extends JComponent implements ActionListener, Runnable{
	private final int FPS = Const.GAME_FPS;
	
	private GameState state;
	
	//game update and draw is running on this thread;
	private Thread updateThread;

	private Game game;
	private Keyboard keyboard;	
	
	private Program program;
	public GameBoard(Program program) {
		this.program = program;
		state = GameState.Running;
		
		keyboard = new Keyboard();
		game = new Game(this);
		
	}
	
	public GameState getState() {
		return state;
	}
	
	public void setState(GameState state) {
		this.state = state;
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(Const.CANVAS_MAX_WIDTH, Const.CANVAS_MAX_HEIGHT);
	}

	@Override
	protected void paintComponent(Graphics arg0) {
		super.paintComponent(arg0);
		Graphics2D graphics = (Graphics2D)arg0;
		graphics.setColor(new Color(16, 8, 17));// dark background screen
		graphics.fillRect(0, 0, getWidth(), getHeight());
		
		game.draw(graphics);
	}
	
	@Override
	protected void processKeyEvent(KeyEvent e) {
		super.processKeyEvent(e);
		if (e.getID() == KeyEvent.KEY_PRESSED)
			keyboard.processKeyEvent(e.getKeyCode(), true);
		else if (e.getID() == KeyEvent.KEY_RELEASED)
			keyboard.processKeyEvent(e.getKeyCode(), false);
	}

	public void run() {// game logic is running on a parallel thread
		
		//pause for 800 milliseconds before starting the game
		try {Thread.sleep(800);}catch(InterruptedException e) {e.printStackTrace();}
		this.start();
	}
	
	private void start() {
		
		while(state != GameState.Stop) {
			game.update(keyboard);
			try {
				Thread.sleep(1000 / FPS); //Throttle thread
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.repaint();
		}
		
		// game is finished
		triggerFinishingActionEvent();
		game.reset();
		
		
	}
	
	private void triggerFinishingActionEvent() {
		//trigger an ActionEvent to return to the menu and add the score to the ScoreFrame
		JButton trigger = new JButton("Silance Is Gold!");// invisible button
		trigger.addActionListener(program);	
		trigger.addActionListener(program.getScoreFrame());
		trigger.doClick();
	}
	
	public ScoreBlock getLatestGameScoreBlock() {
		return game.getScoreBlock();
	}
	
	// GameBoard is only listening to playBtn in the mainMenu
	//when the play button is pressed run the game logic on a parallel thread
	public void actionPerformed(ActionEvent e) {
		this.updateThread = new Thread (this);
		this.updateThread.start();
	}
}