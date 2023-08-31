import javax.swing.DefaultListModel;
// this is the "score list structure" lists modification logics are here 
public class ScoreList {
	private DefaultListModel<ScoreBlock> dlm;
	private int maxSize;
	private String thisVariableName;
	public ScoreList(String thisVariableName, int maxSize) {
		this.maxSize = maxSize;
		this.thisVariableName = thisVariableName;
		dlm = new DefaultListModel<ScoreBlock>();
		
	}
	
	public void add(ScoreBlock block) {
		if(dlm.size() < maxSize + 1) {
			block.setParent(this);
			dlm.add(0, block);
		}
	}
	public void remove(ScoreBlock element) {
		dlm.removeElement(element);
	}
	public void remove(int index) {
		dlm.removeElementAt(index);
	}
	public void removeLastElement() {
		remove(maxSize);
	}
	// fill the sortedList with score from high to low from the dlm and remove the ScoreBlock from it
	// then copy the sortedList back to the dlm (Note! dlm was empty before the copying process)
	public void sort() {
		DefaultListModel<ScoreBlock> sortedList = new DefaultListModel<ScoreBlock>();
		int index = 0;
		ScoreBlock temp = dlm.get(index);
		while(temp != null) {
			index++;
			if(dlm.size() != 1) {
				temp = (temp.getScore() >= dlm.get(index).getScore())? temp : dlm.get(index);
				
			}else {//there is only one ScoreBlock in the dlm
				break;
			}
			if(dlm.size() == index + 1) {
				sortedList.addElement(temp);
				dlm.removeElement(temp);
				temp = dlm.get(0);
				if(index == 1 && dlm.size() == 1) {
					sortedList.addElement(temp);
					dlm.removeElement(temp);
					temp = null;
				}
				index = 0;
			}
		}
		//sorting is done (all elements are sorted in the sortedList)
		//copy all element to the dlm
		for(int i = 0; i < sortedList.size(); i++) {
			dlm.addElement(sortedList.get(i));
		}
	}
	public boolean isFull() {
		return (dlm.size() == maxSize + 1);
	}
	
	public int getMaxSize() {
		return maxSize;
	}
	
	public DefaultListModel<ScoreBlock> getList() {
		return dlm;
	}
	public String getVariableName() {
		return thisVariableName;
	}
}
