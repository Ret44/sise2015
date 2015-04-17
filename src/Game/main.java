package Game;

import java.io.IOException;

public class main {

	private static Dungeon dungeon;
	
	public static void main(String[] args) {
		
		//Agent initialization
		Agent[] agentList = new Agent[2];
		for(int i=0;i<agentList.length;i++)
			agentList[i] = new DummyAgent("Dummy"+i);
		
		//Dungeon generation
		dungeon = new Dungeon(10,10,agentList,true);
		

		System.out.println("Q-Exit | WSAD-Move Agent");
		
		char input = 0;
		int agentInd = 0;
		
		while(true){
			if(dungeon.passTurn() != -1) break;
		}
		/*
		while(input!='q')
		{
			try {
				
				input = (char)System.in.read();		
				
				agentList[agentInd++].turn(input);	//Init turn logic for next agent
				
				if(agentInd>=agentList.length)
					agentInd = 0;
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		System.out.println("Game terminated");
	}
	

}
