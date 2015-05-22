package Game;

import java.util.*;

public class Dungeon {
	private int size;
	private int turn;
	private boolean verbose;
	private final int historyLength = 10;
	
	private Wall[][] walls; 
	private Chamber[][] chambers;
	private AgentStruct[] agents;
	private ArrayList<Checker> checkers = new ArrayList<Checker>();
	
	public Dungeon(int size, Agent[] agents){
		this(size, agents, false);
	}
	
	public Dungeon(int size, Agent[] agents, boolean verbose){
		chambers = new Chamber[size][size];
		this.size = size;
		this.agents = new AgentStruct[agents.length];
		for(int i = 0; i < agents.length; ++i){
			AgentStruct a = new AgentStruct();
			a.agent = agents[i];
			a.x = 0; // TODO set a random position or something
			a.y = 0;
			a.searchedRoom = false;
			a.item = 0;
			this.agents[i] = a;
		}
		
		this.verbose = verbose;
		generateWorld();
	}
	
	private void generateWorld(){
		create_walls();
		add_rooms();
		destroy_walls();
		generate_items(10);
	}
	
	private void generate_items(int n) {
		Random rand = new Random();
		int x,y;
		for(int j = 1;j<n;j++){
			x = rand.nextInt(size);
			y = rand.nextInt(size);
			if(chambers[x][y].itemLevel > 0){
				j--;
			}else{
				chambers[x][y].itemLevel = j;
			}
		}
	}

	private void destroy_walls() {
		if(!chambers[0][0].checked){
			checkers.add(new Checker(0,0));
		}
		for(int i =0;checkers.size()>0;){
			int x = checkers.get(i).x;
			int y = checkers.get(i).y;
			Chamber pokoj = chambers[x][y];
			pokoj.checked = true;
			if(x == 0){
				chambers[x][y].connections[0].connection = false;
			}
			if(x == size -1){
				chambers[x][y].connections[3].connection = false;
			}
			if(y == 0){
				chambers[x][y].connections[1].connection = false;
			}
			if(y == size -1){
				chambers[x][y].connections[2].connection = false;
			}
			if(chambers[x][y].connections[0].connection){
				if(!chambers[x-1][y].checked){
					checkers.add(new Checker(x-1,y));
				}
			}
			if(chambers[x][y].connections[3].connection){
				if(!chambers[x+1][y].checked){
					checkers.add(new Checker(x+1,y));
				}
			}
			if(chambers[x][y].connections[1].connection){
				if(!chambers[x][y-1].checked){
					checkers.add(new Checker(x,y-1));
				}
			}
			if(chambers[x][y].connections[2].connection){
				if(!chambers[x][y+1].checked){
					checkers.add(new Checker(x,y+1));
				}
			}
			checkers.remove(i);
		}
		if(checkers.size() == 0){
			
			for(int i = 0;i<size;i++){
				for(int j = 0;j<size;j++){
					if(!chambers[i][j].checked){
					if(j>0){
					chambers[i][j].connections[1].connection = true;
					}else{
						chambers[i][j].connections[0].connection = true;
						
					}
					checkers.add(new Checker(i,j));
					destroy_walls();
					}
				}
			}
			return;
		}
	}

	private void add_rooms() {
		for(int i = 0;i<size;i++){
			for(int j = 0;j<size;j++){				
				chambers[i][j] = new Chamber(walls[i*2][j],walls[i*2+1][j],walls[i*2+1][j+1],walls[i*2+2][j]);
				
			}
		}
	}

	private void create_walls() {
		walls = new Wall[2*size+1][];
		for(int i = 0;i<size;i++){
			walls[0] = new Wall[size];
			for(int j =0;j<walls[0].length;j++){
				walls[0][j] = new Wall();
			}
		}
		for(int i = 1; i<size+1;i++){
			walls[i*2-1] = new Wall[size +1];
			for(int j =0;j<walls[i*2-1].length;j++){
				walls[i*2-1][j] = new Wall();
			}
			walls[i*2] = new Wall[size];
			for(int j =0;j<walls[i*2].length;j++){
				walls[i*2][j] = new Wall();
			}
		}
	}

