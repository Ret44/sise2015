package Game;

import java.io.IOException;

public class main {

	private static Dungeon dungeon;
	
	public static void main(String[] args) {
		System.load(System.getProperty("user.dir")+"\\CLIPSJNI.dll");

		//Arena.TestAgent(new FuzzyAgent("RomanChomik.flc"));
		Arena.TestAgent(new CLIPSAgent("RomanChomik.clp"));
		//Arena.TestAgent(new FuzzyAgent("agent_bs.flc"));
		//Arena.TestAgent(new CLIPSAgent("agent_bs.CLP"));
		
		/*Arena arena = new Arena();
		arena.AddAgent(new FuzzyAgent("RomanChomik.flc"));
		arena.AddAgent(new CLIPSAgent("RomanChomik.clp"));
		//arena.AddAgent(new FuzzyAgent("agent_bs.flc"));
		arena.AddAgent(new CLIPSAgent("agent_bs.CLP"));
		
		arena.Fight(40);
		arena.PrintResults();//*/
	}
	

}
