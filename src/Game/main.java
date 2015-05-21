package Game;

import java.io.IOException;

public class main {

	private static Dungeon dungeon;
	
	public static void main(String[] args) {
		
		//Agent initialization
		Agent[] agentList = new Agent[1];
		for(int i=0;i<agentList.length;i++)
			agentList[i] = new DummyAgent();
		
		//Dungeon generation
		dungeon = new Dungeon(10, agentList, true);
		

		System.out.println("Q-Exit | WSAD-Move Agent");
		
		char input = 0;
		int agentInd = 0;
		
		int res = dungeon.play();
		
		System.out.println("Winner: " + res + agentList[res].getName());
	
		System.out.println("Game terminated");
	}
	

}
