package Game;

import java.util.ArrayDeque;

public class AgentStruct {
	public int item;
	public int x, y;
	public int agentID;
	public boolean searchedRoom;
	public Agent agent;
	public ArrayDeque<Choice> choiceHistory = new ArrayDeque<Choice>();
	public ArrayDeque<Chamber> chamberHistory = new ArrayDeque<Chamber>();
	
	public AgentStruct copy(){
		AgentStruct c = new AgentStruct();
		c.item = item;
		c.x = x;
		c.y = y;
		c.searchedRoom = searchedRoom;
		c.agent = agent;
		c.choiceHistory = choiceHistory.clone();
		c.chamberHistory = chamberHistory.clone();
		return c;
	}
}
