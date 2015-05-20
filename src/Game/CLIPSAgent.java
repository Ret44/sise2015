package Game;

import CLIPSJNI.*;

public class CLIPSAgent implements Agent {

	public Environment clips;
	
	public CLIPSAgent(String filename){
		clips = new Environment();
		clips.load(filename);
		reset();
	}

	public void reset()
	{
		clips.reset();
		clips.run();
	}
	
	@Override
	public int decide(Choice[] choices, AgentStruct state) {
		//Przyk³ad operacji na clipsie:
	    //  String evalStr = "(find-all-facts ((?f state-list)) TRUE)";
//	      MultifieldValue pv = (MultifieldValue) clips.eval(evalStr);
		return 0;
	}

	@Override
	public String getName() {
		return "CLIPS Agent";
	}

}
