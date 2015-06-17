package Game;

import java.util.*;

public class Dungeon {
	private int size;
	private int turn;
	private boolean verbose;
	private int historyLength = 10;
	private int itemLevel;
	
	private Wall[][] walls; 
	private Chamber[][] chambers;
	private AgentStruct[] agents;
	private ArrayList<Checker> checkers = new ArrayList<Checker>();
	
	public Dungeon(int size, int historyLength, int itemLevel, Agent[] agents){
		this(size, historyLength, itemLevel, agents, false);
	}
	
	public Dungeon(int size, int historyLength, int itemLevel, Agent[] agents, boolean verbose){
		chambers = new Chamber[size][size];
		this.size = size;
		this.itemLevel = itemLevel;
		this.historyLength = historyLength;
		
		generateWorld();
		this.agents = new AgentStruct[agents.length];
		Random rand = new Random();
		for(int i = 0; i < agents.length; ++i){
			AgentStruct a = new AgentStruct();
			a.agent = agents[i];
			a.x = rand.nextInt(size);
			a.y = rand.nextInt(size);
			a.searchedRoom = false;
			a.item = 0;
			a.agentID = i;
			a.currentRoom = chambers[a.x][a.y];
			this.agents[i] = a;
		}
		
		this.verbose = verbose;
	}
	
	private void generateWorld(){
		create_walls();
		add_rooms();
		destroy_walls();
		generate_items(itemLevel);
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
				String itemLevel = null,track = null,agent = "";
				itemLevel = "" + chambers[i][j].itemLevel;

				if(chambers[i][j].trail == null){
					track = "0";
				}else{
					track = "" + chambers[i][j].trail.age;
				}
				if(chambers[i][j].itemLevel == -1){
					itemLevel = "0";
				}
				char ag = ' ';
				for(int k = 0; k < agents.length; k++){
					if(agents[k].x == i && agents[k].y == j){
						ag = ((char)('#' + k));
					}
				}
				agent += "" + ag;
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

	public int play(int turnLimit){
		int i = 0;
		while(true){
			for(int x = 0; x < size; ++x){
				for(int y = 0; y < size; ++y){
					Chamber c = chambers[x][y];
					if(c.trail != null && c.trail.agentID == i){
						--c.trail.age;
						if(c.trail.age <= 0){
							c.trail = null;
						}
					}
				}
			}
			if(verbose) {
				presentWorld();
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
			}
			
			AgentStruct a = agents[i];
			//System.out.println(a.x + " " + a.y);
			Choice[] choices = getChoices(a);

			Choice decision = choices[a.agent.decide(choices, a.copy(), historyLength, itemLevel, turn)];
			
			int result = executeDecision(a, decision);
			if(result >= 0){
				return result;
			}
			
			i++;
			if(i == agents.length){
				i = 0;
				turn++;
				if(turn == turnLimit) return -1;
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
		int dir = -1;
		switch(decision){
		case MoveRight:
			dy++;
			dir = 0;
			break;
		case MoveUp:
			dx--;
			dir = 1;
			break;
		case MoveLeft:
			dy--;
			dir = 2;
			break;
		case MoveDown:
			dx++;
			dir = 3;
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
			a.chamberHistory.pollFirst();
			a.choiceHistory.pollFirst();
		}
		
		if(dx != 0 || dy != 0){
			agentLoc.trail = new Trail(historyLength, dir, a.agentID);
			a.x += dx;
			a.y += dy;
			a.searchedRoom = false;
			a.currentRoom = chambers[a.x][a.y];
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
