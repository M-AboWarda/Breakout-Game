
public class ScoreBlock {
	private String name;
	private int score;
	private ScoreList parentList;
	public ScoreBlock(String name, int score) {
		this.name = name;
		this.score = score;
	}
	public void setParent(ScoreList parent) {
		parentList = parent;
	}
	public String getName() {
		return name;
	}
	public int getScore() {
		return score;
	}
	@Override 
	public String toString() {
		if(parentList.getVariableName() == "latestRunsList") {			
			return ""+ getScore();
		
		}else if(parentList.getVariableName() == "highScoreList") {
			return  getName() + "        " + getScore(); 
		}
		else {
			return this.getName();
		}
	}
}
