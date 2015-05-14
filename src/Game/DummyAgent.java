package Game;

import java.io.IOException;

public class DummyAgent extends Agent{

	
	public DummyAgent()
	{
		this.Name = "Dummy";
	}
	
	public DummyAgent(String name)
	{
		this.Name = name;
	}
	
	@Override
	public void turn(char input) {
		switch(input)
		{
		case 'w' : System.out.println(this.Name+" moves up"); break;
		case 's' : System.out.println(this.Name+" moves down"); break;
		case 'a' : System.out.println(this.Name+" moves left"); break;
		case 'd' : System.out.println(this.Name+" moves right"); break;
		}
	}

	@Override
	public int decide(Choice[] choices) {
		System.out.println("DECIDE!");
		for(int i = 0; i < choices.length; ++i){
			switch(choices[i]){
			case MoveRight:
				System.out.println("Press " + (i + 1) + " to moves right"); break;
			case MoveUp:
				System.out.println("Press " + (i + 1) + " to moves up"); break;
			case MoveLeft:
				System.out.println("Press " + (i + 1) + " to moves left"); break;
			case MoveDown:
				System.out.println("Press " + (i + 1) + " to moves right"); break;
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

}
