package Game;

import java.util.Arrays;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.plot.*;

public class FuzzyAgent implements Agent {
	protected String filename; 
	public FuzzyAgent(String filename){
		this.filename = filename;
	}
	
	@Override
	public int decide(Choice[] choices, AgentStruct state, int historyLength, int itemLevel, int turn) {
		FIS fis = FIS.load(filename, true);
		
		double max = 0;
		int index = 0;

		if(state.searchedRoom)
			fis.setVariable("itemInRoom", state.currentRoom.itemLevel / (double) itemLevel);
		else
			fis.setVariable("itemInRoom", -1);
		
		fis.setVariable("power", state.item / (double) itemLevel);
		
		if(!state.choiceHistory.isEmpty()) 
			fis.setVariable("previousAction", (double) state.choiceHistory.peek().ordinal());
		
		fis.setVariable("wasHere", 0);
		
		if(state.currentRoom.trail != null){
			if(state.currentRoom.trail.agentID != state.agentID) {
				fis.setVariable("trail", 0);
			}else{
				fis.setVariable("trail", 1);
			}
			
			fis.setVariable("trailDirection", state.currentRoom.trail.direction);
			fis.setVariable("trailAge", state.currentRoom.trail.age / (double) historyLength);
		}else{
			fis.setVariable("trail", -1);
			fis.setVariable("trailDirection", -1);
			fis.setVariable("trailAge", -1);
		}
		
		fis.setVariable("turn", turn);
		
		if(state.chamberHistory.contains(state.currentRoom)){
			Chamber[] cham = new Chamber[historyLength];
			state.chamberHistory.toArray(cham);
			double i = Arrays.asList(cham).indexOf(state.currentRoom);
			fis.setVariable("wasHere", i / historyLength);
		}else{
			fis.setVariable("wasHere", 2);
		}
		
		
		Choice[] cho = new Choice[0];
		cho = state.choiceHistory.toArray(cho);
		fis.setVariable("lastMove", -1);
		for(int i = cho.length-1; i >= 0; --i){
			if(cho[i].ordinal() <= 3){
				fis.setVariable("lastMove", cho[i].ordinal());
				//System.out.println("LastMove: " + cho[i]);
				break;
			}
		}
		
		
		for(int i = 0; i < choices.length; ++i){
			fis.setVariable("action", (double) choices[i].ordinal());
			
			fis.evaluate();
			
			double val = fis.getVariable("idea").getLatestDefuzzifiedValue();
			//System.out.println(choices[i].toString() + ": " + val);
			if(val > max){
				max = val;
				index = i;
			}
		}
		
		//System.out.println("AI choice: " + choices[index]);
		//JFuzzyChart.get().chart(fis.getFunctionBlock("tipper"));
		turn++;
		return index;
	}

	@Override
	public String getName() {
		return "TestFuzzy";
	}

}
