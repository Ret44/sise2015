package Game;

import java.util.Arrays;

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
	
	public void printFacts()
	{
		String evalStr = "(facts)";
		clips.eval(evalStr);
		//try {
		//	for(int i=0;i<pv.size();i++)
		//	{
		//		System.out.print(pv.get(i).symbolValue() + " " + pv.get(i).stringValue());
		//	}
		//} catch (Exception e) {
		//	// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}
	}
	
	@Override
	public int decide(Choice[] choices, AgentStruct state) {	
		
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
		
		
		clips.run();
		  String evalStr = "?*decision*";
	      PrimitiveValue pv = clips.eval(evalStr);
	      clips.reset();
	      try {
			System.out.println(pv.toString());
			return Arrays.asList(choices).indexOf(Choice.valueOf(pv.toString()));
	      } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
	      }   
	     
	}

	@Override
	public String getName() {
		return "CLIPS Agent";
	}

}
