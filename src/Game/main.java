package Game;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class main {

	private static Dungeon dungeon;
	
	public static void main(String[] args) {
		System.load(System.getProperty("user.dir")+"\\CLIPSJNI.dll");

		//Arena.TestAgent(new FuzzyAgent("RomanChomik.flc"));
		//Arena.TestAgent(new CLIPSAgent("RomanChomik.clp", true));
		//Arena.TestAgent(new FuzzyAgent("agent_bs.flc"));
		//Arena.TestAgent(new CLIPSAgent("agent_bs.CLP"));
		
		Arena arena = new Arena();
		arena.AddAgent(new FuzzyAgent("RomanChomik.flc"));
		arena.AddAgent(new CLIPSAgent("RomanChomik.clp"));
		arena.AddAgent(new FuzzyAgent("BartlomiejSieczka.flc"));
		arena.AddAgent(new CLIPSAgent("agent_bs.CLP"));
		arena.AddAgent(new CLIPSAgent("GniewomirCiolek.CLP"));
		
		arena.Fight(2);
		arena.PrintResults();
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		Date date = new Date();
		arena.SaveResults(dateFormat.format(date) + ".txt");
	}
	

}
