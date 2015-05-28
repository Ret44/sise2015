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

		
		// TODO add inputs
		fis.setVariable("service", 3);
		fis.setVariable("food", 7);
		
		fis.evaluate();
		
		
		// TODO take outputs and return decision
		fis.getVariable("tip").getLatestDefuzzifiedValue();
		


		JFuzzyChart.get().chart(fis.getFunctionBlock("tipper"));
		return 0;
	}

	@Override
	public String getName() {
		return "TestFuzzy";
	}

}
