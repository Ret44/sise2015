package Game;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.plot.*;

public class FuzzyAgent implements Agent {
	protected String filename; 
	public FuzzyAgent(String filename){
		this.filename = filename;
	}
	
	@Override
	public int decide(Choice[] choices, AgentStruct state) {
		FIS fis = FIS.load(filename, true);

		double max = 0;
		int index = 0;

		for(int i = 0; i < choices.length; ++i){
			fis.setVariable("action", (double) choices[i].ordinal());
			if(!state.choiceHistory.isEmpty()) 
				fis.setVariable("previousAction", (double) state.choiceHistory.peek().ordinal());
			fis.setVariable("power", ((double) state.item) / 10.0);
			
			fis.evaluate();
			
			double val = fis.getVariable("idea").getLatestDefuzzifiedValue();
			if(val > max){
				max = val;
				index = i;
			}
		}
		
		System.out.println("AI choice: " + choices[index]);
		//JFuzzyChart.get().chart(fis.getFunctionBlock("tipper"));
		return index;
	}

	@Override
	public String getName() {
		return "TestFuzzy";
	}

}
