package Game;

import java.io.IOException;

public class main {

	private static Dungeon dungeon;
	
	public static void main(String[] args) {
		
		//Agent initialization
		Agent[] agentList = new Agent[2];
		for(int i=0; i < agentList.length; i++)
			agentList[i] = new RandomAgent();
		
		//Dungeon generation
		dungeon = new Dungeon(10, 10, agentList, true);
		
		int winner = dungeon.play();
		
		System.out.println("Battle ended in " + dungeon.getTurn());
		System.out.println("Winner: " + agentList[winner].getName());
		
	}
	

}
