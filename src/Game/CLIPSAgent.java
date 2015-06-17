package Game;

import java.io.IOException;
import java.util.*;

import CLIPSJNI.*;

public class CLIPSAgent implements Agent {

	public Environment clips;
	private String filename;
	private boolean debug; 
	
	public CLIPSAgent(String filename, boolean debug){
		this.filename = filename;
		clips = new Environment();
		clips.load("templates.CLP");
		clips.load(filename);
		reset();
		this.debug = debug;
	}
	
	public CLIPSAgent(String filename){
		this(filename, false);
	}

	public void reset()
	{
		clips.reset();
		clips.run();
	}
	
	public void printAll()
	{
		PrimitiveValue pv; 
		String evalStr = "?*agentID*";
		String report = this.getName()+" ID:";
		pv = clips.eval(evalStr);
		report+=pv.toString()+" (";
		evalStr = "?*pos-x*";
		clips.eval(evalStr);
		report+=pv.toString()+",";
		evalStr = "?*pos-y*";
		clips.eval(evalStr);
		report+=pv.toString()+")";
		System.out.println(report);
		System.out.println("-----------------------");
		System.out.println("FACTS:");
		evalStr = "(facts)";
		clips.eval(evalStr);
		System.out.println("RULES:");
		evalStr = "(rules)";
		clips.eval(evalStr);
	}
	
	@Override
	public int decide(Choice[] choices, AgentStruct state, int historyLength, int itemLevel, int turn) {	
		
		for(int i=0;i<choices.length;i++)
		{
			if(choices[i].ordinal() < 4)
			{
				clips.assertString("(can-move "+choices[i].toString()+")");
			}
			else
			{
				clips.assertString("(can "+choices[i].toString()+")");
			}
		}
		clips.assertString("(item "+state.item+")");
			
		if(state.searchedRoom) clips.assertString("(room-searched)");
		
		clips.eval("(bind ?*agentID* "+state.agentID+")");
		clips.eval("(bind ?*pos-x* "+state.x+")");
		clips.eval("(bind ?*pos-y* "+state.y+")");
		String chamberString;
		
		chamberString = "(chamber";
		chamberString+=" (index 0)";
		chamberString+=" (item-level "+state.currentRoom.itemLevel+")";
		chamberString+=" (checked "+(state.currentRoom.checked?"true":"false")+")";
		if(state.currentRoom.trail!=null) chamberString+=" (trail "+state.currentRoom.trail.age+" "+state.currentRoom.trail.direction+" "+state.currentRoom.trail.agentID+")";
		else chamberString+= " (trail none)";
		chamberString+=" (connections ";
		for(int i=0;i<4;i++)
		{
			chamberString+= " "+state.currentRoom.connections[i].connection;
		}
		chamberString+="))";
		//System.out.print(chamberString);
		clips.assertString(chamberString);
	
	
		Iterator<Chamber> chamberIt = state.chamberHistory.descendingIterator();
		int index = 1;
		while(chamberIt.hasNext()){
			Chamber chamber = chamberIt.next();
			chamberString = "(chamber";
			chamberString+=" (index "+(index++)+")";
			chamberString+=" (item-level "+chamber.itemLevel+")";
			chamberString+=" (checked "+(chamber.checked?"true":"false")+")";
			if(chamber.trail!=null) chamberString+=" (trail "+chamber.trail.age+" "+chamber.trail.direction+" "+chamber.trail.agentID+")";
			else chamberString+= " (trail none)";
			chamberString+=" (connections ";
			for(int i=0;i<4;i++)
			{
				chamberString+= " "+chamber.connections[i].connection;
			}
			chamberString+="))";
			clips.assertString(chamberString);
		}
		
		Iterator<Choice> choiceIt = state.choiceHistory.descendingIterator();
		index = 1;
		while(choiceIt.hasNext())
		{
			Choice choice = choiceIt.next();
			String choiceString="(previous-choice";
			choiceString+=" (index "+(index++)+")";
			choiceString+=" (choice "+choice.toString()+"))";
			clips.assertString(choiceString);
		}
		
		clips.run();
		
		String evalStr = "?*decision*";
		PrimitiveValue pv = clips.eval(evalStr);
		
		if(debug){
			printAll();
			System.out.println(pv);
			try {
				System.in.read();
				System.in.skip(100);				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		clips.reset();
		try {
			
			return Arrays.asList(choices).indexOf(Choice.valueOf(pv.toString()));
		} catch (Exception e) {
			//e.printStackTrace();
			return 0;
		}   
	     
	}

	@Override
	public String getName() {
		return "CLIPS Agent "+filename;
	}
}
