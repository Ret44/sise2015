package Game;

import java.io.IOException;

public class main {

	private static Dungeon dungeon;
	
	public static void main(String[] args) {
		System.load(System.getProperty("user.dir")+"\\CLIPSJNI.dll");
		
		//Agent initialization
		Agent[] agentList = new Agent[2];
		agentList[0] = new FuzzyAgent("RomanChomik.flc");
		agentList[1] = new CLIPSAgent("RomanChomik.clp");
		
		//Dungeon generation
		dungeon = new Dungeon(10, 10, 10, agentList, true);
		

		System.out.println("Q-Exit | WSAD-Move Agent");
		
		int res = dungeon.play();
		
		System.out.println("Winner: " + res + agentList[res].getName());
	
		System.out.println("Game terminated");
	}
	

}
