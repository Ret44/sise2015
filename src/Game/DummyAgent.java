package Game;

import java.io.IOException;
import java.util.Arrays;

public class DummyAgent implements Agent{

	@Override
	public int decide(Choice[] choices, AgentStruct state, int historyLength, int itemLevel, int turn) {
		System.out.println("" + (char)('#' + state.agentID) + "DECIDE!");
		for(int i = 0; i < choices.length; ++i){
			switch(choices[i]){
			case MoveRight:
				System.out.println("Press 'd' to moves right"); break;
			case MoveUp:
				System.out.println("Press 'w' to moves up"); break;
			case MoveLeft:
				System.out.println("Press 'a' to moves left"); break;
			case MoveDown:
				System.out.println("Press 's' to moves down"); break;
			case Search:
				System.out.println("Press 'f' to search the chamber"); break;
			case PickUpItem:
				System.out.println("Press 'f' to pick up the item"); break;
			}
		}
		
		while(true){
			try {
				char input = (char) System.in.read();
				System.in.skip(124123423);
				int ret;
				switch(input){
				case 'w':
					ret = Arrays.asList(choices).indexOf(Choice.MoveUp);
					if(ret != -1) return ret;
					break;
				case 's':
					ret = Arrays.asList(choices).indexOf(Choice.MoveDown);
					if(ret != -1) return ret;
					break;
				case 'a':
					ret = Arrays.asList(choices).indexOf(Choice.MoveLeft);
					if(ret != -1) return ret;
					break;
				case 'd':
					ret = Arrays.asList(choices).indexOf(Choice.MoveRight);
					if(ret != -1) return ret;
					break;
				case 'f':
					ret = Arrays.asList(choices).indexOf(Choice.Search);
					if(ret != -1) return ret;	
					ret = Arrays.asList(choices).indexOf(Choice.PickUpItem);
					if(ret != -1) return ret;
					break;
				}
				System.out.println("le");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
	}

	@Override
	public String getName() {
		return "Player controlled agent";
	}

}
