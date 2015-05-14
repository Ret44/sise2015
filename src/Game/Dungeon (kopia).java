package Game;

import java.util.*;

public class Dungeon {
	private int size;
	private int turn;
	private boolean verbose;
	
	private Wall[][] walls; 
	private Chamber[][] chambers;
	private Agent[] agents;
	private int[][] agentPos;
	private ArrayList<Checker> checkers = new ArrayList<Checker>();
	
	public Dungeon(int size, Agent[] agents){
		this(size, agents, false);
	}
	
	public Dungeon(int size, Agent[] agents, boolean verbose){
		chambers = new Chamber[size][size];
		this.size = size;
		this.agents = agents;
		this.verbose = verbose;
		this.agents = agents;
		agentPos = new int[agents.length][2];
		generateWorld();
	}
	
	private void generateWorld(){
		create_walls();
		add_rooms();
		destroy_walls();
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
				pokoj.connections[0].connection = false;
			}
			if(x == size -1){
				pokoj.connections[3].connection = false;
			}
			if(y == 0){
				pokoj.connections[1].connection = false;
			}
			if(y == size -1){
				pokoj.connections[2].connection = false;
			}
			if(pokoj.connections[0].connection){
				if(!chambers[x-1][y].checked){
					checkers.add(new Checker(x-1,y));
				}
			}
			if(pokoj.connections[3].connection){
				if(!chambers[x+1][y].checked){
					checkers.add(new Checker(x+1,y));
				}
			}
			if(pokoj.connections[1].connection){
				if(!chambers[x][y-1].checked){
					checkers.add(new Checker(x,y-1));
				}
			}
			if(pokoj.connections[2].connection){
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
				
				String horizontal,horizontal2,vertical;
				if(walls[i*2+1][j].connection){
					vertical = " ";
				}else{
					vertical = "|";
				}
				
				if(walls[(i+1)*2][j].connection){
					horizontal2 = " ";
				}else{
					horizontal2 = "_";
				}
				if(i==0){
					
					line0 += " " + "_" + "_";
				}
				String itemLevel = null,track = null;
				if(chambers[i][j].itemLevel == -1){
					itemLevel = "0";
				}
				if(chambers[i][j].track == -1){
					track = "0";
				}
				line1 += vertical + track + itemLevel;
				line2 += vertical + horizontal2 + horizontal2;
				
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
	
	public int passTurn(){
		for(int i = 0; i < agents.length; ++i){
			if(verbose) presentWorld();
			Vector<Choice> choices = new Vector<Choice>();
			int x = agentPos[i][0];
			int y = agentPos[i][1];
			Chamber agentLoc = chambers[x][y];
			for(int j = 0; j < 4; ++j){
				if(agentLoc.connections[j].connection){
					choices.add(Choice.values()[j]);
				}
			}
			Choice[] arr = new Choice[1];
			int decision = agents[i].decide(choices.toArray(arr));
			switch(choices.get(decision)){
			case MoveUp:
				chambers[x][y].track = 0;
				y++;
				break;
			case MoveLeft:
				chambers[x][y].track = 1;
				x--;
				break;
			case MoveDown:
				chambers[x][y].track = 3;
				y--;
				break;
			case Search:
				
				break;
			case PickUpItem:
				
				break;
			}
			agentPos[i][0] = x;
			agentPos[i][1] = y;
			// TODO combat
		}
		return -1; // no winners this turn
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
