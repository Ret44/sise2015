package Game;

import java.util.*;

public class Dungeon {
	private int width;
	private int height;
	private int turn;
	private final int historyLength = 10;
	private boolean verbose;

	private Chamber[][] chambers;
	private AgentStruct[] agents;
	private int[][] agentPos;
	
	public Dungeon(int width, int height, Agent[] agents){
		this(width, height, agents, false);
	}
	
	public Dungeon(int width, int height, Agent[] agents, boolean verbose){
		chambers = new Chamber[width][height];
		this.width = width;
		this.height = height;
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
		agentPos = new int[agents.length][2];
		generateWorld();
	}
	
	private void generateWorld(){
		for(int x = 0; x < width; ++x){
			for(int y = 0; y < height; ++y){
				chambers[x][y] = new Chamber();
			}
		}
		// TODO do actual world generation
	}
	
	private void presentWorld(){
		// TODO do actual world presentation
	}
	
	public int play(){
		int i = 0;
		while(true){
			if(verbose) presentWorld();
			
			AgentStruct a = agents[i];
			
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
			if(agentLoc.connections[j]){
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
			dx++;
			break;
		case MoveUp:
			dy++;
			break;
		case MoveLeft:
			dx--;
			break;
		case MoveDown:
			dy--;
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
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getTurn() {
		return turn;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}
}
