//import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class Program extends JFrame implements ActionListener{
	private Menu mainMenu;
	private GameBoard board;
	private ScoreFrame scoreFrame;
	public Program() {
		board = new GameBoard(this);
		mainMenu = new Menu(this);
		board.setFocusable(true);
		add(mainMenu);
		setResizable(true);
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		scoreFrame = new ScoreFrame(board);// board is needed to get the ScoreBlocks to be added to the score lists and panels
		
	}
	public GameBoard getGameBoard() {
		return this.board;
	}
	public ScoreFrame getScoreFrame() {
		return this.scoreFrame;
	}
	
	
	@Override
	protected void processKeyEvent(KeyEvent e) {
		super.processKeyEvent(e);
		//mainMenu.processKeyEvent(e);
		board.processKeyEvent(e);
	}

	public void actionPerformed(ActionEvent e) {
		if(((JButton)e.getSource()).getText() == "Play") {
			//remove the menu from the frame and add the gameBoard
			this.add(board);
			this.remove(mainMenu);
			//update the screen to show the difference
			this.setVisible(true);
		}
		else if(((JButton)e.getSource()).getText() == "Show Score Board") {
			scoreFrame.show(true);
		}
		// game is finished
		else if(((JButton)e.getSource()).getText() == "Silance Is Gold!") {//gameboard triggered the invisible button
			this.remove(board);
			this.add(mainMenu);
			
			//update the screen
			mainMenu.repaint();
			this.setVisible(true);
		}
	}
	public static void main(String[] args) {
		Program program = new Program();
	}

}