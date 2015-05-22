package Game;

public class Chamber {
	public Wall[] connections = new Wall[4];
	public int itemLevel = -1;
	public boolean checked = false;
	public Trail trail = null;
	
	public Chamber(){}
	
	public Chamber(Wall up,Wall left,Wall right,Wall down){
		connections[0] = up;
		connections[1] = left;
		connections[2] = right;
		connections[3] = down;
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
