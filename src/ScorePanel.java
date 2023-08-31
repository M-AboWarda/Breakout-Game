import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
// ScorePanel : every thing here is about the GUI
public class ScorePanel extends JPanel{
	private JLabel JTitle;
	private JList<ScoreBlock> scoreList;
	
	public ScorePanel(String title, DefaultListModel<ScoreBlock> dlm, int maxSize) {
		setLayout(new BorderLayout());
		
		JTitle = new JLabel(title);
		JTitle.setFont(new Font("monospace", 0, 20));
		JTitle.setHorizontalAlignment(SwingConstants.CENTER);
		
		JList<String> preNums = new JList<String>(createListNumbers(maxSize));
		preNums.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 18));
		scoreList = new JList<ScoreBlock>(dlm);
		scoreList.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 18));
		
		add(JTitle, BorderLayout.NORTH);
		add(preNums, BorderLayout.WEST);
		add(scoreList, BorderLayout.CENTER);
		
	}
	public DefaultListModel<String> createListNumbers(int maxSize) {
		DefaultListModel<String> preNums = new DefaultListModel<String>();
		for(int i = 0; i < maxSize; i++) {
			preNums.addElement((i + 1) + ".");
		}
		
		return preNums;
	}
}
