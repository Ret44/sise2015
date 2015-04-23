package Game;

public class AgentStruct {
	public int item;
	public int x, y;
	public boolean searchedRoom;
	public Agent agent;
	
	public AgentStruct copy(){
		AgentStruct c = new AgentStruct();
		c.item = item;
		c.x = x;
		c.y = y;
		c.searchedRoom = searchedRoom;
		c.agent = agent;
		return c;
	}
}
