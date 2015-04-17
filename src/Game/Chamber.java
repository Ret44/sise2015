package Game;

public class Chamber {
	public boolean[] connections = new boolean[4];
	public int itemLevel = -1;
	
	public int pickUpItem(int currentItem){
		int t = itemLevel;
		itemLevel = currentItem;
		return t;
	}
}
