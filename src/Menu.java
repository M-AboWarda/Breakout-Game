import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Menu extends JPanel {
	
	//private JLabel gameNameLabel;
	private JButton playBtn;
	private JButton scoreBtn;
	
	public Menu(Program program) {
		JLabel gameNameLabel;
		gameNameLabel = new JLabel("<html><h1 style='color:#c22; font-weight:bold; font-size:50px'>Breakout game!</h1></html>");
		gameNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		playBtn = new JButton("Play");
		playBtn.setFont(new Font("monospace",Font.BOLD, 40));
		playBtn.addActionListener(program.getGameBoard());//start the game when clicked
		playBtn.addActionListener(program);//remove the menu and add the game board when clicked
		
		scoreBtn = new JButton("Show Score Board");
		scoreBtn.setFont(new Font("monospace",Font.BOLD, 40));
		scoreBtn.addActionListener(program);// to show scoreFrame
		
		setLayout(new GridLayout(3,1));
		
		add(gameNameLabel);
		add(playBtn);
		add(scoreBtn);
	}
	
	@Override 
	public Dimension getPreferredSize() {
		return new Dimension(Const.CANVAS_MAX_WIDTH, Const.CANVAS_MAX_HEIGHT);
	}
}