	private void presentWorld(){
		for(int i=0;i<size;i++){
			String line0 = "",line1 = "";
			String line2 = "";
			
			for(int j=0;j<size;j++){
				
				String horizontal,vertical;
				if(chambers[i][j].connections[1].connection){
					vertical = " ";
				}else{
					vertical = "|";
				}
				
				if(chambers[i][j].connections[3].connection){
					horizontal = " ";
				}else{
					horizontal = "_";
				}
				if(i==0){
					
					line0 += " " + "_" + "_" + "_";
				}
				String itemLevel = null,track = null,agent = null;
				itemLevel = "" + chambers[i][j].itemLevel;
				track = "" + chambers[i][j].track;
				if(chambers[i][j].itemLevel == -1){
					itemLevel = "0";
				}
				if(chambers[i][j].track == -1){
					track = "0";
				}
				for(int k = 0; k < agents.length; k++){
					if(agents[k].x == i && agents[k].y == j){
						agent = "#";
					}else{
						agent = " ";
					}
				}
				line1 += vertical + track + itemLevel + agent;
				line2 += vertical + horizontal + horizontal + horizontal;
				
			}
			if(i==0){
				System.out.println(line0);
			}
			line1+="|";
			line2+="|";
			System.out.println(line1);
			System.out.println(line2);
		}
	}

	public int play(){
		int i = 0;
		while(true){
			if(verbose) presentWorld();
			
			AgentStruct a = agents[i];
			System.out.println(a.x + " " + a.y);
			Choice[] choices = getChoices(a);
			
			Choice decision = choices[a.agent.decide(choices, a.copy())];
			
			int result = executeDecision(a, decision);
			if(result >= 0){
				return result;
			}
			
			i++;
			if(i == agents.length){
				i = 0;
				turn++;
			}
		}
	}
	
	private Choice[] getChoices(AgentStruct a){
		Vector<Choice> choices = new Vector<Choice>();
		
		Chamber agentLoc = chambers[a.x][a.y];
		
		for(int j = 0; j < 4; ++j){
			if(agentLoc.connections[j].connection){
				choices.add(Choice.values()[j]);
			}
		}

		if(!a.searchedRoom){
			choices.add(Choice.Search);
		}
		else if(agentLoc.itemLevel > 0){
			choices.add(Choice.PickUpItem);
		}
		
		Choice[] arr = new Choice[1];
		arr = choices.toArray(arr);
		return arr;
	}
	
	private int executeDecision(AgentStruct a, Choice decision){
		Chamber agentLoc = chambers[a.x][a.y];
		int dx = 0;
		int dy = 0;
		switch(decision){
		case MoveRight:
			dy++;
			break;
		case MoveUp:
			dx--;
			break;
		case MoveLeft:
			dy--;
			break;
		case MoveDown:
			dx++;
			break;
		case Search:
			a.searchedRoom = true;
			break;
		case PickUpItem:
			a.item = agentLoc.pickUpItem(a.item);
			break;
		}
		
		a.choiceHistory.add(decision);
		a.chamberHistory.add(agentLoc.copy());
		if(a.choiceHistory.size() > historyLength){
			a.chamberHistory.pollLast();
			a.choiceHistory.pollLast();
		}
		
		if(dx != 0 || dy != 0){
			a.x += dx;
			a.y += dy;
			a.searchedRoom = false;
			return resolveCombat(a.x,  a.y);
		}else{
			return -1;
		}
	}
	
	private int resolveCombat(int x, int y){
		int a0index = -1;
		int a1index = -1;
		AgentStruct a0 = null;
		AgentStruct a1 = null;
		for(int i = 0; i < agents.length; ++i){
			AgentStruct a = agents[i];
			if(a.x == x && a.y == y){
				if(a0 == null){ 
					a0 = a;
					a0index = i;
				}
				else{
					a1 = a;
					a1index = i;
					break;
				}
			}
		}
		
		if(a1 != null){
			if(a0.item > a1.item){
				return a0index;
			}
			if(a0.item < a1.item){
				return a1index;
			}
		}
		return -1;
	}
	public int getSize() {
		return size;
	}

	public int getTurn() {
		return turn;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}
}
