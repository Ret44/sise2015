package Game;

import java.io.IOException;

public class DummyAgent implements Agent{

	@Override
	public int decide(Choice[] choices, AgentStruct state) {
		System.out.println("DECIDE!");
		for(int i = 0; i < choices.length; ++i){
			switch(choices[i]){
			case MoveUp:
				System.out.println("Press " + (i + 1) + " to moves up"); break;
			case MoveLeft:
				System.out.println("Press " + (i + 1) + " to moves left"); break;
			case MoveRight:
				System.out.println("Press " + (i + 1) + " to moves right"); break;
			case MoveDown:
				System.out.println("Press " + (i + 1) + " to moves down"); break;
			case Search:
				System.out.println("Press " + (i + 1) + " to search the chamber"); break;
			case PickUpItem:
				System.out.println("Press " + (i + 1) + " to pick up the item"); break;
			}
		}
		
		while(true){
			try {
				char input = (char) System.in.read();
				System.in.skip(124123423);
				int decision = input - '1';
				
				if(decision >= 0 && decision < choices.length){
					System.out.println(decision);
					return decision;
				}else{
					System.out.println("Wrong choice."); break;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		
		return 0;
	}

	@Override
	public String getName() {
		return "Player controlled agent";
	}

}
