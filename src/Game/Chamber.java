package Game;

public class Chamber {
	public boolean[] connections = new boolean[4];
	public int itemLevel = 0;
	
	public Chamber(){
		for(int i = 0; i < 4; ++i){
			connections[i] = true;
		}
	}
	
	public int pickUpItem(int currentItem){
		int t = itemLevel;
		itemLevel = currentItem;
		return t;
	}
	
	public Chamber copy(){
		Chamber ret = new Chamber();
		ret.itemLevel = itemLevel;
		ret.connections = connections.clone();
		return ret;
	}
}
