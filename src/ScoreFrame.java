import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
//import javax.swing.JPanel;

public class ScoreFrame implements ActionListener {
	private JFrame scoreFrame;
	private ScorePanel highScorePanel;
	private ScorePanel latestRunsPanel;
	
	private ScoreList highScoreList;
	private ScoreList latestRunsList;
	
	private GameBoard game;
	public ScoreFrame(GameBoard game) {
		this.game = game;
		scoreFrame = new JFrame();
		
		//functional lists
		highScoreList = new ScoreList("highScoreList", Const.HIGH_SCORE_LIST_LENGTH);
		latestRunsList = new ScoreList("latestRunsList", Const.LATEST_RUNS_LIST_LENGTH);
		
		//GUI lists "Panels"
		highScorePanel = new ScorePanel("High Score", highScoreList.getList(), highScoreList.getMaxSize());
		latestRunsPanel = new ScorePanel("Latest Runs", latestRunsList.getList(), latestRunsList.getMaxSize());
		
		scoreFrame.setSize(310,Const.CANVAS_MAX_HEIGHT + 50);
		scoreFrame.setLayout(new GridLayout(2,1));
		scoreFrame.setResizable(false);
		
		scoreFrame.add(highScorePanel);
		scoreFrame.add(latestRunsPanel);		
		
	}
	@Override 
	public void actionPerformed(ActionEvent e) {
		ScoreBlock b = game.getLatestGameScoreBlock();
		
		ScoreBlock toHighScoreList = new ScoreBlock(b.getName(),b.getScore());
		highScoreList.add(toHighScoreList);
		highScoreList.sort();
		// isFull returns true if the list is one element bigger than the max size
		if(highScoreList.isFull()) {
			highScoreList.removeLastElement();
		}
		
		// the name is not used anyway in latestRunsList
		ScoreBlock toLatestRunsList = new ScoreBlock("", b.getScore());
		latestRunsList.add(toLatestRunsList);
		// isFull returns true if the list is one element bigger than the max size
		if(latestRunsList.isFull()) {
			latestRunsList.removeLastElement();
		}
		
	}
	public void show(boolean set) {
		scoreFrame.setVisible(set);
	}
}
